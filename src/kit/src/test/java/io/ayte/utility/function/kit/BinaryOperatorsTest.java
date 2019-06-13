package io.ayte.utility.function.kit;

import lombok.val;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Supplier;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;

class BinaryOperatorsTest {
    @Test
    public void minTest() {
        assertThat(BinaryOperators.<Integer>min().apply(1, 2), equalTo(1));
        assertThat(BinaryOperators.<String>min(Comparator.reverseOrder()).apply("a", "b"), equalTo("b"));
    }

    @Test
    public void maxTest() {
        assertThat(BinaryOperators.<Integer>max().apply(1, 2), equalTo(2));
        assertThat(BinaryOperators.<String>max(Comparator.reverseOrder()).apply("a", "b"), equalTo("a"));
    }

    @Test
    public void usingFirstTest() {
        assertThat(BinaryOperators.first().apply(1, 2), equalTo(1));
    }

    @Test
    public void usingSecondTest() {
        assertThat(BinaryOperators.second().apply(1, 2), equalTo(2));
    }

    @Test
    public void testAugment() {
        val augmented = BinaryOperators.augment(Long::sum);
        assertThat(augmented.capture(2L).apply(3L), equalTo(5L));
    }

    @Test
    public void testCreateFromFunction() {
        BiFunction<Long, Long, Long> function = Long::sum;
        assertThat(BinaryOperators.create(function), instanceOf(BinaryOperator.class));
    }

    @Test
    public void testCreateFromSupplier() {
        Supplier<Long> supplier = () -> 12L;
        assertThat(BinaryOperators.create(supplier), instanceOf(BinaryOperator.class));
    }
}
