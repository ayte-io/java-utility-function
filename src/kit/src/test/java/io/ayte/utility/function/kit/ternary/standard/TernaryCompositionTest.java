package io.ayte.utility.function.kit.ternary.standard;

import io.ayte.utility.function.api.TernaryFunction;
import lombok.val;
import org.junit.jupiter.api.Test;

import java.util.function.Function;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class TernaryCompositionTest {
    @Test
    public void rejectsNullDelegate() {
        assertThrows(NullPointerException.class, () -> TernaryComposition.create(null, Function.identity()));
    }

    @Test
    public void rejectsNullTransformer() {
        assertThrows(NullPointerException.class, () -> TernaryComposition.create((a, b, c) -> 4, null));
    }

    @SuppressWarnings("unchecked")
    @Test
    public void callsProvidedFunctions() {
        val delegate = mock(TernaryFunction.class);
        when(delegate.apply(1, 2, 3)).thenReturn(4);
        val transformer = mock(Function.class);
        when(transformer.apply(4)).thenReturn(5);
        val subject = TernaryComposition.create(delegate, transformer);
        assertThat(subject.apply(1, 2, 3), equalTo(5));
        verify(delegate, times(1)).apply(1, 2, 3);
        verify(transformer, times(1)).apply(4);
    }

    @Test
    public void acceptsNullValues() {
        val subject = TernaryComposition.create((a, b, c) -> null, any -> null);
        assertDoesNotThrow(() -> subject.apply(null, null, null));
    }
}
