package io.ayte.utility.function.kit.binary.operator.standard;

import lombok.val;
import org.junit.jupiter.api.Test;

import java.util.function.BiFunction;
import java.util.function.BinaryOperator;

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
        BinaryOperator<Object> operator = (a, b) -> a;
        val wrapped = FunctionToOperatorWrapper.create(operator);
        assertThat(wrapped, is(operator));
    }

    @Test
    public void wrapsFunction() {
        BiFunction<Object, Object, Object> function = (a, b) -> a;
        val wrapped = FunctionToOperatorWrapper.create(function);
        assertThat(wrapped, not(is(function)));
    }

    @SuppressWarnings("unchecked")
    @Test
    public void callsDelegate() {
        val delegate = mock(BiFunction.class);
        when(delegate.apply(1, 2)).thenReturn(3);
        val wrapped = FunctionToOperatorWrapper.create(delegate);
        assertThat(wrapped.apply(1, 2), equalTo(3));
        verify(delegate, times(1)).apply(1, 2);
    }
}
