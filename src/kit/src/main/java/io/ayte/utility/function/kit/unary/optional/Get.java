package io.ayte.utility.function.kit.unary.optional;

import io.ayte.utility.function.kit.AugmentedFunction;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.ToString;

import java.util.Optional;
import java.util.function.Function;

@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Get<T> implements AugmentedFunction<Optional<T>, T> {
    private static final Get INSTANCE = new Get<>();

    @SuppressWarnings({"OptionalGetWithoutIsPresent", "squid:S3655"})
    @Override
    public T apply(@NonNull Optional<T> subject) {
        return subject.get();
    }

    @SuppressWarnings("unchecked")
    public static <T> Function<Optional<T>, T> create() {
        return INSTANCE;
    }
}
