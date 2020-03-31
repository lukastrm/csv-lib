/*
 *  Copyright (c) 2020, trm factory, Lukas Trommer. All rights reserved.
 *  This software is provided under a separate licence agreement only.
 *  Further usage, modification and/or redistribution without the written permission of the author(s)
 *  is strictly prohibited.
 */

package com.trm.csvlib.io;

/**
 * @author Lukas Trommer
 * @since 1.0
 */
public class CSVFormat {

    public static final CSVFormat DEFAULT = new CSVFormat('"', ',');
    public static final CSVFormat MICROSOFT_EXCEL = new CSVFormat('"', ';');

    /**
     * the enclosing character used by the format to enclose special characters
     */
    final char enclosingCharacter;

    /**
     * the separator character used by the format to separate neighboring cell values
     */
    final char separator;

    /**
     * Constructor without arguments.
     *
     * @param enclosingCharacter the enclosing character used to wrap generic contents with control characters in it
     * @param separator the character used to separate two adjacent values
     */
    public CSVFormat(char enclosingCharacter, char separator) {
        this.enclosingCharacter = enclosingCharacter;
        this.separator = separator;
    }
}
