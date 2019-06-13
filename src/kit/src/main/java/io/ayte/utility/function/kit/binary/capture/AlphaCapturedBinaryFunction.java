package io.ayte.utility.function.kit.binary.capture;

import io.ayte.utility.function.kit.AugmentedFunction;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.function.BiFunction;

@ToString
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class AlphaCapturedBinaryFunction<T1, T2, R> implements AugmentedFunction<T2, R> {
    private final BiFunction<T1, T2, R> delegate;
    private final T1 alpha;

    @Override
    public R apply(T2 beta) {
        return delegate.apply(alpha, beta);
    }

    public static <T1, T2, R> AlphaCapturedBinaryFunction<T1, T2, R> create(@NonNull BiFunction<T1, T2, R> delegate, T1 alpha) {
        return new AlphaCapturedBinaryFunction<>(delegate, alpha);
    }
}
