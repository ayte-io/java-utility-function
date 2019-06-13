package io.ayte.utility.function.kit.ternary.capture;

import io.ayte.utility.function.api.TernaryFunction;
import io.ayte.utility.function.kit.AugmentedBinaryFunction;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@ToString
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class BetaCapturedTernaryFunction<T1, T2, T3, R> implements AugmentedBinaryFunction<T1, T3, R> {
    private final TernaryFunction<T1, T2, T3, R> delegate;
    private final T2 beta;

    @Override
    public R apply(T1 alpha, T3 gamma) {
        return delegate.apply(alpha, beta, gamma);
    }

    public static <T1, T2, T3, R> BetaCapturedTernaryFunction<T1, T2, T3, R> create(
            @NonNull TernaryFunction<T1, T2, T3, R> delegate,
            T2 beta
    ) {
        return new BetaCapturedTernaryFunction<>(delegate, beta);
    }
}
