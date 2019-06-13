package io.ayte.utility.function.kit.unary.operator.standard;

import io.ayte.utility.function.kit.AugmentedUnaryOperator;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.function.UnaryOperator;

@ToString(includeFieldNames = false)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class OperatorAugmentationWrapper<T> implements AugmentedUnaryOperator<T> {
    private final UnaryOperator<T> delegate;

    @Override
    public T apply(T subject) {
        return delegate.apply(subject);
    }

    public static <T> AugmentedUnaryOperator<T> create(@NonNull UnaryOperator<T> subject) {
        if (subject instanceof AugmentedUnaryOperator) {
            return (AugmentedUnaryOperator<T>) subject;
        }
        return new OperatorAugmentationWrapper<>(subject);
    }
}
