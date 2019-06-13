package io.ayte.utility.function.kit.ternary.operator.standard;

import io.ayte.utility.function.api.TernaryOperator;
import io.ayte.utility.function.kit.AugmentedTernaryOperator;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.function.Supplier;

@ToString(includeFieldNames = false)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Producer<T> implements AugmentedTernaryOperator<T> {
    private final Supplier<? extends T> delegate;

    @Override
    public T apply(T alpha, T beta, T gamma) {
        return delegate.get();
    }

    public static <T> TernaryOperator<T> create(@NonNull Supplier<? extends T> supplier) {
        return new Producer<>(supplier);
    }
}
