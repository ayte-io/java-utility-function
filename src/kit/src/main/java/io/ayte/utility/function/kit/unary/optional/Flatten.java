package io.ayte.utility.function.kit.unary.optional;

import io.ayte.utility.function.kit.AugmentedFunction;
import io.ayte.utility.function.kit.unary.operator.standard.Identity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.ToString;

import java.util.Optional;
import java.util.function.Function;

/**
 * Probably nobody's gonna use it, but anyway.
 *
 * @param <I> Identity type.
 * @since 0.1.0
 */
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Flatten<I> implements AugmentedFunction<Optional<Optional<I>>, Optional<I>> {
    private static final Flatten INSTANCE = new Flatten<>();

    @Override
    public Optional<I> apply(@NonNull Optional<Optional<I>> subject) {
        return subject.flatMap(Identity.create());
    }

    @SuppressWarnings("unchecked")
    public static <I> Function<Optional<Optional<I>>, Optional<I>> create() {
        return INSTANCE;
    }
}
