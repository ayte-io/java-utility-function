package io.ayte.utility.function.kit.ternary.capture;

import io.ayte.utility.function.api.TernaryFunction;
import io.ayte.utility.function.kit.AugmentedBinaryFunction;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@ToString
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class GammaCapturedTernaryFunction<T1, T2, T3, R> implements AugmentedBinaryFunction<T1, T2, R> {
    private final TernaryFunction<T1, T2, T3, R> delegate;
    private final T3 gamma;

    @Override
    public R apply(T1 alpha, T2 beta) {
        return delegate.apply(alpha, beta, gamma);
    }

    public static <T1, T2, T3, R> GammaCapturedTernaryFunction<T1, T2, T3, R> create(
            @NonNull TernaryFunction<T1, T2, T3, R> delegate,
            T3 gamma
    ) {
        return new GammaCapturedTernaryFunction<>(delegate, gamma);
    }
}
