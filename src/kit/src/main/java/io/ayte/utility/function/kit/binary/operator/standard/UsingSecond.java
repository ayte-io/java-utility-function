package io.ayte.utility.function.kit.binary.operator.standard;

import io.ayte.utility.function.kit.AugmentedBinaryOperator;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UsingSecond<T> implements AugmentedBinaryOperator<T> {
    private static final UsingSecond INSTANCE = new UsingSecond<>();

    @Override
    public T apply(T any, T value) {
        return value;
    }

    @SuppressWarnings("unchecked")
    public static <T> UsingSecond<T> create() {
        return INSTANCE;
    }
}
