/*
 * Copyright (c) 2020, trm factory, Lukas Trommer. All rights reserved.
 * This software is provided under a separate licence agreement only. Further usage, modification and/or redistribution
 * without the written permission of the author(s) is strictly prohibited.
 */
package com.trm.csvlib.io;

import com.trm.csvlib.model.RowEncoder;
import com.trm.csvlib.model.TableEncoder;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.Iterator;

/**
 * @author Lukas Trommer
 * @since 1.0
 */
public class CSVWriter {

    private final OutputStreamWriter writer;
    private final CSVFormat format;
    private boolean didWriteRow;

    public CSVWriter(OutputStream writer) {
        this.writer = new OutputStreamWriter(writer);
        this.format = CSVFormat.DEFAULT;
    }

    public CSVWriter(OutputStream stream, Charset charset, CSVFormat format) {
        this.writer = new OutputStreamWriter(stream, charset);
        this.format = format;
    }

    public <T extends RowEncoder> void writeTable(TableEncoder<T> table) throws IOException {
        Iterator<T> iterator = table.rowIterator();

        while (iterator.hasNext()) {
            writeRow(iterator.next());
        }

        close();
    }

    public void writeRow(RowEncoder row) throws IOException {
        boolean didWriteCellValue = false;

        if (didWriteRow) {
            writer.write(System.lineSeparator());
        }

        Iterator<String> iterator = row.cellValueIterator();

        while (iterator.hasNext()) {
            String value = iterator.next();

            if (didWriteCellValue) {
                writer.write(format.separator);
            } else {
                didWriteCellValue = true;
            }

            if (value == null) {
                continue;
            }

            boolean enclosed = value.indexOf(format.enclosingCharacter) >= 0 || value.indexOf(format.separator) >= 0 ||
                    value.contains("\n") || value.contains("\r");

            if (enclosed) {
                value = value.replace(String.valueOf(format.enclosingCharacter), String.valueOf(new char[] {format.enclosingCharacter, format.enclosingCharacter}));
                writer.write(format.enclosingCharacter);
            }

            writer.write(value);

            if (enclosed) {
                writer.write(format.enclosingCharacter);
            }
        }

        didWriteRow = true;
    }

    public void close() throws IOException {
        writer.close();
    }
}