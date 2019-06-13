package io.ayte.utility.function.kit.unary.operator.standard;

import io.ayte.utility.function.api.EnrichedUnaryOperator;
import io.ayte.utility.function.kit.AugmentedUnaryOperator;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.function.Supplier;

@ToString(includeFieldNames = false)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Producer<T> implements AugmentedUnaryOperator<T> {
    private final Supplier<? extends T> factory;

    @Override
    public T apply(T any) {
        return factory.get();
    }

    public static <T> EnrichedUnaryOperator<T> create(@NonNull Supplier<? extends T> factory) {
        return new Producer<>(factory);
    }
}
