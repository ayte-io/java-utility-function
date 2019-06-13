package io.ayte.utility.function.kit.unary.iterable;

import io.ayte.utility.function.api.UnaryFunction;
import io.ayte.utility.function.kit.AugmentedFunction;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.val;

import java.util.Comparator;

@ToString
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Max<T> implements AugmentedFunction<Iterable<T>, T> {
    private static final Max NATURAL = new Max<>(Comparator.naturalOrder());

    private final Comparator<? super T> comparator;

    @Override
    public T apply(Iterable<T> subject) {
        val iterator = subject.iterator();
        if (!iterator.hasNext()) {
            return null;
        }
        T leader = iterator.next();
        while (iterator.hasNext()) {
            T candidate = iterator.next();
            if (comparator.compare(leader, candidate) <= 0) {
                leader = candidate;
            }
        }
        return leader;
    }

    public static <T> UnaryFunction<Iterable<T>, T> create(@NonNull Comparator<? super T> comparator) {
        return new Max<>(comparator);
    }

    @SuppressWarnings("unchecked")
    public static <T extends Comparable<T>> UnaryFunction<Iterable<T>, T> create() {
        return NATURAL;
    }
}
