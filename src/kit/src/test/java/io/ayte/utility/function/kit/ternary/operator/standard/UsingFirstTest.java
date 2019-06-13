package io.ayte.utility.function.kit.ternary.operator.standard;

import io.ayte.utility.function.kit.ternary.operator.standard.UsingFirst;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;

class UsingFirstTest {
    @Test
    public void satisfiesContract() {
        assertThat(UsingFirst.create().apply(1, 2, 3), equalTo(1));
    }

    @Test
    public void acceptsNullValues() {
        assertDoesNotThrow(() -> UsingFirst.create().apply(null, null, null));
    }
}
