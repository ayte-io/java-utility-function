package io.ayte.utility.function.kit.ternary.operator.comparing;

import io.ayte.utility.function.api.TernaryOperator;
import io.ayte.utility.function.kit.AugmentedTernaryOperator;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.Comparator;

@ToString
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Min<T> implements AugmentedTernaryOperator<T> {
    private static final Min NATURAL = new Min<>(Comparator.naturalOrder());

    private final Comparator<? super T> comparator;

    @Override
    public T apply(T alpha, T beta, T gamma) {
        if (comparator.compare(alpha, beta) > 0) {
            return comparator.compare(beta, gamma) > 0 ? gamma : beta;
        }
        return comparator.compare(alpha, gamma) > 0 ? gamma : alpha;
    }

    public static <T> TernaryOperator<T> create(@NonNull Comparator<? super T> comparator) {
        return new Min<>(comparator);
    }

    @SuppressWarnings("unchecked")
    public static <T extends Comparable<T>> TernaryOperator<T> create() {
        return NATURAL;
    }
}
