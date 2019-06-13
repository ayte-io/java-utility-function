package io.ayte.utility.function.kit.unary.operator.standard;

import io.ayte.utility.function.kit.AugmentedUnaryOperator;
import lombok.val;
import org.junit.jupiter.api.Test;

import java.util.function.UnaryOperator;

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
    public void passesThroughAugmentedOperator() {
        AugmentedUnaryOperator<Integer> operator = x -> x;
        val wrapped = OperatorAugmentationWrapper.create(operator);
        assertThat(wrapped, is(operator));
    }

    @Test
    public void wrapsRegularOperator() {
        UnaryOperator<Integer> operator = x -> x;
        val wrapped = OperatorAugmentationWrapper.create(operator);
        assertThat(wrapped, not(is(operator)));
    }

    @SuppressWarnings("unchecked")
    @Test
    public void callsDelegate() {
        val operator = mock(UnaryOperator.class);
        when(operator.apply(1)).thenReturn(1);
        val wrapped = OperatorAugmentationWrapper.create(operator);
        assertThat(wrapped.apply(1), equalTo(1));
        verify(operator, times(1)).apply(1);
    }
}
