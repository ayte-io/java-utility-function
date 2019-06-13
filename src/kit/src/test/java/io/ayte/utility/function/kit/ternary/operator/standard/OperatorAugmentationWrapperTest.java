package io.ayte.utility.function.kit.ternary.operator.standard;

import io.ayte.utility.function.api.TernaryOperator;
import io.ayte.utility.function.kit.AugmentedTernaryOperator;
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

class OperatorAugmentationWrapperTest {
    @Test
    public void rejectsNullDelegate() {
        assertThrows(NullPointerException.class, () -> OperatorAugmentationWrapper.create(null));
    }

    @Test
    public void passesThroughAugmentedOperator() {
        AugmentedTernaryOperator<Object> operator = (a, b, c) -> a;
        val wrapped = OperatorAugmentationWrapper.create(operator);
        assertThat(wrapped, is(operator));
    }

    @Test
    public void wrapsRegularOperator() {
        TernaryOperator<Object> operator = (a, b, c) -> a;
        val wrapped = OperatorAugmentationWrapper.create(operator);
        assertThat(wrapped, not(is(operator)));
    }

    @SuppressWarnings("unchecked")
    @Test
    public void callsDelegate() {
        val operator = mock(TernaryOperator.class);
        when(operator.apply(1, 2, 3)).thenReturn(1);
        val wrapped = OperatorAugmentationWrapper.create(operator);
        assertThat(wrapped.apply(1, 2, 3), equalTo(1));
        verify(operator, times(1)).apply(1, 2, 3);
    }
}
