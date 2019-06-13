package io.ayte.utility.function.kit.unary.optional;

import io.ayte.utility.function.kit.AugmentedFunction;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.Optional;
import java.util.function.Supplier;

@ToString(includeFieldNames = false)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class OrElseGet<I> implements AugmentedFunction<Optional<I>, I> {
    private final Supplier<? extends I> supplier;

    @Override
    public I apply(@NonNull Optional<I> subject) {
        return subject.orElseGet(supplier);
    }

    public static <T> OrElseGet<T> create(@NonNull Supplier<? extends T> supplier) {
        return new OrElseGet<>(supplier);
    }
}
