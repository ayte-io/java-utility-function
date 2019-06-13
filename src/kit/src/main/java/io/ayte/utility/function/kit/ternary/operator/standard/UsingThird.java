package io.ayte.utility.function.kit.ternary.operator.standard;

import io.ayte.utility.function.api.TernaryOperator;
import io.ayte.utility.function.kit.AugmentedTernaryOperator;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UsingThird<T> implements AugmentedTernaryOperator<T> {
    private static final UsingThird INSTANCE = new UsingThird<>();

    @Override
    public T apply(T alpha, T beta, T gamma) {
        return gamma;
    }

    @SuppressWarnings("unchecked")
    public static <T> TernaryOperator<T> create() {
        return INSTANCE;
    }
}
