package io.ayte.utility.function.kit.ternary.operator.standard;

import io.ayte.utility.function.api.TernaryOperator;
import io.ayte.utility.function.kit.AugmentedTernaryOperator;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@ToString(includeFieldNames = false)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class OperatorAugmentationWrapper<T> implements AugmentedTernaryOperator<T> {
    private final TernaryOperator<T> delegate;

    @Override
    public T apply(T alpha, T beta, T gamma) {
        return delegate.apply(alpha, beta, gamma);
    }

    public static <T> AugmentedTernaryOperator<T> create(@NonNull TernaryOperator<T> subject) {
        if (subject instanceof AugmentedTernaryOperator) {
            return (AugmentedTernaryOperator<T>) subject;
        }
        return new OperatorAugmentationWrapper<>(subject);
    }
}
