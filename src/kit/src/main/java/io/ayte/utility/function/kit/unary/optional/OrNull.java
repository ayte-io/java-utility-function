package io.ayte.utility.function.kit.unary.optional;

import io.ayte.utility.function.kit.AugmentedFunction;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.ToString;

import java.util.Optional;

@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OrNull<T> implements AugmentedFunction<Optional<T>, T> {
    private static final OrNull INSTANCE = new OrNull<>();

    @Override
    public T apply(@NonNull Optional<T> subject) {
        return subject.orElse(null);
    }

    @SuppressWarnings("unchecked")
    public static <T> OrNull<T> create() {
        return INSTANCE;
    }
}
