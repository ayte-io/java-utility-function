package io.ayte.utility.function.kit;

import io.ayte.utility.function.api.BinaryFunction;
import io.ayte.utility.function.kit.binary.standard.AugmentationFunctionWrapper;
import io.ayte.utility.function.kit.binary.standard.BinaryComposition;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * @since 0.1.0
 */
@SuppressWarnings("unused")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BinaryFunctions {
    /**
     * Composes two functions, piping output of one into another.
     *
     * @param former First function.
     * @param latter Second function that accepts output of first one.
     * @param <T1> First function first argument type.
     * @param <T2> First function second argument type.
     * @param <I> First function output / second function input typee.
     * @param <R> Return type.
     * @return Function that comprises of both functions.
     * @since 0.1.0
     */
    public static <T1, T2, I, R> BinaryFunction<T1, T2, R> compose(
            @NonNull BiFunction<? super T1, ? super T2, ? extends I> former,
            @NonNull Function<? super I, ? extends R> latter
    ) {
        return BinaryComposition.create(former, latter);
    }

    /**
     * Wraps or casts provided function so resulting type satisfies
     * augmented interface.
     *
     * @param subject Function to convert.
     * @param <T1> First argument type.
     * @param <T2> Second argument type.
     * @param <R> Return type.
     * @return Wrapped or casted function.
     * @since 0.1.0
     */
    public static <T1, T2, R> BinaryFunction<T1, T2, R> augment(@NonNull BiFunction<T1, T2, R> subject) {
        return AugmentationFunctionWrapper.create(subject);
    }
}
