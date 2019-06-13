package io.ayte.utility.function.kit.unary.operator.standard;

import lombok.val;
import org.junit.jupiter.api.Test;

import java.util.function.Function;
import java.util.function.UnaryOperator;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class FunctionToOperatorWrapperTest {
    @Test
    public void rejectsNullDelegate() {
        assertThrows(NullPointerException.class, () -> FunctionToOperatorWrapper.create(null));
    }

    @Test
    public void passesOperatorThrough() {
        UnaryOperator<Integer> subject = x -> x;
        val wrapped = FunctionToOperatorWrapper.create(subject);
        assertThat(wrapped, is(subject));
    }

    @Test
    public void wrapsFunction() {
        Function<Integer, Integer> subject = x -> x;
        val wrapped = FunctionToOperatorWrapper.create(subject);
        assertThat(wrapped, not(is(subject)));
    }

    @SuppressWarnings("unchecked")
    @Test
    public void callsDelegate() {
        val subject = mock(Function.class);
        when(subject.apply(1)).thenReturn(1);
        val wrapped = FunctionToOperatorWrapper.create(subject);
        assertThat(wrapped.apply(1), equalTo(1));
        verify(subject, times(1)).apply(1);
    }
}
