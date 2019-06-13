package io.ayte.utility.function.kit.unary.iterable;

import io.ayte.utility.function.api.TernaryFunction;
import io.ayte.utility.function.kit.ternary.operator.standard.UsingThird;
import io.ayte.utility.supplier.kit.Factories;
import lombok.val;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.function.Function;
import java.util.function.Supplier;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ToMapTest {
    @Test
    public void rejectsNullDelegates() {
        assertThrows(
                NullPointerException.class,
                () -> ToMap.create(null, Function.identity(), Function.identity(), UsingThird.create())
        );
        assertThrows(
                NullPointerException.class,
                () -> ToMap.create(Factories.hashMap(), null, Function.identity(), UsingThird.create())
        );

        assertThrows(
                NullPointerException.class,
                () -> ToMap.create(Factories.hashMap(), Function.identity(), null, UsingThird.create())
        );

        assertThrows(
                NullPointerException.class,
                () -> ToMap.create(Factories.hashMap(), Function.identity(), Function.identity(), null)
        );

    }

    @SuppressWarnings("unchecked")
    @Test
    public void callsDelegates() {
        val factory = mock(Supplier.class);
        when(factory.get()).thenReturn(new HashMap<>());
        val keyExtractor = mock(Function.class);
        when(keyExtractor.apply(1)).thenReturn(0);
        when(keyExtractor.apply(2)).thenReturn(0);
        val valueExtractor = mock(Function.class);
        when(valueExtractor.apply(1)).thenReturn(2);
        when(valueExtractor.apply(2)).thenReturn(4);
        val combiner = mock(TernaryFunction.class);
        when(combiner.apply(0, 2, 4)).thenReturn(6);
        val subject = ToMap.create(factory, keyExtractor, valueExtractor, combiner);
        assertThat(subject.apply(Arrays.asList(1, 2)), equalTo(Collections.singletonMap(0, 6)));
        verify(factory, times(1)).get();
        verify(keyExtractor, times(1)).apply(1);
        verify(keyExtractor, times(1)).apply(2);
        verify(valueExtractor, times(1)).apply(1);
        verify(valueExtractor, times(1)).apply(2);
        verify(combiner, times(1)).apply(0, 2, 4);
    }

    @Test
    public void defaultCombinerThrowsOnRepeatingKeys() {
        assertThrows(IllegalArgumentException.class, () -> {
            val subject = ToMap.create(Function.identity(), Function.identity());
            subject.apply(Arrays.asList(1, 1));
        });
    }
}
