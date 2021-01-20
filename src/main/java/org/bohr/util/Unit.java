/**
 * Copyright (c) 2019 The BoHr Developers
 * <p>
 * Distributed under the MIT software license, see the accompanying file
 * LICENSE or https://opensource.org/licenses/mit-license.php
 */
package org.bohr.util;

import java.math.BigInteger;

import static java.util.Arrays.stream;

public enum Unit {
    NANO_bohr(0, "nBOHR"),

    MICRO_bohr(3, "μBOHR"),

    MILLI_bohr(6, "mBOHR"),

    bohr(9, "BOHR"),

    KILO_bohr(12, "kBOHR"),

    MEGA_bohr(15, "MBOHR");

    public final int exp;
    public final long factor;
    public final String symbol;

    Unit(int exp, String symbol) {
        this.exp = exp;
        this.factor = BigInteger.TEN.pow(exp).longValueExact();
        this.symbol = symbol;
    }

    /**
     * Decode the unit from symbol.
     *
     * @param symbol
     *            the symbol text
     * @return a Unit object if valid; otherwise false
     */
    public static org.bohr.util.Unit of(String symbol) {
        return stream(values()).filter(v -> v.symbol.equals(symbol)).findAny().orElse(null);
    }
}
