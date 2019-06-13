package io.ayte.utility.function.kit.ternary.capture;

import io.ayte.utility.function.api.TernaryFunction;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.function.Supplier;

@ToString
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class AllArgumentsCapturedTernaryFunction<T1, T2, T3, R> implements Supplier<R> {
    private final TernaryFunction<T1, T2, T3, R> delegate;
    private final T1 alpha;
    private final T2 beta;
    private final T3 gamma;

    @Override
    public R get() {
        return delegate.apply(alpha, beta, gamma);
    }

    public static <T1, T2, T3, R> Supplier<R> create(
            @NonNull TernaryFunction<T1, T2, T3, R> delegate,
            T1 alpha,
            T2 beta,
            T3 gamma
    ) {
        return new AllArgumentsCapturedTernaryFunction<>(delegate, alpha, beta, gamma);
    }
}
