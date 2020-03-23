/*
 * Copyright (c) 2020, trm factory, Lukas Trommer. All rights reserved.
 * This software is provided under a separate licence agreement only. Further usage, modification and/or redistribution
 * without the written permission of the author(s) is strictly prohibited.
 */
package com.trm.csvlib.data;

import com.trm.csvlib.Utility;
import com.trm.csvlib.model.TableDecoder;
import com.trm.csvlib.model.TableEncoder;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * This class represents the whole data contained in a CSV data set.
 * Each subset of data is represented as a single {@link UniformDataRow} object.
 *
 * @author Lukas Trommer
 * @since 1.0
 */
public class UniformDataTable<T extends UniformDataRow> implements TableDecoder<T>, TableEncoder<T> {

    private List<T> rows = new ArrayList<>();

    /**
     * Constructor without arguments. When using this way to instantiate a new object of {@link UniformDataTable} an
     * empty list of {@code T} objects is created as a value storage.
     */
    public UniformDataTable() { }

    /**
     * Constructor for a given list of {@code T} objects.
     *
     * @param rows a list of {@code T} objects representing the data described by this instance of {@link UniformDataTable}
     */
    public UniformDataTable(List<T> rows) {
        if (rows == null) {
            throw new NullPointerException();
        }

        this.rows = rows;
    }

    @Override
    public Iterator<T> rowIterator() {
        return rows.iterator();
    }

    /**
     * Returns the contained data as a list of {@code T} objects.
     *
     * @return all data rows as list of {@code T} objects
     */
    public List<T> getRows() {
        return rows;
    }

    /**
     * Returns the data for a row at a given row index in the data set as {@code T} object..
     *
     * @param index the desired index for which the row data will be returned
     * @return the row data as {@code T} object
     */
    public T getRow(int index) {
        return rows.get(index);
    }

    /**
     * Sets the data for a row at a specified row index. If the row index exceeds the current size of the value storage
     * new empty rows will be created.
     *
     * @param index the desired index at which the row will be inserted
     * @param row the row containing the data as {@code T} object
     */
    public void setRow(int index, T row) {
        Utility.setAndFillList(rows, row, index);
    }

    /**
     * Adds the data for a row at the end of the current data set.
     *
     * @param row the row containing the data as {@code T} object
     */
    public void addRow(T row) {
        rows.add(row);
    }
}