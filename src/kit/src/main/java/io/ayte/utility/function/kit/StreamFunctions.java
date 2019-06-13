package io.ayte.utility.function.kit;

import io.ayte.utility.function.api.UnaryFunction;
import io.ayte.utility.function.kit.unary.stream.Collect;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Stream;

/**
 * @since 0.1.0
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StreamFunctions {
    /**
     * @param filter Predicate pre-filtering the collected stream.
     * @param transformer Transformer function.
     * @param collector Collector to be used.
     * @param <T> Input type.
     * @param <I> Transformed type.
     * @param <R> Return type.
     * @return Function that collects stream.
     * @since 0.1.0
     */
    public static <T, I, R> UnaryFunction<Stream<T>, R> collect(
            @NonNull Predicate<? super T> filter,
            @NonNull Function<? super T, ? extends I> transformer,
            @NonNull Collector<? super I, ?, ? extends R> collector
    ) {
        return Collect.create(filter, transformer, collector);
    }

    /**
     * @param filter Predicate pre-filtering the collected stream.
     * @param collector Collector to be used.
     * @param <T> Input type.
     * @param <R> Return type.
     * @return Function that collects stream.
     * @since 0.1.0
     */
    public static <T, R> UnaryFunction<Stream<T>, R> collect(
            @NonNull Predicate<? super T> filter,
            @NonNull Collector<? super T, ?, ? extends R> collector
    ) {
        return Collect.create(filter, collector);
    }

    /**
     * @param transformer Transformer function.
     * @param collector Collector to be used.
     * @param <T> Input type.
     * @param <I> Transformed type.
     * @param <R> Return type.
     * @return Function that collects stream.
     * @since 0.1.0
     */
    public static <T, I, R> UnaryFunction<Stream<T>, R> collect(
            @NonNull Function<? super T, ? extends I> transformer,
            @NonNull Collector<? super I, ?, ? extends R> collector
    ) {
        return Collect.create(transformer, collector);
    }

    /**
     * @param collector Collector to be used.
     * @param <T> Input type.
     * @param <R> Return type.
     * @return Function that collects stream.
     * @since 0.1.0
     */
    public static <T, R> UnaryFunction<Stream<T>, R> collect(@NonNull Collector<? super T, ?, ? extends R> collector) {
        return Collect.create(collector);
    }
}
