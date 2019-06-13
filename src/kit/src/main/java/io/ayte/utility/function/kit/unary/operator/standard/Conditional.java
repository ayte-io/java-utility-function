package io.ayte.utility.function.kit.unary.operator.standard;

import io.ayte.utility.function.api.EnrichedUnaryOperator;
import io.ayte.utility.function.kit.AugmentedUnaryOperator;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

@ToString
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Conditional<T> implements AugmentedUnaryOperator<T> {
    private final Predicate<T> condition;
    private final UnaryOperator<T> delegate;

    @Override
    public T apply(T subject) {
        return condition.test(subject) ? delegate.apply(subject) : subject;
    }

    public static <T> EnrichedUnaryOperator<T> create(@NonNull Predicate<T> condition, @NonNull UnaryOperator<T> delegate) {
        return new Conditional<>(condition, delegate);
    }

    public static <T> EnrichedUnaryOperator<T> create(@NonNull Predicate<T> condition, @NonNull Supplier<T> factory) {
        return create(condition, Producer.create(factory));
    }
}
