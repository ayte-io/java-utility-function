package io.ayte.utility.function.kit.ternary.capture;

import io.ayte.utility.function.api.TernaryFunction;
import io.ayte.utility.function.api.UnaryFunction;
import io.ayte.utility.function.kit.AugmentedFunction;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@ToString
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class AlphaBetaCapturedTernaryFunction<T1, T2, T3, R> implements AugmentedFunction<T3, R> {
    private final TernaryFunction<T1, T2, T3, R> delegate;
    private final T1 alpha;
    private final T2 beta;

    @Override
    public R apply(T3 gamma) {
        return delegate.apply(alpha, beta, gamma);
    }

    public static <T1, T2, T3, R> UnaryFunction<T3, R> create(
            @NonNull TernaryFunction<T1, T2, T3, R> delegate,
            T1 alpha,
            T2 beta
    ) {
        return new AlphaBetaCapturedTernaryFunction<>(delegate, alpha, beta);
    }
}
