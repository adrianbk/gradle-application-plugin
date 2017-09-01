package com.adrianbk.deployment.assertions;

public class Assert {
    public static void notNull(Object o, String context) throws IllegalArgumentException {
        if (o == null) {
            throw new IllegalArgumentException(String.format("'%s' must not be null", context));
        }
    }

    public static void notNullOrBlank(String name, String context) throws IllegalArgumentException {
        if (null == name || name.trim().length() == 0) {
            throw new IllegalArgumentException(String.format("'%s' must not be null or blank", context));
        }
    }
}
