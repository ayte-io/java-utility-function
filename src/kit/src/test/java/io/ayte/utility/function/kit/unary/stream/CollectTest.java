package io.ayte.utility.function.kit.unary.stream;

import lombok.val;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CollectTest {
    @Test
    public void rejectsNullComponents() {
        assertThrows(NullPointerException.class, () -> Collect.create(null, a -> null, Collectors.toList()));
        assertThrows(NullPointerException.class, () -> Collect.create(any -> false, null, Collectors.toList()));
        assertThrows(NullPointerException.class, () -> Collect.create(any -> false, a -> null, null));
    }

    @Test
    public void filtersOutItems() {
        val subject = Collect.<Integer, List<Integer>>create(any -> any % 2 != 0, Collectors.toList());
        assertThat(subject.apply(Stream.of(1, 2, 3)), equalTo(Arrays.asList(1, 3)));
    }

    @Test
    public void transformsItems() {
        val subject = Collect.<Integer, Integer, List<Integer>>create((Integer any) -> any * 2, Collectors.toList());
        assertThat(subject.apply(Stream.of(1, 2, 3)), equalTo(Arrays.asList(2, 4, 6)));
    }

    @SuppressWarnings("unchecked")
    @Test
    public void callsDelegates() {
        val filter = mock(Predicate.class);
        when(filter.test(any())).thenReturn(true);
        when(filter.test(2)).thenReturn(false);
        val transformer = mock(Function.class);
        when(transformer.apply(any()))
                .then(input -> ((Integer) input.getArgument(0)) * 2);
        val subject = Collect.create(filter, transformer, Collectors.toList());
        assertThat(subject.apply(Stream.of(1, 2, 3)), equalTo(Arrays.asList(2, 6)));
        verify(filter, times(1)).test(1);
        verify(filter, times(1)).test(2);
        verify(filter, times(1)).test(3);
        verify(transformer, times(1)).apply(1);
        verify(transformer, times(1)).apply(3);
    }

    @Test
    public void acceptsSingleCollector() {
        val subject = Collect.<Integer, List<Integer>>create(Collectors.toList());
        assertThat(subject.apply(Stream.of(1, 2, 3)), equalTo(Arrays.asList(1, 2, 3)));
    }
}
