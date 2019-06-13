package io.ayte.utility.function.kit.ternary.operator.standard;

import io.ayte.utility.function.api.TernaryFunction;
import io.ayte.utility.function.api.TernaryOperator;
import io.ayte.utility.function.kit.AugmentedTernaryOperator;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@ToString(includeFieldNames = false)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class FunctionToOperatorWrapper<T> implements AugmentedTernaryOperator<T> {
    private final TernaryFunction<? super T, ? super T, ? super T, ? extends T> delegate;

    @Override
    public T apply(T alpha, T beta, T gamma) {
        return delegate.apply(alpha, beta, gamma);
    }

    @SuppressWarnings("unchecked")
    public static <T> TernaryOperator<T> create(
            @NonNull TernaryFunction<? super T, ? super T, ? super T, ? extends T> subject
    ) {
        if (subject instanceof TernaryOperator) {
            return (TernaryOperator<T>) subject;
        }
        return new FunctionToOperatorWrapper<>(subject);
    }
}
