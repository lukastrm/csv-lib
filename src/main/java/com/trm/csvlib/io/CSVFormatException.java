/*
 * Copyright (c) 2020, trm factory, Lukas Trommer. All rights reserved.
 * This software is provided under a separate licence agreement only. Further usage, modification and/or redistribution
 * without the written permission of the author(s) is strictly prohibited.
 */
package com.trm.csvlib.io;

import com.trm.csvlib.model.CSVException;

/**
 * An exception type used to indicate a malformed input data format.
 *
 * @author Lukas Trommer
 * @since 1.0
 */
public class CSVFormatException extends CSVException {

    public CSVFormatException(String message) {
        super(message);
    }
}
