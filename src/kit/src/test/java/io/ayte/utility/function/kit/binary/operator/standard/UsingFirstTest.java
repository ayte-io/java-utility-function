package io.ayte.utility.function.kit.binary.operator.standard;

import io.ayte.utility.function.kit.binary.operator.standard.UsingFirst;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class UsingFirstTest {
    @Test
    public void acceptsNullValues() {
        assertDoesNotThrow(() -> UsingFirst.create().apply(null, null));
    }

    @Test
    public void returnsFirstArgument() {
        assertThat(UsingFirst.create().apply(1, 2), equalTo(1));
    }
}
