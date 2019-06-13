package io.ayte.utility.function.kit;

import io.ayte.utility.function.kit.unary.optional.FlatMap;
import io.ayte.utility.function.kit.unary.optional.Flatten;
import io.ayte.utility.function.kit.unary.optional.Get;
import io.ayte.utility.function.kit.unary.optional.Map;
import io.ayte.utility.function.kit.unary.optional.Or;
import io.ayte.utility.function.kit.unary.optional.OrElse;
import io.ayte.utility.function.kit.unary.optional.OrElseGet;
import io.ayte.utility.function.kit.unary.optional.OrNull;
import io.ayte.utility.function.kit.unary.optional.OrSupply;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

/**
 * Collections of functions related to {@link Optional}.
 *
 * @since 0.1.0
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OptionalFunctions {
    /**
     * @param <I> Identity type.
     * @return Function that unwraps identity value.
     * @since 0.1.0
     */
    public static <I> Function<Optional<I>, I> get() {
        return Get.create();
    }

    /**
     * @param <I> Identity type.
     * @return Function that returns identity value or null.
     * @since 0.1.0
     */
    public static <I> Function<Optional<I>, I> orNull() {
        return OrNull.create();
    }

    /**
     * @param value Fallback value.
     * @param <I> Identity type.
     * @return Function that unwraps identity value or returns provided
     * fallback value.
     * @since 0.1.0
     */
    public static <I> Function<Optional<I>, I> orElse(I value) {
        return OrElse.create(value);
    }

    /**
     * @param supplier Fallback value factory.
     * @param <I> Identity type.
     * @return Function that unwraps identity value or creates new value
     * using provided factory.
     * @since 0.1.0
     */
    public static <I> Function<Optional<I>, I> orElseGet(@NonNull Supplier<? extends I> supplier) {
        return OrElseGet.create(supplier);
    }

    /**
     * @param substitute Optional to return if current optional is
     * empty.
     * @param <I> Identity type.
     * @return Function that either returns current optional (if it has
     * identity) or substitute optional (if current optional is empty).
     * @since 0.1.0
     */
    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    public static <I> UnaryOperator<Optional<I>> or(@NonNull Optional<? extends I> substitute) {
        return Or.create(substitute);
    }

    /**
     * @param factory Factory for optional to return if current optional
     * is empty.
     * @param <I> Identity type.
     * @return Function that either returns current optional (if it has
     * identity) or substitute optional (if current optional is empty).
     * @since 0.1.0
     */
    public static <I> UnaryOperator<Optional<I>> orSupply(@NonNull Supplier<Optional<? extends I>> factory) {
        return OrSupply.create(factory);
    }

    /**
     * @param transformer Function to apply over optional's identity.
     * @param <I> Current identity type.
     * @param <O> Identity type after transformation.
     * @return Function that performs {@link Optional#map(Function)}
     * operation.
     * @since 0.1.0
     */
    public static <I, O> Function<Optional<I>, Optional<O>> map(@NonNull Function<? super I, ? extends O> transformer) {
        return Map.create(transformer);
    }

    /**
     * @param transformer Function to apply over optional's identity.
     * @param <I> Current identity type.
     * @param <O> Identity type after transformation.
     * @return Function that performs {@link Optional#flatMap(Function)}
     * operation.
     * @since 0.1.0
     */
    public static <I, O> Function<Optional<I>, Optional<O>> flatMap(
            @NonNull Function<? super I, Optional<? extends O>> transformer
    ) {
        return FlatMap.create(transformer);
    }

    /**
     * @param <I> Identity type.
     * @return Function that unwraps nested optional.
     * @since 0.1.0
     */
    public static <I> Function<Optional<Optional<I>>, Optional<I>> flatten() {
        return Flatten.create();
    }
}
