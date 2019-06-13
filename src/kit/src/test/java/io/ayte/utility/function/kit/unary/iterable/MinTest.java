package io.ayte.utility.function.kit.unary.iterable;

import lombok.val;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MinTest {
    @Test
    public void rejectsNullComparator() {
        assertThrows(NullPointerException.class, () -> Min.create(null));
    }

    @Test
    public void returnsNullOnEmptyIterable() {
        assertThat(Min.create().apply(Collections.emptyList()), nullValue());
    }

    @SuppressWarnings({"unchecked", "ResultOfMethodCallIgnored"})
    @Test
    public void selectsMinValue() {
        val comparator = mock(Comparator.class);
        when(comparator.compare(any(), any()))
                .then(input -> Integer.compare(input.getArgument(0), input.getArgument(1)));
        val subject = Min.<Integer>create(comparator);
        assertThat(subject.apply(Arrays.asList(2, 1, 3, 5, 4)), equalTo(1));
        verify(comparator, times(1)).compare(2, 1);
        verify(comparator, times(1)).compare(1, 3);
        verify(comparator, times(1)).compare(1, 5);
        verify(comparator, times(1)).compare(1, 4);
    }
}
