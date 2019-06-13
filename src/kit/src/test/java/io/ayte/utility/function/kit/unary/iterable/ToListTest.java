package io.ayte.utility.function.kit.unary.iterable;

import lombok.val;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Function;
import java.util.function.Supplier;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SuppressWarnings("unchecked")
class ToListTest {
    @Test
    public void rejectsNullTransformer() {
        assertThrows(NullPointerException.class, () -> ToList.create((Function) null));
    }

    @Test
    public void rejectsNullFactory() {
        assertThrows(NullPointerException.class, () -> ToList.create((Supplier) null));
    }

    @Test
    public void callsDelegates() {
        val transformer = mock(Function.class);
        when(transformer.apply(any())).then(input -> ((Integer) input.getArgument(0)) * 2);
        val factory = mock(Supplier.class);
        when(factory.get()).thenReturn(new ArrayList<>());
        val subject = ToList.create(factory, transformer);
        assertThat(subject.apply(Arrays.asList(1, 2)), equalTo(Arrays.asList(2, 4)));
        verify(factory, times(1)).get();
        verify(transformer, times(1)).apply(1);
        verify(transformer, times(1)).apply(2);
    }
}
