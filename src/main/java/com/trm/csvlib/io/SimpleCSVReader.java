/*
 *  Copyright (c) 2020, trm factory, Lukas Trommer. All rights reserved.
 *  This software is provided under a separate licence agreement only.
 *  Further usage, modification and/or redistribution without the written permission of the author(s)
 *  is strictly prohibited.
 */

package com.trm.csvlib.io;

import com.trm.csvlib.data.StringDataRow;
import com.trm.csvlib.data.StringDataTable;
import com.trm.csvlib.model.*;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

/**
 * @author Lukas Trommer
 * @since 1.0
 */
public class SimpleCSVReader extends CSVReader {

    private static final TableDecoderFactory<StringDataTable> TABLE_FACTORY = StringDataTable::new;
    private static final RowDecoderFactory<StringDataRow> ROW_FACTORY = StringDataRow::new;

    public SimpleCSVReader(InputStream stream) {
        super(stream);
    }

    public SimpleCSVReader(InputStream stream, Charset charset, CSVFormat format) {
        super(stream, charset, format);
    }

    public StringDataTable readTable() throws CSVException, IOException {
        StringDataTable table = null;

        try {
            table = super.readTable(TABLE_FACTORY, ROW_FACTORY);
        } catch (CellValueDataTypeException ignored) { }

        return table;
    }

    public StringDataRow readRow() throws CSVException, IOException {
        StringDataRow row = null;

        try {
            row = super.readRow(ROW_FACTORY);
        } catch (CellValueDataTypeException ignored) { }

        return row;
    }
}
