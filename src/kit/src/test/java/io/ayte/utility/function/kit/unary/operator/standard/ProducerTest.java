package io.ayte.utility.function.kit.unary.operator.standard;

import lombok.val;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ProducerTest {
    @Test
    public void rejectsNullFactory() {
        assertThrows(NullPointerException.class, () -> Producer.create(null));
    }

    @Test
    public void satisfiesContract() {
        val subject = Producer.create(() -> 2);
        assertThat(subject.apply(1), equalTo(2));
    }
}
