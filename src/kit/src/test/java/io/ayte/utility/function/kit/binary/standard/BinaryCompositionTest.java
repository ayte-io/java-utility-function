package io.ayte.utility.function.kit.binary.standard;

import lombok.val;
import org.junit.jupiter.api.Test;

import java.util.function.BiFunction;
import java.util.function.Function;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class BinaryCompositionTest {
    @Test
    public void rejectsNullDelegate() {
        assertThrows(NullPointerException.class, () -> BinaryComposition.create(null, any -> null));
    }

    @Test
    public void rejectsNullTransformer() {
        assertThrows(NullPointerException.class, () -> BinaryComposition.create((a, b) -> null, null));
    }

    @Test
    public void acceptsNullValues() {
        val subject = BinaryComposition.create((a, b) -> null, any -> 4);
        assertThat(subject.apply(null, null), equalTo(4));
    }

    @SuppressWarnings("unchecked")
    @Test
    public void callsDelegates() {
        val delegate = mock(BiFunction.class);
        when(delegate.apply(1, 2)).thenReturn(3);
        val transformer = mock(Function.class);
        when(transformer.apply(3)).thenReturn(4);
        val subject = BinaryComposition.create(delegate, transformer);
        assertThat(subject.apply(1, 2), equalTo(4));
        verify(delegate, times(1)).apply(1, 2);
        verify(transformer, times(1)).apply(3);
    }
}
