package io.ayte.utility.function.kit.ternary.standard;

import io.ayte.utility.function.api.TernaryFunction;
import io.ayte.utility.function.kit.AugmentedTernaryFunction;
import lombok.val;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class AugmentationFunctionWrapperTest {
    @Test
    public void rejectsNullDelegate() {
        assertThrows(NullPointerException.class, () -> AugmentationFunctionWrapper.create(null));
    }

    @Test
    public void wrapsTernaryFunction() {
        TernaryFunction<Long, Long, Long, Long> delegate = (a, b, c) -> a + b + c;
        val subject = AugmentationFunctionWrapper.create(delegate);
        assertThat(subject, not(is(delegate)));
    }

    @Test
    public void passesThroughAugmentedFunction() {
        AugmentedTernaryFunction<Long, Long, Long, Long> delegate = (a, b, c) -> a + b + c;
        val subject = AugmentationFunctionWrapper.create(delegate);
        assertThat(subject, is(delegate));
    }

    @SuppressWarnings("unchecked")
    @Test
    public void callsDelegate() {
        val delegate = mock(TernaryFunction.class);
        when(delegate.apply(1, 1, 1)).thenReturn(1);
        val subject = AugmentationFunctionWrapper.create(delegate);
        assertThat(subject.apply(1, 1, 1), equalTo(1));
        verify(delegate, times(1)).apply(1, 1, 1);
    }
}
