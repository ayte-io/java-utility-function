package io.ayte.utility.function.kit.unary.standard;

import io.ayte.utility.function.kit.unary.operator.standard.Identity;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.nullValue;

class IdentityTest {
    @Test
    public void returnsPassedValue() {
        assertThat(Identity.create().apply(1), equalTo(1));
    }

    @Test
    public void acceptsNull() {
        assertThat(Identity.create().apply(null), nullValue());
    }
}
