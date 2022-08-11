/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package liquibase.util;

import java.util.Objects;
import java.util.stream.Stream;

/**
 * Enumeration of IO case sensitivity.
 * <p>
 * Different filing systems have different rules for case-sensitivity.
 * Windows is case-insensitive, Unix is case-sensitive.
 * </p>
 * <p>
 * This class captures that difference, providing an enumeration to
 * control how file name comparisons should be performed. It also provides
 * methods that use the enumeration to perform comparisons.
 * </p>
 * <p>
 * Wherever possible, you should use the {@code check} methods in this
 * class to compare file names.
 * </p>
 *
 * @since 1.3
 */
public enum IOCase {

    /**
     * The constant for case-sensitive regardless of operating system.
     */
    SENSITIVE("Sensitive", true),

    /**
     * The constant for case-insensitive regardless of operating system.
     */
    INSENSITIVE("Insensitive", false),

    /**
     * The constant for case sensitivity determined by the current operating system.
     * Windows is case-insensitive when comparing file names, Unix is case-sensitive.
     * <p>
     * <strong>Note:</strong> This only caters for Windows and Unix. Other operating
     * systems (e.g. OSX and OpenVMS) are treated as case-sensitive if they use the
     * Unix file separator and case-insensitive if they use the Windows file separator
     * (see {@link java.io.File#separatorChar}).
     * </p>
     * <p>
     * If you serialize this constant on Windows, and deserialize on Unix, or vice
     * versa, then the value of the case-sensitivity flag will change.
     * </p>
     */
    SYSTEM("System", !CommonsFilenameUtils.isSystemWindows());

    /** Serialization version. */
    private static final long serialVersionUID = -6343169151696340687L;

    /**
     * Factory method to create an IOCase from a name.
     *
     * @param name  the name to find
     * @return the IOCase object
     * @throws IllegalArgumentException if the name is invalid
     */
    public static IOCase forName(final String name) {
        return Stream.of(IOCase.values()).filter(ioCase -> ioCase.getName().equals(name)).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid IOCase name: " + name));
    }

    /**
     * Tests for cases sensitivity in a null-safe manner.
     *
     * @param ioCase an IOCase.
     * @return true if the input is non-null and {@link #isCaseSensitive()}.
     * @since 2.10.0
     */
    public static boolean isCaseSensitive(final IOCase ioCase) {
        return ioCase != null && ioCase.isCaseSensitive();
    }

    /**
     * Returns the given value if not-null, the defaultValue if null.
     *
     * @param value the value to test.
     * @param defaultValue the default value.
     * @return the given value if not-null, the defaultValue if null.
     * @since 2.12.0
     */
    public static IOCase value(final IOCase value, final IOCase defaultValue) {
        return value != null ? value : defaultValue;
    }

    /** The enumeration name. */
    private final String name;

    /** The sensitivity flag. */
    private final transient boolean sensitive;

    /**
     * Constructs a new instance.
     *
     * @param name  the name
     * @param sensitive  the sensitivity
     */
    IOCase(final String name, final boolean sensitive) {
        this.name = name;
        this.sensitive = sensitive;
    }

    /**
     * Compares two strings using the case-sensitivity rule.
     * <p>
     * This method mimics {@link String#equals} but takes case-sensitivity
     * into account.
     * </p>
     *
     * @param str1  the first string to compare, not null
     * @param str2  the second string to compare, not null
     * @return true if equal using the case rules
     * @throws NullPointerException if either string is null
     */
    public boolean checkEquals(final String str1, final String str2) {
        Objects.requireNonNull(str1, "str1");
        Objects.requireNonNull(str2, "str2");
        return sensitive ? str1.equals(str2) : str1.equalsIgnoreCase(str2);
    }

    /**
     * Gets the name of the constant.
     *
     * @return the name of the constant
     */
    public String getName() {
        return name;
    }

    /**
     * Does the object represent case-sensitive comparison.
     *
     * @return true if case-sensitive
     */
    public boolean isCaseSensitive() {
        return sensitive;
    }

    /**
     * Replaces the enumeration from the stream with a real one.
     * This ensures that the correct flag is set for SYSTEM.
     *
     * @return the resolved object
     */
    private Object readResolve() {
        return forName(name);
    }

    /**
     * Gets a string describing the sensitivity.
     *
     * @return a string describing the sensitivity
     */
    @Override
    public String toString() {
        return name;
    }

}
