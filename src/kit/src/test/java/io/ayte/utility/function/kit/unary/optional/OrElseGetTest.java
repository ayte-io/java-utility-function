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

@SuppressWarnings("unchecked")
class OrElseGetTest {
    @Test
    public void rejectsNullSupplier() {
        assertThrows(NullPointerException.class, () -> OrElseGet.create(null));
    }

    @Test
    public void substitutesEmptyValue() {
        val supplier = mock(Supplier.class);
        when(supplier.get()).thenReturn(3);
        val subject = OrElseGet.create(supplier);
        assertThat(subject.apply(Optional.empty()), equalTo(3));
        verify(supplier, times(1)).get();
    }

    @Test
    public void respectsNonEmptyValue() {
        val supplier = mock(Supplier.class);
        when(supplier.get()).thenReturn(3);
        val subject = OrElseGet.create(supplier);
        assertThat(subject.apply(Optional.of(4)), equalTo(4));
        verify(supplier, times(0)).get();
    }
}
