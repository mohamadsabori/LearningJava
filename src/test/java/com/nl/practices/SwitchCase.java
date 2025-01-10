package com.nl.practices;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SwitchCase {

    @Test
    void should_correct_answer() {
        String temp = null;
        String matchCase = switch (temp) {
            case null -> null;
            case String s -> "long text";
        };
        assertEquals(matchCase, "Empty text");
    }
}