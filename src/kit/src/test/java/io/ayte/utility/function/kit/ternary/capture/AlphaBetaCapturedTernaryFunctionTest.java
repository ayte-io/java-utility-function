package io.ayte.utility.function.kit.ternary.capture;

import io.ayte.utility.function.api.TernaryFunction;
import lombok.val;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class AlphaBetaCapturedTernaryFunctionTest {
    @Test
    public void rejectsNullDelegate() {
        assertThrows(NullPointerException.class, () -> AlphaBetaCapturedTernaryFunction.create(null, 1, 2));
    }

    @Test
    public void acceptsNullValues() {
        val subject = AlphaBetaCapturedTernaryFunction.create((a, b, c) -> 4, null, null);
        assertThat(subject.apply(null), equalTo(4));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void callsDelegate() {
        val delegate = mock(TernaryFunction.class);
        when(delegate.apply(1, 2, 3)).thenReturn(4);
        val subject = AlphaBetaCapturedTernaryFunction.create(delegate, 1, 2);
        assertThat(subject.apply(3), equalTo(4));
        verify(delegate, times(1)).apply(1, 2, 3);
    }
}
