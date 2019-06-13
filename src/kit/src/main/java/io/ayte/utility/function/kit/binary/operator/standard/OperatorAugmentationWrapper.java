package io.ayte.utility.function.kit.binary.operator.standard;

import io.ayte.utility.function.kit.AugmentedBinaryOperator;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.function.BinaryOperator;

@ToString(includeFieldNames = false)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class OperatorAugmentationWrapper<T> implements AugmentedBinaryOperator<T> {
    private final BinaryOperator<T> delegate;

    @Override
    public T apply(T alpha, T beta) {
        return delegate.apply(alpha, beta);
    }

    public static <T> AugmentedBinaryOperator<T> create(@NonNull BinaryOperator<T> subject) {
        if (subject instanceof AugmentedBinaryOperator) {
            return (AugmentedBinaryOperator<T>) subject;
        }
        return new OperatorAugmentationWrapper<>(subject);
    }
}
