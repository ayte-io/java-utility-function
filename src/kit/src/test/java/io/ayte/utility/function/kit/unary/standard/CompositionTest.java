package io.ayte.utility.function.kit.unary.standard;

import lombok.val;
import org.junit.jupiter.api.Test;

import java.util.function.Function;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CompositionTest {
    @Test
    public void rejectsNullDelegate() {
        assertThrows(NullPointerException.class, () -> Composition.create(null, a -> null));
    }

    @Test
    public void rejectsNullTransformer() {
        assertThrows(NullPointerException.class, () -> Composition.create(a -> null, null));
    }

    @SuppressWarnings("unchecked")
    @Test
    public void appliesPassedFunctions() {
        val delegate = mock(Function.class);
        when(delegate.apply(1)).thenReturn(2);
        val transformer = mock(Function.class);
        when(transformer.apply(2)).thenReturn(3);
        val subject = Composition.create(delegate, transformer);
        assertThat(subject.apply(1), equalTo(3));
    }

    @Test
    public void acceptsNullValue() {
        assertDoesNotThrow(() -> Composition.create(a -> null, a -> null).apply(null));
    }
}
