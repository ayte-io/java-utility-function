package io.ayte.utility.function.api;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @see Function
 * @see BiFunction
 *
 * @param <T1> First argument type.
 * @param <T2> Second argument type.
 * @param <T3> Third argument type.
 * @param <R> Return type.
 *
 * @since 0.1.0
 */
@FunctionalInterface
public interface TernaryFunction<T1, T2, T3, R> {
    R apply(T1 alpha, T2 beta, T3 gamma);

    default <R2> TernaryFunction<T1, T2, T3, R2> andThen(Function<? super R, ? extends R2> transformer) {
        return (alpha, beta, gamma) -> transformer.apply(apply(alpha, beta, gamma));
    }

    default BinaryFunction<T2, T3, R> captureAlpha(T1 alpha) {
        return (beta, gamma) -> apply(alpha, beta, gamma);
    }

    default BinaryFunction<T1, T3, R> captureBeta(T2 beta) {
        return (alpha, gamma) -> apply(alpha, beta, gamma);
    }

    default BinaryFunction<T1, T2, R> captureGamma(T3 gamma) {
        return (alpha, beta) -> apply(alpha, beta, gamma);
    }

    default BinaryFunction<T2, T3, R> capture(T1 alpha) {
        return (beta, gamma) -> apply(alpha, beta, gamma);
    }

    default UnaryFunction<T3, R> capture(T1 alpha, T2 beta) {
        return capture(alpha).capture(beta);
    }

    default Supplier<R> capture(T1 alpha, T2 beta, T3 gamma) {
        return capture(alpha, beta).capture(gamma);
    }
}
