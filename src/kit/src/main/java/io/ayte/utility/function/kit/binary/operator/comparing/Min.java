package io.ayte.utility.function.kit.binary.operator.comparing;

import io.ayte.utility.function.kit.AugmentedBinaryOperator;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.Comparator;

@ToString
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Min<T> implements AugmentedBinaryOperator<T> {
    private static final Min NATURAL = new Min<>(Comparator.naturalOrder());

    private final Comparator<? super T> comparator;

    @Override
    public T apply(T alpha, T beta) {
        return comparator.compare(alpha, beta) <= 0 ? alpha : beta;
    }

    public static <T> Min<T> create(@NonNull Comparator<? super T> comparator) {
        return new Min<>(comparator);
    }

    @SuppressWarnings("unchecked")
    public static <T> Min<T> create() {
        return NATURAL;
    }
}
