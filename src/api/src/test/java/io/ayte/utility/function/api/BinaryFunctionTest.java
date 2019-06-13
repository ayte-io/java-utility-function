package io.ayte.utility.function.api;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

class BinaryFunctionTest {
    @Test
    public void captureTest() {
        BinaryFunction<String, Integer, Integer> implementation = Integer::valueOf;
        assertThat(implementation.capture("12", 8).get(), equalTo(10));
    }

    @Test
    public void captureAlphaTest() {
        BinaryFunction<String, Integer, Integer> implementation = Integer::valueOf;
        assertThat(implementation.captureAlpha("12").apply(8), equalTo(10));
    }

    @Test
    public void captureBetaTest() {
        BinaryFunction<String, Integer, Integer> implementation = Integer::valueOf;
        assertThat(implementation.captureBeta(8).apply("12"), equalTo(10));
    }
}
