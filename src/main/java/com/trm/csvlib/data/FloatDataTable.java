/*
 *  Copyright (c) 2020, trm factory, Lukas Trommer. All rights reserved.
 *  This software is provided under a separate licence agreement only.
 *  Further usage, modification and/or redistribution without the written permission of the author(s)
 *  is strictly prohibited.
 */

package com.trm.csvlib.data;

import java.util.List;

/**
 * This class represents the whole data contained in a CSV data set.
 * Each subset of data is represented as a single {@link FloatDataRow} object.
 *
 * @see UniformDataTable
 * @author Lukas Trommer
 * @since 1.0
 */
public class FloatDataTable extends UniformDataTable<FloatDataRow> {

    public FloatDataTable() { }

    public FloatDataTable(List<FloatDataRow> rows) {
        super();
    }
}
