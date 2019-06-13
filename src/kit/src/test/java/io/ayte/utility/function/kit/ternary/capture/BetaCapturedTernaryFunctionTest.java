package io.ayte.utility.function.kit.ternary.capture;

import io.ayte.utility.function.api.TernaryFunction;
import lombok.val;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class BetaCapturedTernaryFunctionTest {
    @Test
    public void rejectsNullDelegate() {
        assertThrows(NullPointerException.class, () -> BetaCapturedTernaryFunction.create(null, 1));
    }

    @Test
    public void acceptsNullValues() {
        val subject = BetaCapturedTernaryFunction.create((a, b, c) -> 4, null);
        assertThat(subject.apply(null, null), equalTo(4));
    }

    @SuppressWarnings("unchecked")
    @Test
    public void callsDelegate() {
        val delegate = mock(TernaryFunction.class);
        when(delegate.apply(1, 2, 3)).thenReturn(4);
        val subject = BetaCapturedTernaryFunction.create(delegate, 2);
        assertThat(subject.apply(1, 3), equalTo(4));
    }
}
