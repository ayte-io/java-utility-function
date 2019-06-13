package io.ayte.utility.function.kit.binary.capture;

import lombok.val;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.function.BiFunction;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class AllArgumentsCapturedBinaryFunctionTest {
    @SuppressWarnings("unchecked")
    @Test
    public void worksCorrectly() {
        val delegate = Mockito.mock(BiFunction.class);
        when(delegate.apply(1, 2)).thenReturn(3);
        val subject = AllArgumentsCapturedBinaryFunction.create(delegate, 1, 2);
        assertThat(subject.get(), equalTo(3));
        verify(delegate, times(1)).apply(1, 2);
    }

    @Test
    public void doesNotAcceptNullDelegate() {
        assertThrows(NullPointerException.class, () -> AllArgumentsCapturedBinaryFunction.create(null, 1, 2));
    }

    @Test
    public void acceptsNullParameters() {
        val value = new Object();
        val subject = AllArgumentsCapturedBinaryFunction.create((a, b) -> value, null, null);
        assertThat(subject.get(), is(value));
    }
}
