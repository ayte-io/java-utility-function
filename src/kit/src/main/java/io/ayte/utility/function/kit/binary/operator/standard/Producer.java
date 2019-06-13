package io.ayte.utility.function.kit.binary.operator.standard;

import io.ayte.utility.function.api.EnrichedBinaryOperator;
import io.ayte.utility.function.kit.AugmentedBinaryOperator;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.function.Supplier;

@ToString(includeFieldNames = false)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Producer<T> implements AugmentedBinaryOperator<T> {
    private final Supplier<? extends T> delegate;

    @Override
    public T apply(T alpha, T beta) {
        return delegate.get();
    }

    public static <T> EnrichedBinaryOperator<T> create(@NonNull Supplier<? extends T> delegate) {
        return new Producer<>(delegate);
    }
}
