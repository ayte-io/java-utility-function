package io.ayte.utility.function.kit.binary.operator.standard;

import io.ayte.utility.function.kit.AugmentedBinaryOperator;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.function.BiFunction;
import java.util.function.BinaryOperator;

@ToString(includeFieldNames = false)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class FunctionToOperatorWrapper<T> implements AugmentedBinaryOperator<T> {
    private final BiFunction<? super T, ? super T, ? extends T> delegate;

    @Override
    public T apply(T alpha, T beta) {
        return delegate.apply(alpha, beta);
    }

    @SuppressWarnings("unchecked")
    public static <T> BinaryOperator<T> create(@NonNull BiFunction<? super T, ? super T, ? extends T> subject) {
        if (subject instanceof BinaryOperator) {
            return (BinaryOperator<T>) subject;
        }
        return new FunctionToOperatorWrapper<>(subject);
    }
}
