package io.ayte.utility.function.kit.ternary.operator.standard;

import io.ayte.utility.function.kit.ternary.operator.standard.UsingThird;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class UsingThirdTest {
    @Test
    public void satisfiesContract() {
        assertThat(UsingThird.create().apply(1, 2, 3), equalTo(3));
    }

    @Test
    public void acceptsNullValues() {
        assertDoesNotThrow(() -> UsingThird.create().apply(null, null, null));
    }
}
