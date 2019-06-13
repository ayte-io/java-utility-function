package io.ayte.utility.function.kit;

import lombok.val;
import org.junit.jupiter.api.Test;

import java.util.function.Function;
import java.util.function.UnaryOperator;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;

class OperatorsTest {
    @Test
    public void testIdentity() {
        assertThat(Operators.identity().apply(1), equalTo(1));
    }

    @Test
    public void testConditionalOperator() {
        val subject = Operators.<Integer>conditional(x -> x % 2 == 0, x -> x * 2);
        assertThat(subject.apply(1), equalTo(1));
        assertThat(subject.apply(2), equalTo(4));
    }

    @Test
    public void testConditionalSupplier() {
        val subject = Operators.<Integer>conditional(x -> x % 2 == 0, () -> 0);
        assertThat(subject.apply(1), equalTo(1));
        assertThat(subject.apply(2), equalTo(0));
    }

    @Test
    public void testSupplierWrapper() {
        val subject = Operators.create(() -> 0);
        assertThat(subject.apply(12), equalTo(0));
    }

    @Test
    public void testAugment() {
        UnaryOperator<Object> operator = x -> x;
        val augmented = Operators.augment(operator);
        assertThat(augmented.capture(3).get(), equalTo(3));
    }

    @Test
    public void testCreateFromFunction() {
        val operator = Operators.create(Function.identity());
        assertThat(operator, instanceOf(UnaryOperator.class));
    }
}
