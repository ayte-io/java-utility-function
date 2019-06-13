package io.ayte.utility.function.kit.binary.operator.standard;

import io.ayte.utility.function.kit.binary.operator.standard.UsingSecond;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class UsingSecondTest {
    @Test
    public void acceptsNullValues() {
        assertDoesNotThrow(() -> UsingSecond.create().apply(null, null));
    }

    @Test
    public void returnsSecondArgument() {
        assertThat(UsingSecond.create().apply(1, 2), equalTo(2));
    }
}
