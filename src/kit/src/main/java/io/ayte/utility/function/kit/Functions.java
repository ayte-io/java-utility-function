package io.ayte.utility.function.kit;

import io.ayte.utility.function.api.TernaryFunction;
import io.ayte.utility.function.api.UnaryFunction;
import io.ayte.utility.function.kit.unary.iterable.Max;
import io.ayte.utility.function.kit.unary.iterable.Min;
import io.ayte.utility.function.kit.unary.iterable.ToList;
import io.ayte.utility.function.kit.unary.iterable.ToMap;
import io.ayte.utility.function.kit.unary.iterable.ToSet;
import io.ayte.utility.function.kit.unary.standard.AugmentationFunctionWrapper;
import io.ayte.utility.function.kit.unary.standard.Composition;
import io.ayte.utility.function.kit.unary.stream.Collect;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

/**
 * @since 0.1.0
 */
@SuppressWarnings({"squid:S1452", "unused"})
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Functions {
    /**
     * @param <T> Operation type.
     * @return Simple function that returns provided argument.
     * @since 0.1.0
     */
    public static <T> UnaryOperator<T> identity() {
        return Operators.identity();
    }

    /**
     * @param former First (outer) function.
     * @param latter Second (inner) function.
     * @param <I> Input type.
     * @param <M> Intermediate type.
     * @param <O> Output type.
     * @return Composition of two functions, where output of former one
     * is fed into input of latter one.
     * @since 0.1.0
     */
    public static <I, M, O> UnaryFunction<I, O> compose(
            @NonNull Function<? super I, M> former,
            @NonNull Function<? super M, ? extends O> latter
    ) {
        return Composition.create(former, latter);
    }

    /**
     * @param <E> Iterable element type.
     * @return Function that converts iterable to list.
     * @see #toList(Supplier)
     * @see #toList(Function)
     * @see #toList(Supplier, Function)
     * @since 0.1.0
     */
    public static <E> UnaryFunction<Iterable<? extends E>, List<E>> toList() {
        return ToList.create();
    }

    /**
     * @param factory Empty list factory which allows to construct list
     * of custom type.
     * @param <E> Element type.
     * @param <L> List type.
     * @return Function that converts iterable to list using provided
     * factory to instantiate the list.
     * @see #toList()
     * @see #toList(Function)
     * @see #toList(Supplier, Function)
     * @since 0.1.0
     */
    public static <E, L extends List<E>> UnaryFunction<Iterable<? extends E>, L> toList(@NonNull Supplier<L> factory) {
        return ToList.create(factory);
    }

    /**
     *
     * @param transformer Transformer function that is used to convert
     * elements.
     * @param <E> Original element type.
     * @param <O> Converted element type.
     * @see #toList()
     * @see #toList(Supplier)
     * @see #toList(Supplier, Function)
     * @return Function that converts every element of iterable and
     * collects results to list.
     * @since 0.1.0
     */
    public static <E, O> UnaryFunction<Iterable<? extends E>, List<O>> toList(@NonNull Function<E, O> transformer) {
        return ToList.create(transformer);
    }

    /**
     * @param factory Factory that provides empty list.
     * @param transformer Transformer function that is used to convert
     * elements.
     * @param <E> Original element type.
     * @param <O> Converted element type.
     * @param <L> Resulting list type.
     * @see #toList()
     * @see #toList(Supplier)
     * @see #toList(Function)
     * @return Function that converts every element of iterable and
     * collects results to list using provided factory to instantiate
     * list.
     * @since 0.1.0
     */
    public static <E, O, L extends List<O>> UnaryFunction<Iterable<? extends E>, L> toList(
            @NonNull Supplier<L> factory,
            @NonNull Function<E, O> transformer
    ) {
        return ToList.create(factory, transformer);
    }

    /**
     * @param <E> Element type.
     * @return Function that collects iterable into set.
     * @since 0.1.0
     */
    public static <E> UnaryFunction<Iterable<? extends E>, Set<E>> toSet() {
        return ToSet.create();
    }

    /**
     * @param factory Set factory.
     * @param <E> Element type.
     * @param <S> Set type.
     * @return Function that collects iterable into set instantiated
     * using provided factory.
     * @since 0.1.0
     */
    public static <E, S extends Set<E>> UnaryFunction<Iterable<? extends E>, S> toSet(@NonNull Supplier<S> factory) {
        return ToSet.create(factory);
    }

    /**
     * @param transformer Function used to convert iterable elements.
     * @param <E> Original element type.
     * @param <O> Resulting element type.
     * @return Function that collects iterable into set, converting
     * every element.
     * @since 0.1.0
     */
    public static <E, O> UnaryFunction<Iterable<? extends E>, Set<O>> toSet(@NonNull Function<E, O> transformer) {
        return ToSet.create(transformer);
    }

    /**
     * @param factory Set factory.
     * @param transformer Function used to convert iterable elements.
     * @param <E> Original element type.
     * @param <O> Resulting element type.
     * @param <S> Set type.
     * @return Function that converts and collects iterable elements
     * into set instantiated using provided factory.
     * @since 0.1.0
     */
    public static <E, O, S extends Set<O>> UnaryFunction<Iterable<? extends E>, S> toSet(
            @NonNull Supplier<S> factory,
            @NonNull Function<E, O> transformer
    ) {
        return ToSet.create(factory, transformer);
    }

    public static <E> UnaryFunction<Iterable<? extends E>, Map<E, E>> toMap() {
        return ToMap.create();
    }

    /**
     * @param keyGenerator Function used to generate key from original
     * element.
     * @param valueGenerator Function used to generate value from
     * original element.
     * @param <E> Element type.
     * @param <K> Map key type.
     * @param <V> Map value type.
     * @return Function that collects iterable into map.
     * @see #toMap(Function, Function, TernaryFunction)
     * @see #toMap(Supplier, Function, Function)
     * @see #toMap(Supplier, Function, Function, TernaryFunction)
     * @since 0.1.0
     */
    public static <E, K, V> UnaryFunction<Iterable<? extends E>, Map<K, V>> toMap(
            @NonNull Function<E, K> keyGenerator,
            @NonNull Function<E, V> valueGenerator
    ) {
        return ToMap.create(keyGenerator, valueGenerator);
    }

    /**
     * @param keyGenerator Function used to generate key from original
     * element.
     * @param valueGenerator Function used to generate value from
     * original element.
     * @param combiner Function used to merge values that ended up
     * having same key.
     * @param <E> Element type.
     * @param <K> Map key type.
     * @param <V> Map value type.
     * @return Function that collects iterable into map.
     * @see #toMap(Function, Function)
     * @see #toMap(Supplier, Function, Function)
     * @see #toMap(Supplier, Function, Function, TernaryFunction)
     * @since 0.1.0
     */
    public static <E, K, V> UnaryFunction<Iterable<? extends E>, Map<K, V>> toMap(
            @NonNull Function<E, K> keyGenerator,
            @NonNull Function<E, V> valueGenerator,
            @NonNull TernaryFunction<K, V, V, V> combiner
    ) {
        return ToMap.create(keyGenerator, valueGenerator, combiner);
    }

    /**
     * @param factory Supplier used to create empty map.
     * @param keyGenerator Function used to generate key from original
     * element.
     * @param valueGenerator Function used to generate value from
     * original element.
     * @param <E> Element type.
     * @param <K> Map key type.
     * @param <V> Map value type.
     * @param <M> Map type.
     * @return Function that collects iterable into map.
     * @see #toMap(Function, Function)
     * @see #toMap(Function, Function, TernaryFunction)
     * @see #toMap(Supplier, Function, Function, TernaryFunction)
     * @since 0.1.0
     */
    public static <E, K, V, M extends Map<K, V>> UnaryFunction<Iterable<? extends E>, M> toMap(
            @NonNull Supplier<M> factory,
            @NonNull Function<E, K> keyGenerator,
            @NonNull Function<E, V> valueGenerator
    ) {
        return ToMap.create(factory, keyGenerator, valueGenerator);
    }

    /**
     * @param factory Supplier used to create empty map.
     * @param keyGenerator Function used to generate key from original
     * element.
     * @param valueGenerator Function used to generate value from
     * original element.
     * @param combiner Function used to merge values that ended up
     * having same key.
     * @param <E> Element type.
     * @param <K> Map key type.
     * @param <V> Map value type.
     * @param <M> Map type.
     * @return Function that collects iterable into map.
     * @see #toMap(Function, Function)
     * @see #toMap(Supplier, Function, Function)
     * @see #toMap(Function, Function, TernaryFunction)
     * @since 0.1.0
     */
    public static <E, K, V, M extends Map<K, V>> UnaryFunction<Iterable<? extends E>, M> toMap(
            @NonNull Supplier<M> factory,
            @NonNull Function<E, K> keyGenerator,
            @NonNull Function<E, V> valueGenerator,
            @NonNull TernaryFunction<K, V, V, V> combiner
    ) {
        return ToMap.create(factory, keyGenerator, valueGenerator, combiner);
    }

    /**
     * @param comparator Comparator to compare iterable elements.
     * @param <E> Iterable element type.
     * @return Function that extracts maximum element from an iterable.
     * @since 0.1.0
     */
    public static <E> UnaryFunction<Iterable<E>, E> max(@NonNull Comparator<E> comparator) {
        return Max.create(comparator);
    }

    /**
     * @param <E> Iterable element type.
     * @return Function that extracts maximum element from an iterable.
     * @since 0.1.0
     */
    public static <E extends Comparable<E>> UnaryFunction<Iterable<E>, E> max() {
        return Max.create();
    }

    /**
     * @param comparator Comparator to compare iterable elements.
     * @param <E> Iterable element type.
     * @return Function that extracts minimum element from an iterable.
     * @since 0.1.0
     */
    public static <E> UnaryFunction<Iterable<E>, E> min(@NonNull Comparator<E> comparator) {
        return Min.create(comparator);
    }

    /**
     * @param <E> Iterable element type.
     * @return Function that extracts minimum element from an iterable.
     * @since 0.1.0
     */
    public static <E extends Comparable<E>> UnaryFunction<Iterable<E>, E> min() {
        return Min.create();
    }

    /**
     * Returns augmented function, either by casting or wrapping in
     * special wrapper.
     *
     * @param subject Function to augment.
     * @param <T> Input type.
     * @param <R> Return type.
     * @return Augmented function.
     * @since 0.1.0
     */
    public static <T, R> AugmentedFunction<T, R> augment(@NonNull Function<T, R> subject) {
        return AugmentationFunctionWrapper.create(subject);
    }
}
