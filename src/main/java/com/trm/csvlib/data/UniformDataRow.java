/*
 * Copyright (c) 2020, trm factory, Lukas Trommer. All rights reserved.
 * This software is provided under a separate licence agreement only. Further usage, modification and/or redistribution
 * without the written permission of the author(s) is strictly prohibited.
 */
package com.trm.csvlib.data;

import com.trm.csvlib.Utility;
import com.trm.csvlib.model.RowDecoder;
import com.trm.csvlib.model.RowEncoder;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @param <T>
 * @author Lukas Trommer
 * @since 1.0
 */
public abstract class UniformDataRow<T> implements RowDecoder, RowEncoder {

    protected final List<T> values;

    /**
     * Constructor without arguments. When using this way to instantiate a new object of {@link UniformDataRow} an empty
     * list of {@link T} values is created as a value storage.
     */
    protected UniformDataRow() {
        this.values = new ArrayList<>();
    }

    /**
     * Constructor for a given list of {@code T} values.
     *
     * @param values a list of {@code T} objects representing the cell values described by this instance
     */
    protected UniformDataRow(List<T> values) {
        if (values == null) {
            throw new NullPointerException();
        }

        this.values = values;
    }

    /**
     * Returns the contained cell values as a list of {@code T} objects.
     *
     * @return a list of {@code T} objects representing the cell values described by this instance
     */
    public List<T> getValues() {
        return values;
    }

    /**
     * Returns the cell value at a given cell index in the row.
     *
     * @param index the desired index for which the cell value will be returned
     * @return the cell value as {@code T} object
     */
    public T getValue(int index) {
        return values.get(index);
    }

    /**
     * Sets the value for a cell at a specified cell index. If the cell index exceeds the current size of the row value
     * storage new empty cells will be created.
     *
     * @param index the desired index at which the cell will be inserted
     * @param value the cell value as {@code T} object for the specified cell
     */
    public void setValue(int index, T value) {
        Utility.setAndFillList(values, value, index);
    }

    /**
     * Adds the value for a cell at the end of the current row.
     *
     * @param value the value as {@code T} object for the specified cell
     */
    public void addValue(T value) {
        values.add(value);
    }

    /**
     * Removes the cell and its value at a given cell index.
     * @param index the desired index at which the cell will be removed
     */
    public void removeValue(int index) {
        values.remove(index);
    }
}
