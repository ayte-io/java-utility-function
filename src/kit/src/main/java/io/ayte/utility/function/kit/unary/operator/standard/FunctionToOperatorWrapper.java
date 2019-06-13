package io.ayte.utility.function.kit.unary.operator.standard;

import io.ayte.utility.function.kit.AugmentedUnaryOperator;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.function.Function;
import java.util.function.UnaryOperator;

@ToString(includeFieldNames = false)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class FunctionToOperatorWrapper<T> implements AugmentedUnaryOperator<T> {
    private final Function<? super T, ? extends T> delegate;

    @Override
    public T apply(T subject) {
        return delegate.apply(subject);
    }

    @SuppressWarnings("unchecked")
    public static <T> UnaryOperator<T> create(@NonNull Function<? super T, ? extends T> subject) {
        if (subject instanceof UnaryOperator) {
            return (UnaryOperator<T>) subject;
        }
        return new FunctionToOperatorWrapper<>(subject);
    }
}
