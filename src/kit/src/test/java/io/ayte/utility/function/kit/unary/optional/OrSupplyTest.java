package io.ayte.utility.function.kit.unary.optional;

import lombok.val;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.function.Supplier;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class OrSupplyTest {
    @Test
    public void rejectsNullFactory() {
        assertThrows(NullPointerException.class, () -> OrSupply.create(null));
    }

    @SuppressWarnings("unchecked")
    @Test
    public void satisfiesContract() {
        val factory = mock(Supplier.class);
        when(factory.get()).thenReturn(Optional.of(2));
        val subject = OrSupply.create(factory);
        assertThat(subject.apply(Optional.of(3)), equalTo(Optional.of(3)));
        verify(factory, times(0)).get();
        assertThat(subject.apply(Optional.empty()), equalTo(Optional.of(2)));
    }
}
