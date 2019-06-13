package io.ayte.utility.function.kit.unary.standard;

import io.ayte.utility.function.kit.AugmentedFunction;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.function.Function;

@ToString
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Composition<T1, T2, R> implements AugmentedFunction<T1, R> {
    private final Function<? super T1, ? extends T2> delegate;
    private final Function<? super T2, ? extends R> transformer;

    @Override
    public R apply(T1 subject) {
        return transformer.apply(delegate.apply(subject));
    }

    public static <T1, T2, R> Composition<T1, T2, R> create(
            @NonNull Function<? super T1, ? extends T2> delegate,
            @NonNull Function<? super T2, ? extends R> transformer
    ) {
        return new Composition<>(delegate, transformer);
    }
}
