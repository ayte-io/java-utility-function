package io.ayte.utility.function.api;

import java.util.function.Function;
import java.util.function.Supplier;

/**
 * This interface extends default {@link Function} with additional
 * helper methods.
 *
 * @param <T> Input type.
 * @param <R> Return type.
 *
 * @since 0.1.0
 */
@FunctionalInterface
public interface UnaryFunction<T, R> extends Function<T, R> {
    /**
     * Captures provided argument, converting function into a supplier
     * which always calls this function with a supplied argument
     *
     * @param argument Argument to use, nullable.
     *
     * @return Partially (completely?) applied function.
     * @since 0.1.0
     */
    default Supplier<R> capture(T argument) {
        return () -> apply(argument);
    }
}
