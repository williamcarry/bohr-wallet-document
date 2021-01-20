/**
 * Copyright (c) 2019 The BoHr Developers
 * <p>
 * Distributed under the MIT software license, see the accompanying file
 * LICENSE or https://opensource.org/licenses/mit-license.php
 */
package org.bohr.util;

import java.math.BigDecimal;
import java.math.BigInteger;

import static java.math.RoundingMode.FLOOR;

/**
 * Represents an amount of BoHr, the base unit of computation.
 */
public final class Amount implements Comparable<Amount> {

    public static final org.bohr.util.Amount ZERO = new org.bohr.util.Amount(0);
    public static final org.bohr.util.Amount ONE = new org.bohr.util.Amount(1);
    public static final org.bohr.util.Amount TEN = new org.bohr.util.Amount(10);

    private final long nano;

    private Amount(long nano) {
        this.nano = nano;
    }

    /**
     * Converts n nBoHr to an amount.
     *
     * @param n
     *            the number of nBoHr
     * @return
     */
    public static org.bohr.util.Amount of(long n) {
        return new org.bohr.util.Amount(n);
    }

    /**
     * Converts n nBoHr to an amount.
     *
     * @param n
     *            the number of nBoHr
     * @return
     */
    public static org.bohr.util.Amount of(String n) {
        return new org.bohr.util.Amount(Long.parseLong(n));
    }

    /**
     * Converts n units to an amount.
     *
     * @param n
     *            the number of unit
     * @param unit
     *            the unit
     * @return an Amount
     * @throws ArithmeticException
     *             if the value overflows
     */
    public static org.bohr.util.Amount of(long n, Unit unit) throws ArithmeticException {
        return new org.bohr.util.Amount(Math.multiplyExact(n, unit.factor));
    }

    /**
     * Converts a BigDecimal of units to an amount.
     *
     * @param d
     *            the big decimal
     * @param unit
     *            the unit to use when converting
     * @return an Amount
     */
    public static org.bohr.util.Amount of(BigDecimal d, Unit unit) {
        return new org.bohr.util.Amount(d.movePointRight(unit.exp).setScale(0, FLOOR).longValueExact());
    }

    public static org.bohr.util.Amount sum(org.bohr.util.Amount a, org.bohr.util.Amount b) throws ArithmeticException {
        return new org.bohr.util.Amount(Math.addExact(a.nano, b.nano));
    }

    /**
     * Converts this amount to a BigDecimal.
     *
     * @param scale
     *            the scale of digits
     * @param unit
     *            the unit to use when converting.
     * @return A BigDecimal
     */
    public BigDecimal toDecimal(int scale, Unit unit) {
        BigDecimal nano = BigDecimal.valueOf(this.nano);
        return nano.movePointLeft(unit.exp).setScale(scale, FLOOR);
    }

    /**
     * Converts this amount to a long integer.
     *
     * @return a long integer.
     */
    public long toLong() {
        return nano;
    }

    /**
     * Converts this amount to a BigInteger.
     *
     * @return a BigInteger
     */
    public BigInteger toBigInteger() {
        return BigInteger.valueOf(nano);
    }

    @Override
    public int compareTo(org.bohr.util.Amount other) {
        return this.lessThan(other) ? -1 : (this.greaterThan(other) ? 1 : 0);
    }

    @Override
    public int hashCode() {
        return Long.hashCode(nano);
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof org.bohr.util.Amount && ((org.bohr.util.Amount) other).nano == nano;
    }

    @Override
    public String toString() {
        return String.valueOf(nano);
    }

    public boolean greaterThan(org.bohr.util.Amount other) {
        return nano > other.nano;
    }

    public boolean greaterThanOrEqual(org.bohr.util.Amount other) {
        return nano >= other.nano;
    }

    public boolean isPositive() {
        return greaterThan(ZERO);
    }

    public boolean isNotNegative() {
        return greaterThanOrEqual(ZERO);
    }

    public boolean lessThan(org.bohr.util.Amount other) {
        return nano < other.nano;
    }

    public boolean lessThanOrEqual(org.bohr.util.Amount other) {
        return nano <= other.nano;
    }

    public boolean isNegative() {
        return lessThan(ZERO);
    }

    public boolean isNotPositive() {
        return lessThanOrEqual(ZERO);
    }

    public org.bohr.util.Amount negate() throws ArithmeticException {
        return new org.bohr.util.Amount(Math.negateExact(this.nano));
    }

    public org.bohr.util.Amount add(org.bohr.util.Amount a) throws ArithmeticException {
        return new org.bohr.util.Amount(Math.addExact(this.nano, a.nano));
    }

    public org.bohr.util.Amount subtract(org.bohr.util.Amount a) throws ArithmeticException {
        return new org.bohr.util.Amount(Math.subtractExact(this.nano, a.nano));
    }

    public org.bohr.util.Amount multiply(long a) throws ArithmeticException {
        return new org.bohr.util.Amount(Math.multiplyExact(this.nano, a));
    }
}
