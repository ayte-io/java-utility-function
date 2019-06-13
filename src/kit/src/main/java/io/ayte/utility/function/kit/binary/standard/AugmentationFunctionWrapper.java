package io.ayte.utility.function.kit.binary.standard;

import io.ayte.utility.function.kit.AugmentedBinaryFunction;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.function.BiFunction;

@ToString(includeFieldNames = false)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class AugmentationFunctionWrapper<T1, T2, R> implements AugmentedBinaryFunction<T1, T2, R> {
    private final BiFunction<T1, T2, R> delegate;

    @Override
    public R apply(T1 alpha, T2 beta) {
        return delegate.apply(alpha, beta);
    }

    public static <T1, T2, R> AugmentedBinaryFunction<T1, T2, R> create(@NonNull BiFunction<T1, T2, R> subject) {
        if (subject instanceof AugmentedBinaryFunction) {
            return (AugmentedBinaryFunction<T1, T2, R>) subject;
        }
        return new AugmentationFunctionWrapper<>(subject);
    }
}
