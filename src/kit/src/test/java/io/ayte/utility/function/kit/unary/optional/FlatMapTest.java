package io.ayte.utility.function.kit.unary.optional;

import lombok.val;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FlatMapTest {
    @Test
    public void rejectsNullTransformer() {
        assertThrows(NullPointerException.class, () -> FlatMap.create(null));
    }

    @Test
    public void satisfiesContract() {
        val subject = FlatMap.create((Integer x) -> Optional.of(x * x));
        assertThat(subject.apply(Optional.of(2)), equalTo(Optional.of(4)));
    }
}
