package io.ayte.utility.function.kit.binary.operator.comparing;

import io.ayte.utility.function.kit.AugmentedBinaryOperator;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.Comparator;

@ToString
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Max<T> implements AugmentedBinaryOperator<T> {
    private static final Max NATURAL = new Max<>(Comparator.naturalOrder());

    private final Comparator<? super T> comparator;

    @Override
    public T apply(T alpha, T beta) {
        return comparator.compare(alpha, beta) >= 0 ? alpha : beta;
    }

    public static <T> Max<T> create(@NonNull Comparator<? super T> comparator) {
        return new Max<>(comparator);
    }

    @SuppressWarnings("unchecked")
    public static <T> Max<T> create() {
        return NATURAL;
    }
}
