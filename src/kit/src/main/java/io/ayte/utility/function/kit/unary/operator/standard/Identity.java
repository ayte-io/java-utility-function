package io.ayte.utility.function.kit.unary.operator.standard;

import io.ayte.utility.function.api.EnrichedUnaryOperator;
import io.ayte.utility.function.kit.AugmentedUnaryOperator;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Classic {@code x -> x} function, wrapped in a named class.
 *
 * @param <T> value type.
 */
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Identity<T> implements AugmentedUnaryOperator<T> {
    private static final Identity INSTANCE = new Identity<>();

    @Override
    public T apply(T subject) {
        return subject;
    }

    @SuppressWarnings("unchecked")
    public static <T> EnrichedUnaryOperator<T> create() {
        return INSTANCE;
    }
}
