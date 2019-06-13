package io.ayte.utility.function.kit.unary.optional;

import io.ayte.utility.function.kit.AugmentedFunction;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.Optional;
import java.util.function.Function;

@ToString(includeFieldNames = false)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Map<I, O> implements AugmentedFunction<Optional<I>, Optional<O>> {
    private final Function<? super I, ? extends O> transformer;

    @Override
    public Optional<O> apply(@NonNull Optional<I> subject) {
        return subject.map(transformer);
    }

    public static <I, O> Function<Optional<I>, Optional<O>> create(
            @NonNull Function<? super I, ? extends O> transformer
    ) {
        return new Map<>(transformer);
    }
}
