package io.ayte.utility.function.api;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

class TernaryFunctionTest {
    @Test
    public void captureAlphaTest() {
        TernaryFunction<Integer, Integer, Integer, Integer> subject = (a, b, c) -> a + b + c;
        assertThat(subject.captureAlpha(1).apply(2, 3), equalTo(6));
    }

    @Test
    public void captureBetaTest() {
        TernaryFunction<Integer, Integer, Integer, Integer> subject = (a, b, c) -> a + b + c;
        assertThat(subject.captureBeta(2).apply(1, 3), equalTo(6));
    }

    @Test
    public void captureGammaTest() {
        TernaryFunction<Integer, Integer, Integer, Integer> subject = (a, b, c) -> a + b + c;
        assertThat(subject.captureGamma(3).apply(1, 2), equalTo(6));
    }

    @Test
    public void captureTest() {
        TernaryFunction<Integer, Integer, Integer, Integer> subject = (a, b, c) -> a + b + c;
        assertThat(subject.capture(1, 2, 3).get(), equalTo(6));
    }

    @Test
    public void andThenTest() {
        TernaryFunction<Integer, Integer, Integer, Integer> subject = (a, b, c) -> a + b + c;
        assertThat(subject.andThen(x -> x * 2).apply(1, 2, 3), equalTo(12));
    }
}
