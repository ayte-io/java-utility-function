package io.ayte.utility.function.api;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

class UnaryFunctionTest {
    @Test
    public void captureTest() {
        UnaryFunction<String, Integer> subject = Integer::valueOf;
        assertThat(subject.capture("10").get(), equalTo(10));
    }
}
