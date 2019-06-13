package io.ayte.utility.function.kit.ternary.operator.standard;

import io.ayte.utility.function.api.TernaryOperator;
import io.ayte.utility.function.kit.AugmentedTernaryOperator;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UsingSecond<T> implements AugmentedTernaryOperator<T> {
    private static final UsingSecond INSTANCE = new UsingSecond<>();

    @Override
    public T apply(T alpha, T beta, T gamma) {
        return beta;
    }

    @SuppressWarnings("unchecked")
    public static <T> TernaryOperator<T> create() {
        return INSTANCE;
    }
}
