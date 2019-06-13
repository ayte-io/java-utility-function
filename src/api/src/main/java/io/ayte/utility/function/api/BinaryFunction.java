package io.ayte.utility.function.api;

import java.util.function.BiFunction;
import java.util.function.Supplier;

/**
 * {@link BiFunction} extension with argument-capturing methods.
 *
 * @param <T1> First argument type.
 * @param <T2> Second argument type.
 * @param <R> Return type.
 *
 * @since 0.1.0
 */
@FunctionalInterface
public interface BinaryFunction<T1, T2, R> extends BiFunction<T1, T2, R> {
    /**
     * @param alpha First argument value.
     * @return Partially applied function.
     *
     * @since 0.1.0
     */
    default UnaryFunction<T2, R> captureAlpha(T1 alpha) {
        return beta -> apply(alpha, beta);
    }

    /**
     * @param beta Second argument value.
     * @return Partially applied function.
     *
     * @since 0.1.0
     */
    default UnaryFunction<T1, R> captureBeta(T2 beta) {
        return alpha -> apply(alpha, beta);
    }

    /**
     * @param alpha First argument value.
     * @return Partially applied function.
     *
     * @since 0.1.0
     */
    default UnaryFunction<T2, R> capture(T1 alpha) {
        return captureAlpha(alpha);
    }

    /**
     * @param alpha First argument.
     * @param beta Second argument.
     * @return Partially (completely?) applied function.
     *
     * @since 0.1.0
     */
    default Supplier<R> capture(T1 alpha, T2 beta) {
        return capture(alpha).capture(beta);
    }
}
