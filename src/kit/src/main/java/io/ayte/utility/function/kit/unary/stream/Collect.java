package io.ayte.utility.function.kit.unary.stream;

import io.ayte.utility.function.api.UnaryFunction;
import io.ayte.utility.function.kit.AugmentedFunction;
import io.ayte.utility.function.kit.unary.operator.standard.Identity;
import io.ayte.utility.predicate.kit.Predicates;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Stream;

@ToString
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Collect<T, I, R> implements AugmentedFunction<Stream<T>, R> {
    private final Predicate<? super T> filter;
    private final Function<? super T, ? extends I> transformer;
    private final Collector<? super I, ?, ? extends R> collector;

    @Override
    public R apply(Stream<T> subject) {
        return subject
                .filter(filter)
                .map(transformer)
                .collect(collector);
    }

    public static <T, I, R> UnaryFunction<Stream<T>, R> create(
            @NonNull Predicate<? super T> filter,
            @NonNull Function<? super T, ? extends I> transformer,
            @NonNull Collector<? super I, ?, ? extends R> collector
    ) {
        return new Collect<>(filter, transformer, collector);
    }

    public static <T, R> UnaryFunction<Stream<T>, R> create(
            @NonNull Predicate<? super T> filter,
            @NonNull Collector<? super T, ?, ? extends R> collector
    ) {
        return new Collect<>(filter, Identity.create(), collector);
    }

    public static <T, I, R> UnaryFunction<Stream<T>, R> create(
            @NonNull Function<? super T, ? extends I> transformer,
            @NonNull Collector<? super I, ?, ? extends R> collector
    ) {
        return new Collect<>(Predicates.constantTrue(), transformer, collector);
    }

    public static <T, R> UnaryFunction<Stream<T>, R> create(@NonNull Collector<? super T, ?, ? extends R> collector) {
        return new Collect<>(Predicates.constantTrue(), Identity.create(), collector);
    }
}
