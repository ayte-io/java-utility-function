package io.ayte.utility.function.kit.unary.optional;

import io.ayte.utility.function.kit.AugmentedUnaryOperator;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.Optional;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

@ToString(includeFieldNames = false)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class OrSupply<I> implements AugmentedUnaryOperator<Optional<I>> {
    private final Supplier<Optional<? extends I>> factory;

    @SuppressWarnings("unchecked")
    @Override
    public Optional<I> apply(@NonNull Optional<I> subject) {
        return subject.isPresent() ? subject : (Optional<I>) factory.get();
    }

    public static <I> UnaryOperator<Optional<I>> create(@NonNull Supplier<Optional<? extends I>> factory) {
        return new OrSupply<>(factory);
    }
}
