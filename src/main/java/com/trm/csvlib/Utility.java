/*
 *  Copyright (c) 2020, trm factory, Lukas Trommer. All rights reserved.
 *  This software is provided under a separate licence agreement only.
 *  Further usage, modification and/or redistribution without the written permission of the author(s)
 *  is strictly prohibited.
 */

package com.trm.csvlib;

import java.util.List;

/**
 * @author Lukas Trommer
 * @since 1.0
 */
public class Utility {

    /**
     * This function adds an item to a list at the given position. If the index is larger than the current size of
     * the list it will be padded with {@code null} values.
     * @param list The list the item should be added to
     * @param value The item that should be added
     * @param index The desired index at which the item will be added to the list
     * @param <T> See {@link List}
     */
    public static <T> void setAndFillList(List<T> list, T value, int index) {
        int size = list.size();

        if (list.size() > index) {
            list.add(index, value);
        } else if (size == index) {
            list.add(value);
        } else {
            for (int i = list.size(); i < index; i++) {
                list.add(null);
            }

            list.add(value);
        }
    }
}
