package io.ayte.utility.function.kit;

import io.ayte.utility.function.api.TernaryFunction;
import io.ayte.utility.function.kit.ternary.standard.AugmentationFunctionWrapper;
import io.ayte.utility.function.kit.ternary.standard.TernaryComposition;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.function.Function;

/**
 * @since 0.1.0
 */
@SuppressWarnings("unused")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TernaryFunctions {
    /**
     * Connects two functions, creating a composition that pipes output
     * of one function into another.
     *
     * @param former First (outer) function.
     * @param latter Second (inner) function which accepts input of
     * first function.
     * @param <T1> First function first argument type.
     * @param <T2> First function second argument type.
     * @param <T3> First function third argument type.
     * @param <I> First function output / second function input type.
     * @param <R> Return type
     * @return Function that comprises of both functions.
     * @since 0.1.0
     */
    public static <T1, T2, T3, I, R> TernaryFunction<T1, T2, T3, R> compose(
            @NonNull TernaryFunction<? super T1, ? super T2, ? super T3, ? extends I> former,
            @NonNull Function<? super I, ? extends R> latter
    ) {
        return TernaryComposition.create(former, latter);
    }

    /**
     * Converts provided function (by casting or wrapping) to target
     * interface type.
     *
     * @param subject Function to convert to augmented function.
     * @param <T1> First argument type.
     * @param <T2> Second argument type.
     * @param <T3> Third argument type.
     * @param <R> Return type.
     * @return Casted or wrapped function.
     * @since 0.1.0
     */
    public static <T1, T2, T3, R> AugmentedTernaryFunction<T1, T2, T3, R> augment(
            @NonNull TernaryFunction<T1, T2, T3, R> subject
    ) {
        return AugmentationFunctionWrapper.create(subject);
    }
}
