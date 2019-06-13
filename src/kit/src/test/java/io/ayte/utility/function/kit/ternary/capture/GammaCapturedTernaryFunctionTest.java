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

class GammaCapturedTernaryFunctionTest {
    @Test
    public void rejectsNullDelegate() {
        assertThrows(NullPointerException.class, () -> GammaCapturedTernaryFunction.create(null, 3));
    }

    @Test
    public void acceptsNullValues() {
        val subject = GammaCapturedTernaryFunction.create((a, b, c) -> 4, null);
        assertThat(subject.apply(null, null), equalTo(4));
    }

    @SuppressWarnings("unchecked")
    @Test
    public void callsDelegate() {
        val delegate = mock(TernaryFunction.class);
        when(delegate.apply(1, 2, 3)).thenReturn(4);
        val subject = GammaCapturedTernaryFunction.create(delegate, 3);
        assertThat(subject.apply(1, 2), equalTo(4));
        verify(delegate, times(1)).apply(1, 2, 3);
    }
}
