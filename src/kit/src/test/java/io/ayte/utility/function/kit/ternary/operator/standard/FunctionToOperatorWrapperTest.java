package io.ayte.utility.function.kit.ternary.operator.standard;

import io.ayte.utility.function.api.TernaryFunction;
import io.ayte.utility.function.api.TernaryOperator;
import lombok.val;
import org.junit.jupiter.api.Test;

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
    public void rejectsNullSubject() {
        assertThrows(NullPointerException.class, () -> FunctionToOperatorWrapper.create(null));
    }

    @Test
    public void passesTernaryOperatorThrough() {
        TernaryOperator<Object> subject = (a, b, c) -> a;
        val wrapped = FunctionToOperatorWrapper.create(subject);
        assertThat(wrapped, is(subject));
    }

    @Test
    public void wrapsTernaryFunction() {
        TernaryFunction<Object, Object, Object, Object> subject = (a, b, c) -> a;
        val wrapped = FunctionToOperatorWrapper.create(subject);
        assertThat(wrapped, not(is(subject)));
    }

    @SuppressWarnings("unchecked")
    @Test
    public void callsSubject() {
        val subject = mock(TernaryFunction.class);
        when(subject.apply(1, 2, 3)).thenReturn(1);
        val wrapped = FunctionToOperatorWrapper.create(subject);
        assertThat(wrapped.apply(1, 2, 3), equalTo(1));
        verify(subject, times(1)).apply(1, 2, 3);
    }
}
