package io.ayte.utility.function.kit;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;

class AugmentedTernaryFunctionTest {
    @Test
    public void testAndThen() {
        AugmentedTernaryFunction<Integer, Integer, Integer, Integer> subject = (a, b, c) -> a + (b * c);
        assertThat(subject.andThen(x -> x * 2).apply(1, 2, 3), equalTo(14));
    }

    @Test
    public void testCapture() {
        AugmentedTernaryFunction<Integer, Integer, Integer, Integer> subject = (a, b, c) -> a + (b * c);
        assertThat(subject.capture(1, 2, 3).get(), equalTo(7));
    }

    @Test
    public void testCaptureAlphaBeta() {
        AugmentedTernaryFunction<Integer, Integer, Integer, Integer> subject = (a, b, c) -> a + (b * c);
        assertThat(subject.capture(1, 2).apply(3), equalTo(7));
    }

    @Test
    public void testCaptureAlpha() {
        AugmentedTernaryFunction<Integer, Integer, Integer, Integer> subject = (a, b, c) -> a + (b * c);
        assertThat(subject.captureAlpha(1).apply(2, 3), equalTo(7));
    }

    @Test
    public void testCaptureBeta() {
        AugmentedTernaryFunction<Integer, Integer, Integer, Integer> subject = (a, b, c) -> a + (b * c);
        assertThat(subject.captureBeta(2).apply(1, 3), equalTo(7));
    }

    @Test
    public void testCaptureGamma() {
        AugmentedTernaryFunction<Integer, Integer, Integer, Integer> subject = (a, b, c) -> a + (b * c);
        assertThat(subject.captureGamma(3).apply(1, 2), equalTo(7));
    }
}
