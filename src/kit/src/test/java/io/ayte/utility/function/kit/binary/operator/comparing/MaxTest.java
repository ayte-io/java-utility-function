package io.ayte.utility.function.kit.binary.operator.comparing;

import lombok.val;
import org.junit.jupiter.api.Test;

import java.util.Comparator;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class MaxTest {
    @Test
    public void rejectsNullComparator() {
        assertThrows(NullPointerException.class, () -> Max.create(null));
    }

    @SuppressWarnings("unchecked")
    @Test
    public void selectsMaxValue() {
        val comparator = mock(Comparator.class);
        val alpha = new Object();
        val beta = new Object();
        when(comparator.compare(alpha, beta)).thenReturn(-1);
        when(comparator.compare(beta, alpha)).thenReturn(1);
        val subject = Max.create(comparator);
        assertThat(subject.apply(alpha, beta), is(beta));
        assertThat(subject.apply(beta, alpha), is(beta));
    }
}
