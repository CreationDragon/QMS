package com.jfw.qms.utils;

import java.math.BigDecimal;

public class Logarithm {
    public static double log(BigDecimal value, double base) {
        return Math.log(value.doubleValue()) / Math.log(base);
    }
}
