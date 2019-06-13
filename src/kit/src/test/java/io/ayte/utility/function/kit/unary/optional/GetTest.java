package io.ayte.utility.function.kit.unary.optional;

import lombok.val;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

class GetTest {
    @Test
    public void satisfiesContract() {
        val subject = Get.<Integer>create();
        assertThat(subject.apply(Optional.of(2)), equalTo(2));
    }
}
