package io.ayte.utility.function.kit;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

class AugmentedFunctionTest {
    @Test
    public void composeTest() {
        AugmentedFunction<String, Integer> subject = Integer::valueOf;
        assertThat(subject.<String>compose(input -> '1' + input).apply("1"), equalTo(11));
    }

    @Test
    public void andThenTest() {
        AugmentedFunction<String, Integer> subject = Integer::valueOf;
        assertThat(subject.andThen(x -> x * 2).apply("1"), equalTo(2));
    }
}
