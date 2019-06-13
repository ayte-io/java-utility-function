package io.ayte.utility.function.kit;

import io.ayte.utility.function.api.TernaryFunction;
import io.ayte.utility.function.api.TernaryOperator;
import lombok.val;
import org.junit.jupiter.api.Test;

import java.util.function.Supplier;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;

class TernaryOperatorsTest {
    @Test
    public void testFirst() {
        assertThat(TernaryOperators.first().apply(1, 2, 3), equalTo(1));
    }

    @Test
    public void testSecond() {
        assertThat(TernaryOperators.second().apply(1, 2, 3), equalTo(2));
    }

    @Test
    public void testThird() {
        assertThat(TernaryOperators.third().apply(1, 2, 3), equalTo(3));
    }

    @Test
    public void testMin() {
        assertThat(TernaryOperators.<Integer>min().apply(1, 2, 3), equalTo(1));
        assertThat(TernaryOperators.<Integer>min().apply(2, 3, 1), equalTo(1));
        assertThat(TernaryOperators.<Integer>min().apply(3, 1, 2), equalTo(1));

        assertThat(TernaryOperators.min(Integer::compare).apply(1, 2, 3), equalTo(1));
        assertThat(TernaryOperators.min(Integer::compare).apply(2, 3, 1), equalTo(1));
        assertThat(TernaryOperators.min(Integer::compare).apply(3, 1, 2), equalTo(1));
    }

    @Test
    public void testMax() {
        assertThat(TernaryOperators.<Integer>max().apply(1, 2, 3), equalTo(3));
        assertThat(TernaryOperators.<Integer>max().apply(2, 3, 1), equalTo(3));
        assertThat(TernaryOperators.<Integer>max().apply(3, 1, 2), equalTo(3));

        assertThat(TernaryOperators.max(Integer::compare).apply(1, 2, 3), equalTo(3));
        assertThat(TernaryOperators.max(Integer::compare).apply(2, 3, 1), equalTo(3));
        assertThat(TernaryOperators.max(Integer::compare).apply(3, 1, 2), equalTo(3));
    }

    @Test
    public void testAugment() {
        val augmented = TernaryOperators.augment((Long a, Long b, Long c) -> a + b + c);
        assertThat(augmented.capture(1L, 2L).apply(3L), equalTo(6L));
    }

    @Test
    public void testCreateFromFunction() {
        TernaryFunction<Long, Long, Long, Long> fn = (a, b, c) -> a + b + c;
        assertThat(TernaryOperators.create(fn), instanceOf(TernaryOperator.class));
    }

    @Test
    public void testCreateFromSupplier() {
        Supplier<Long> supplier = () -> 12L;
        assertThat(TernaryOperators.create(supplier), instanceOf(TernaryOperator.class));
    }
}
