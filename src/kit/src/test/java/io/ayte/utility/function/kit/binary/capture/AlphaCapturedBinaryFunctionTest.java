package io.ayte.utility.function.kit.binary.capture;

import lombok.val;
import org.junit.jupiter.api.Test;

import java.util.function.BiFunction;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class AlphaCapturedBinaryFunctionTest {
    @Test
    public void rejectsNullDelegate() {
        assertThrows(NullPointerException.class, () -> AlphaCapturedBinaryFunction.create(null, 1));
    }

    @Test
    public void acceptsNullValues() {
        val subject = AlphaCapturedBinaryFunction.create((a, b) -> 3, null);
        assertThat(subject.apply(null), equalTo(3));
    }

    @SuppressWarnings("unchecked")
    @Test
    public void invokesDelegate() {
        val delegate = mock(BiFunction.class);
        when(delegate.apply(1, 2)).thenReturn(3);
        val subject = AlphaCapturedBinaryFunction.create(delegate, 1);
        assertThat(subject.apply(2), equalTo(3));
        verify(delegate, times(1)).apply(1, 2);
    }
}
