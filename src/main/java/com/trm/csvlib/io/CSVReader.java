/*
 *  Copyright (c) 2020, trm factory, Lukas Trommer. All rights reserved.
 *  This software is provided under a separate licence agreement only.
 *  Further usage, modification and/or redistribution without the written permission of the author(s)
 *  is strictly prohibited.
 */

package com.trm.csvlib.io;

import com.trm.csvlib.model.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

/**
 * @author Lukas Trommer
 * @since 1.0
 */
public class CSVReader {

    private final InputStreamReader reader;
    private final CSVFormat format;
    private char read;
    private int rowIndex;
    private boolean eof = false;
    private boolean separated = true;
    private boolean enclosing = false;

    public CSVReader(InputStream stream) {
        this.reader = new InputStreamReader(stream);
        this.format = CSVFormat.DEFAULT;
    }

    public CSVReader(InputStream stream, Charset charset, CSVFormat format) {
        this.reader = new InputStreamReader(stream, charset);
        this.format = format;
    }

    public <T extends TableDecoder<R>, R extends RowDecoder> T readTable(TableDecoderFactory<T> tableDecoderFactory, RowDecoderFactory<R> rowDecoderFactory) throws IOException, CSVException {
        T tableDescriptor = tableDecoderFactory.newTable();
        R row;

        while ((row = readRow(rowDecoderFactory)) != null) {
            tableDescriptor.addRow(row);
        }

        return tableDescriptor;
    }

    public <T extends RowDecoder> T readRow(RowDecoderFactory<T> rowDecoderFactory) throws IOException, CSVException {
        if (eof) {
            return null;
        }

        T row = rowDecoderFactory.newRow();
        StringBuilder builder = new StringBuilder();
        int cellIndex = 0;

        while (!read()) {
            if (enclosing) {
                if (read == format.enclosingCharacter) {
                    if (read()) {
                        break;
                    } else {
                        if (read == format.enclosingCharacter) {
                            builder.append(read);
                        } else if (read == format.separator) {
                            enclosing = false;
                            row.addCellValue(builder.toString(), new CellAddress(rowIndex, cellIndex++));
                            builder = new StringBuilder();
                            separated = true;
                        } else if (read == '\n' || read == '\r') {
                            enclosing = false;
                            break;
                        } else {
                            throw new CSVFormatException("Bad CSV format: Enclosing character was not escaped correctly");
                        }
                    }
                } else {
                    builder.append(read);
                }
            } else {
                if (read == format.separator) {
                    row.addCellValue(builder.toString(), new CellAddress(rowIndex, cellIndex++));
                    builder = new StringBuilder();
                    separated = true;
                } else if (read == '\n' || read == '\r') {
                    separated = true;
                    break;
                } else {
                    if (separated && read == format.enclosingCharacter) {
                        enclosing = true;
                    } else if (read != 0xFEFF) {
                        builder.append(read);
                    }

                    separated = false;
                }
            }
        }

        if (eof) {
            return null;
        } else {
            row.addCellValue(builder.toString(), new CellAddress(rowIndex, cellIndex));
            rowIndex++;
            return row;
        }
    }

    private boolean read() throws IOException {
        int in = reader.read();

        if (in == -1) {
            return eof = true;
        } else {
            char read = (char) in;

            if (!enclosing) {
                if (read == '\n' && this.read == '\r') {
                    return read();
                }
            }

            this.read = read;
            return false;
        }
    }
}