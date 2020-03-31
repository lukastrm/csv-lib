/*
 *  Copyright (c) 2020, trm factory, Lukas Trommer. All rights reserved.
 *  This software is provided under a separate licence agreement only.
 *  Further usage, modification and/or redistribution without the written permission of the author(s)
 *  is strictly prohibited.
 */

package com.trm.csvlib.model;

/**
 *
 * @param <T>
 * @author Lukas Trommer
 * @since 1.0
 */
public interface TableDecoder<T extends RowDecoder> {

    void addRow(T row);
}
