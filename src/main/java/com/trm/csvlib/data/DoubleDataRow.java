/*
 * Copyright (c) 2020, trm factory, Lukas Trommer. All rights reserved.
 * This software is provided under a separate licence agreement only. Further usage, modification and/or redistribution
 * without the written permission of the author(s) is strictly prohibited.
 */
package com.trm.csvlib.data;

import com.trm.csvlib.model.CellAddress;
import com.trm.csvlib.model.CellValueDataTypeException;

import java.util.Iterator;

/**
 * This class represents the whole data contained in a single row of a CSV data set.
 * Each value is represented as a single {@link Double} value.
 *
 * @see UniformDataRow
 * @author Lukas Trommer
 * @since 1.0
 */
public class DoubleDataRow extends UniformDataRow<Double> {

    @Override
    public void addCellValue(String value, CellAddress address) throws CellValueDataTypeException {
        try {
            addValue(value == null ? 0 : Double.parseDouble(value));
        } catch (NumberFormatException e) {
            throw new CellValueDataTypeException("Invalid double cell value", address);
        }
    }

    @Override
    public Iterator<String> cellValueIterator() {
        return new Iterator<>() {
            @Override
            public boolean hasNext() {
                return values.iterator().hasNext();
            }

            @Override
            public String next() {
                return Double.toString(values.iterator().next());
            }
        };
    }
}
