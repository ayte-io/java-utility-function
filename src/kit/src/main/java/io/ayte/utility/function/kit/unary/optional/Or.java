package io.ayte.utility.function.kit.unary.optional;

import io.ayte.utility.function.kit.AugmentedUnaryOperator;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.Optional;

@SuppressWarnings("OptionalUsedAsFieldOrParameterType")
@ToString(includeFieldNames = false)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Or<I> implements AugmentedUnaryOperator<Optional<I>> {
    private final Optional<? extends I> substitute;

    @SuppressWarnings("unchecked")
    @Override
    public Optional<I> apply(@NonNull Optional<I> subject) {
        return subject.isPresent() ? subject : (Optional<I>) substitute;
    }

    public static <I> Or<I> create(@NonNull Optional<? extends I> substitute) {
        return new Or<>(substitute);
    }
}
