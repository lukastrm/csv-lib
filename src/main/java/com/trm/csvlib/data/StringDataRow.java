/*
 * Copyright (c) 2020, trm factory, Lukas Trommer. All rights reserved.
 * This software is provided under a separate licence agreement only. Further usage, modification and/or redistribution
 * without the written permission of the author(s) is strictly prohibited.
 */
package com.trm.csvlib.data;

import com.trm.csvlib.model.CellAddress;

import java.util.Iterator;

/**
 * This class represents the whole data contained in a single row of a CSV data set.
 * Each value is represented as a single {@link String} value.
 *
 * @see UniformDataRow
 * @author Lukas Trommer
 * @since 1.0
 */
public class StringDataRow extends UniformDataRow<String> {

    @Override
    public void addCellValue(String value, CellAddress address) {
        addValue(value);
    }

    @Override
    public Iterator<String> cellValueIterator() {
        return values.iterator();
    }
}
