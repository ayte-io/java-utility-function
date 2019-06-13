package io.ayte.utility.function.kit.unary.optional;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.nullValue;

class OrNullTest {
    @Test
    public void returnsNullOnEmptyValue() {
        assertThat(OrNull.create().apply(Optional.empty()), nullValue());
    }

    @Test
    public void respectsPresentValue() {
        assertThat(OrNull.create().apply(Optional.of(3)), equalTo(3));
    }
}
