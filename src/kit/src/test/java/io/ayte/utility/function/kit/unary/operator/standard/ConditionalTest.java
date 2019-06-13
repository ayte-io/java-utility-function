package io.ayte.utility.function.kit.unary.operator.standard;

import lombok.val;
import org.junit.jupiter.api.Test;

import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ConditionalTest {
    @Test
    public void rejectsNullCondition() {
        assertThrows(NullPointerException.class, () -> Conditional.create(null, x -> x));
        assertThrows(NullPointerException.class, () -> Conditional.create(null, () -> null));
    }

    @Test
    public void rejectsNullOperator() {
        assertThrows(NullPointerException.class, () -> Conditional.create(any -> true, (UnaryOperator<Object>) null));
        assertThrows(NullPointerException.class, () -> Conditional.create(any -> true, (Supplier<Object>) null));
    }

    @SuppressWarnings("unchecked")
    @Test
    public void satisfiesContract() {
        val condition = mock(Predicate.class);
        when(condition.test(1)).thenReturn(false);
        when(condition.test(2)).thenReturn(true);
        val operator = mock(UnaryOperator.class);
        when(operator.apply(2)).thenReturn(-1);
        val subject = Conditional.create(condition, operator);
        assertThat(subject.apply(1), equalTo(1));
        assertThat(subject.apply(2), equalTo(-1));
        verify(condition, times(1)).test(1);
        verify(condition, times(1)).test(2);
        verify(operator, times(1)).apply(2);
    }

    @Test
    public void acceptsSupplier() {
        val subject = Conditional.<Integer>create(x -> x % 2 == 0, () -> 0);
        assertThat(subject.apply(1), equalTo(1));
        assertThat(subject.apply(2), equalTo(0));
    }
}
