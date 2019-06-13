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

class BetaCapturedBinaryFunctionTest {
    @Test
    public void rejectsNullDelegate() {
        assertThrows(NullPointerException.class, () -> BetaCapturedBinaryFunction.create(null, 1));
    }

    @Test
    public void acceptsNullValues() {
        val subject = BetaCapturedBinaryFunction.create((a, b) -> 3, null);
        assertThat(subject.apply(null), equalTo(3));
    }

    @SuppressWarnings("unchecked")
    @Test
    public void callsDelegate() {
        val delegate = mock(BiFunction.class);
        when(delegate.apply(1, 2)).thenReturn(3);
        val subject = BetaCapturedBinaryFunction.create(delegate, 2);
        assertThat(subject.apply(1), equalTo(3));
        verify(delegate, times(1)).apply(1, 2);
    }
}
