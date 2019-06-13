package io.ayte.utility.function.kit.unary.iterable;

import lombok.val;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.function.Function;
import java.util.function.Supplier;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SuppressWarnings("unchecked")
class ToSetTest {
    @Test
    public void rejectsNullDelegates() {
        assertThrows(NullPointerException.class, () -> ToSet.create((Supplier) null));
        assertThrows(NullPointerException.class, () -> ToSet.create((Function) null));
    }

    @Test
    public void callsDelegates() {
        val factory = mock(Supplier.class);
        when(factory.get()).thenReturn(new HashSet<>());
        val transformer = mock(Function.class);
        when(transformer.apply(1)).thenReturn(2);
        when(transformer.apply(2)).thenReturn(4);
        when(transformer.apply(3)).thenReturn(4);
        val subject = ToSet.create(factory, transformer);
        assertThat(subject.apply(Arrays.asList(1, 2, 3)), equalTo(new HashSet(Arrays.asList(2, 4))));
        verify(factory, times(1)).get();
        verify(transformer, times(1)).apply(1);
        verify(transformer, times(1)).apply(2);
        verify(transformer, times(1)).apply(3);
    }
}
