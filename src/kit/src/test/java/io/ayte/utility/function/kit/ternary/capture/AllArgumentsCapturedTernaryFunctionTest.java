package io.ayte.utility.function.kit.ternary.capture;

import io.ayte.utility.function.api.TernaryFunction;
import lombok.val;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SuppressWarnings("unchecked")
class AllArgumentsCapturedTernaryFunctionTest {
    @Test
    public void rejectsNullDelegate() {
        assertThrows(NullPointerException.class, () -> AllArgumentsCapturedTernaryFunction.create(null, 1, 2, 3));
    }

    @Test
    public void acceptsNullValues() {
        val subject = AllArgumentsCapturedTernaryFunction.create((a, b, c) -> 4, null, null, null);
        assertThat(subject.get(), equalTo(4));
    }

    @Test
    public void callsDelegate() {
        val delegate = mock(TernaryFunction.class);
        when(delegate.apply(1, 2, 3)).thenReturn(4);
        val subject = AllArgumentsCapturedTernaryFunction.create(delegate, 1, 2, 3);
        assertThat(subject.get(), equalTo(4));
        verify(delegate, times(1)).apply(1, 2, 3);
    }
}
