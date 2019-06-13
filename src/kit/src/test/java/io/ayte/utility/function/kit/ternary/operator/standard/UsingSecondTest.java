package io.ayte.utility.function.kit.ternary.operator.standard;

import io.ayte.utility.function.kit.ternary.operator.standard.UsingSecond;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class UsingSecondTest {
    @Test
    public void satisfiesContract() {
        assertThat(UsingSecond.create().apply(1, 2, 3), equalTo(2));
    }

    @Test
    public void acceptsNullValues() {
        assertDoesNotThrow(() -> UsingSecond.create().apply(null, null, null));
    }
}
