package io.ayte.utility.function.kit;

import io.ayte.utility.function.kit.unary.optional.Get;
import lombok.val;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.jupiter.api.Assertions.assertThrows;

class OptionalFunctionsTest {
    @Test
    public void testGet() {
        assertThat(OptionalFunctions.get().apply(Optional.of(2)), equalTo(2));
        assertThrows(NoSuchElementException.class, () -> Get.create().apply(Optional.empty()));
    }

    @Test
    public void testOrNull() {
        assertThat(OptionalFunctions.orNull().apply(Optional.of(2)), equalTo(2));
        assertThat(OptionalFunctions.orNull().apply(Optional.empty()), nullValue());
    }

    @Test
    public void testOrElse() {
        assertThat(OptionalFunctions.orElse(2).apply(Optional.of(1)), equalTo(1));
        assertThat(OptionalFunctions.orElse(2).apply(Optional.empty()), equalTo(2));
    }

    @Test
    public void testOrElseGet() {
        assertThat(OptionalFunctions.orElseGet(() -> 2).apply(Optional.of(1)), equalTo(1));
        assertThat(OptionalFunctions.orElseGet(() -> 2).apply(Optional.empty()), equalTo(2));
    }

    @Test
    public void testOr() {
        assertThat(OptionalFunctions.or(Optional.of(2)).apply(Optional.of(1)), equalTo(Optional.of(1)));
        assertThat(OptionalFunctions.or(Optional.of(2)).apply(Optional.empty()), equalTo(Optional.of(2)));
    }

    @Test
    public void testOrSupply() {
        assertThat(OptionalFunctions.orSupply(() -> Optional.of(2)).apply(Optional.of(1)), equalTo(Optional.of(1)));
        assertThat(OptionalFunctions.orSupply(() -> Optional.of(2)).apply(Optional.empty()), equalTo(Optional.of(2)));
    }

    @Test
    public void testMap() {
        assertThat(OptionalFunctions.<Integer, Integer>map(x -> x * 2).apply(Optional.of(1)), equalTo(Optional.of(2)));
    }

    @Test
    public void testFlatMap() {
        val subject = OptionalFunctions.<Integer, Integer>flatMap(x -> Optional.of(x * 2));
        assertThat(subject.apply(Optional.of(1)), equalTo(Optional.of(2)));
    }

    @Test
    public void testFlatten() {
        assertThat(OptionalFunctions.flatten().apply(Optional.of(Optional.of(2))), equalTo(Optional.of(2)));
    }
}
