package io.ayte.utility.function.kit.ternary.standard;

import io.ayte.utility.function.api.TernaryFunction;
import io.ayte.utility.function.kit.AugmentedTernaryFunction;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@ToString(includeFieldNames = false)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class AugmentationFunctionWrapper<T1, T2, T3, R> implements AugmentedTernaryFunction<T1, T2, T3, R> {
    private final TernaryFunction<T1, T2, T3, R> delegate;

    @Override
    public R apply(T1 alpha, T2 beta, T3 gamma) {
        return delegate.apply(alpha, beta, gamma);
    }

    public static <T1, T2, T3, R> AugmentedTernaryFunction<T1, T2, T3, R> create(
            @NonNull TernaryFunction<T1, T2, T3, R> subject
    ) {
        if (subject instanceof AugmentedTernaryFunction) {
            return (AugmentedTernaryFunction<T1, T2, T3, R>) subject;
        }
        return new AugmentationFunctionWrapper<>(subject);
    }
}
