package io.ayte.utility.function.kit.binary.operator.standard;

import io.ayte.utility.function.kit.AugmentedBinaryOperator;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UsingFirst<T> implements AugmentedBinaryOperator<T> {
    private static final UsingFirst INSTANCE = new UsingFirst<>();

    @Override
    public T apply(T value, T any) {
        return value;
    }

    @SuppressWarnings("unchecked")
    public static <T> UsingFirst<T> create() {
        return INSTANCE;
    }
}
