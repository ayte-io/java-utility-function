package io.ayte.utility.function.kit.unary.optional;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.Optional;
import java.util.function.Function;

@ToString(includeFieldNames = false)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class OrElse<T> implements Function<Optional<T>, T> {
    private final T fallback;

    @Override
    public T apply(@NonNull Optional<T> subject) {
        return subject.orElse(fallback);
    }

    public static <T> OrElse<T> create(T fallback) {
        return new OrElse<>(fallback);
    }
}
