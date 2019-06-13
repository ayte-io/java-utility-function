package io.ayte.utility.function.kit.ternary.operator.comparing;

import lombok.val;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MinTest {
    @Test
    public void rejectsNullComparator() {
        assertThrows(NullPointerException.class, () -> Min.create(null));
    }

    @Test
    public void satisfiesContract() {
        val subject = Min.create(Integer::compareTo);
        assertThat(subject.apply(3, 2, 1), equalTo(1));
        assertThat(subject.apply(2, 1, 3), equalTo(1));
        assertThat(subject.apply(1, 3, 2), equalTo(1));
        assertThat(subject.apply(2, 3, 1), equalTo(1));
    }

    @Test
    public void usesNaturalComparator() {
        val subject = Min.<Integer>create();
        assertThat(subject.apply(1, 2, 3), equalTo(1));
    }
}
