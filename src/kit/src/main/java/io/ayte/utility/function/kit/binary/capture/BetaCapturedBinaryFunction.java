package io.ayte.utility.function.kit.binary.capture;

import io.ayte.utility.function.kit.AugmentedFunction;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.function.BiFunction;

@ToString
@RequiredArgsConstructor
public class BetaCapturedBinaryFunction<T1, T2, R> implements AugmentedFunction<T1, R> {
    private final BiFunction<T1, T2, R> delegate;
    private final T2 beta;

    @Override
    public R apply(T1 alpha) {
        return delegate.apply(alpha, beta);
    }

    public static <T1, T2, R> BetaCapturedBinaryFunction<T1, T2, R> create(@NonNull BiFunction<T1, T2, R> delegate, T2 beta) {
        return new BetaCapturedBinaryFunction<>(delegate, beta);
    }
}
