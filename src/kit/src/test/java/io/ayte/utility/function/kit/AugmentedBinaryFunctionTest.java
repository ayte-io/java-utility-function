package io.ayte.utility.function.kit;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;

class AugmentedBinaryFunctionTest {
    @Test
    public void captureAlphaTest() {
        AugmentedBinaryFunction<String, String, Integer> subject = String::indexOf;
        assertThat(subject.captureAlpha("source").apply("ource"), equalTo(1));
    }

    @Test
    public void captureBetaTest() {
        AugmentedBinaryFunction<String, String, Integer> subject = String::indexOf;
        assertThat(subject.captureBeta("ource").apply("source"), equalTo(1));
    }

    @Test
    public void captureTest() {
        AugmentedBinaryFunction<String, String, Integer> subject = String::indexOf;
        assertThat(subject.capture("source", "ource").get(), equalTo(1));
    }

    @Test
    public void andThenTest() {
        AugmentedBinaryFunction<String, String, Integer> subject = String::indexOf;
        assertThat(subject.andThen(x -> x * 2).apply("source", "ource"), equalTo(2));
    }
}
