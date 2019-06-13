package io.ayte.utility.function.kit.binary.operator.standard;

import io.ayte.utility.function.kit.AugmentedBinaryOperator;
import lombok.val;
import org.junit.jupiter.api.Test;

import java.util.function.BinaryOperator;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class OperatorAugmentationWrapperTest {
    @Test
    public void rejectsNullDelegate() {
        assertThrows(NullPointerException.class, () -> OperatorAugmentationWrapper.create(null));
    }

    @Test
    public void passesAugmentedOperatorThrough() {
        AugmentedBinaryOperator<Object> operator = (a, b) -> a;
        val wrapped = OperatorAugmentationWrapper.create(operator);
        assertThat(wrapped, is(operator));
    }

    @Test
    public void wrapsRegularOperator() {
        BinaryOperator<Object> operator = (a, b) -> a;
        val wrapped = OperatorAugmentationWrapper.create(operator);
        assertThat(wrapped, not(is(operator)));
    }

    @SuppressWarnings("unchecked")
    @Test
    public void callsDelegate() {
        val delegate = mock(BinaryOperator.class);
        when(delegate.apply(1, 2)).thenReturn(3);
        val wrapped = OperatorAugmentationWrapper.create(delegate);
        assertThat(wrapped.apply(1, 2), equalTo(3));
        verify(delegate, times(1)).apply(1, 2);
    }
}
