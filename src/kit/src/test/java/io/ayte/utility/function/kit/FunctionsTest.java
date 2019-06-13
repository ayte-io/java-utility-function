package io.ayte.utility.function.kit;

import lombok.val;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

class FunctionsTest {
    @Test
    public void testIdentity() {
        assertThat(Functions.identity().apply(1), equalTo(1));
    }

    @Test
    public void testComposition() {
        val subject = Functions.<Integer, Integer, Integer>compose(x -> x * 2, x -> x * 3);
        assertThat(subject.apply(1), equalTo(6));
        assertThat(subject.apply(2), equalTo(12));
    }

    @Test
    public void testToList() {
        val source = new LinkedHashSet<Integer>();
        source.add(1);
        source.add(2);
        source.add(3);
        assertThat(Functions.toList().apply(source), equalTo(Arrays.asList(1, 2, 3)));
    }

    @Test
    public void testToSet() {
        val source = Arrays.asList(1, 2, 3);
        val expectation = new HashSet<>(source);
        assertThat(Functions.toSet().apply(source), equalTo(expectation));
    }

    @Test
    public void testToMap() {
        val source = Arrays.asList(1, 2, 3);
        val expectation = new HashMap<Integer, Boolean>();
        expectation.put(2, false);
        expectation.put(4, true);
        expectation.put(6, false);
        val value = Functions.<Integer, Integer, Boolean>toMap(x -> x * 2, x -> x % 2 == 0).apply(source);
        assertThat(value, equalTo(expectation));
    }
}
