package io.ayte.utility.function.kit.ternary.standard;

import io.ayte.utility.function.api.TernaryFunction;
import io.ayte.utility.function.kit.AugmentedTernaryFunction;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.function.Function;

@ToString
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class TernaryComposition<T1, T2, T3, R1, R2> implements AugmentedTernaryFunction<T1, T2, T3, R2> {
    private final TernaryFunction<? super T1, ? super T2, ? super T3, ? extends R1> delegate;
    private final Function<? super R1, ? extends R2> transformer;

    @Override
    public R2 apply(T1 alpha, T2 beta, T3 gamma) {
        return transformer.apply(delegate.apply(alpha, beta, gamma));
    }

    public static <T1, T2, T3, R1, R2> TernaryFunction<T1, T2, T3, R2> create(
            @NonNull TernaryFunction<? super T1, ? super T2, ? super T3, ? extends R1> delegate,
            @NonNull Function<? super R1, ? extends R2> transformer
    ) {
        return new TernaryComposition<>(delegate, transformer);
    }
}
