package io.ayte.utility.function.kit.unary.optional;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;

class OrElseTest {
    @Test
    public void acceptsNullValue() {
        assertDoesNotThrow(() -> OrElse.create(null));
    }

    @Test
    public void returnsOriginalValue() {
        assertThat(OrElse.create(3).apply(Optional.of(2)), equalTo(2));
    }

    @Test
    public void substitutesEmptyValue() {
        assertThat(OrElse.create(3).apply(Optional.empty()), equalTo(3));
    }
}
