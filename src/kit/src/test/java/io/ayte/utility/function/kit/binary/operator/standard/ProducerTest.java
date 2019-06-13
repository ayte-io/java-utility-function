package io.ayte.utility.function.kit.binary.operator.standard;

import lombok.val;
import org.junit.jupiter.api.Test;

import java.util.function.Supplier;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ProducerTest {
    @Test
    public void rejectsNullDelegate() {
        assertThrows(NullPointerException.class, () -> Producer.create(null));
    }

    @SuppressWarnings("unchecked")
    @Test
    public void callsDelegate() {
        val delegate = mock(Supplier.class);
        when(delegate.get()).thenReturn(32);
        val producer = Producer.create(delegate);
        assertThat(producer.apply(1, 2), equalTo(32));
        verify(delegate, times(1)).get();
    }
}
