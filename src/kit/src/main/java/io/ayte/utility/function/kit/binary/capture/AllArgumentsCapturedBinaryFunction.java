package io.ayte.utility.function.kit.binary.capture;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.function.BiFunction;
import java.util.function.Supplier;

@ToString
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class AllArgumentsCapturedBinaryFunction<T1, T2, R> implements Supplier<R> {
    private final BiFunction<T1, T2, R> delegate;
    private final T1 alpha;
    private final T2 beta;

    @Override
    public R get() {
        return delegate.apply(alpha, beta);
    }

    public static <T1, T2, R> AllArgumentsCapturedBinaryFunction<T1, T2, R> create(
            @NonNull BiFunction<T1, T2, R> delegate,
            T1 alpha,
            T2 beta
    ) {
        return new AllArgumentsCapturedBinaryFunction<>(delegate, alpha, beta);
    }
}
