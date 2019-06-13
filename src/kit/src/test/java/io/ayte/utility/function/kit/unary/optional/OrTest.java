package io.ayte.utility.function.kit.unary.optional;

import lombok.val;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;

class OrTest {
    @SuppressWarnings("OptionalAssignedToNull")
    @Test
    public void rejectsNullSubstitute() {
        assertThrows(NullPointerException.class, () -> Or.create(null));
    }

    @Test
    public void returnsSourceOnNonEmptyOptional() {
        val subject = Or.create(Optional.of(2));
        assertThat(subject.apply(Optional.of(3)), equalTo(Optional.of(3)));
    }

    @Test
    public void returnsSubstituteOnEmptyOptional() {
        val subject = Or.create(Optional.of(2));
        assertThat(subject.apply(Optional.empty()), equalTo(Optional.of(2)));
    }
}
