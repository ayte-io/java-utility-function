package io.ayte.utility.function.kit.binary.standard;

import io.ayte.utility.function.kit.AugmentedBinaryFunction;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.function.BiFunction;
import java.util.function.Function;

@ToString
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class BinaryComposition<T1, T2, R1, R2> implements AugmentedBinaryFunction<T1, T2, R2> {
    private final BiFunction<? super T1, ? super T2, ? extends R1> delegate;
    private final Function<? super R1, ? extends R2> transformer;

    @Override
    public R2 apply(T1 alpha, T2 beta) {
        return transformer.apply(delegate.apply(alpha, beta));
    }

    public static <T1, T2, R1, R2> AugmentedBinaryFunction<T1, T2, R2> create(
            @NonNull BiFunction<? super T1, ? super T2, ? extends R1> delegate,
            @NonNull Function<? super R1, ? extends R2> transformer
    ) {
        return new BinaryComposition<>(delegate, transformer);
    }
}
