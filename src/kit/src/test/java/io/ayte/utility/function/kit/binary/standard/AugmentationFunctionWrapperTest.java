package io.ayte.utility.function.kit.binary.standard;

import io.ayte.utility.function.kit.AugmentedBinaryFunction;
import lombok.val;
import org.junit.jupiter.api.Test;

import java.util.function.BiFunction;

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
    public void passesThroughAugmentedFunction() {
        AugmentedBinaryFunction<Object, Object, Object> subject = (a, b) -> a;
        val wrapped = AugmentationFunctionWrapper.create(subject);
        assertThat(wrapped, is(subject));
    }

    @Test
    public void wrapsRegularFunction() {
        BiFunction<Object, Object, Object> subject = (a, b) -> a;
        val wrapped = AugmentationFunctionWrapper.create(subject);
        assertThat(wrapped, not(is(subject)));
    }

    @SuppressWarnings("unchecked")
    @Test
    public void callsDelegate() {
        val subject = mock(BiFunction.class);
        when(subject.apply(1, 2)).thenReturn(3);
        val wrapped = AugmentationFunctionWrapper.create(subject);
        assertThat(wrapped.apply(1, 2), equalTo(3));
        verify(subject, times(1)).apply(1, 2);
    }
}
