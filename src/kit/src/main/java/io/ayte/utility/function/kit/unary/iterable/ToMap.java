package io.ayte.utility.function.kit.unary.iterable;

import io.ayte.utility.function.api.TernaryFunction;
import io.ayte.utility.function.api.UnaryFunction;
import io.ayte.utility.function.kit.AugmentedFunction;
import io.ayte.utility.function.kit.unary.operator.standard.Identity;
import io.ayte.utility.supplier.kit.Factories;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.val;

import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

@SuppressWarnings("squid:S1452")
@ToString
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class ToMap<T, K, V, M extends Map<K, V>> implements AugmentedFunction<Iterable<? extends T>, M> {
    private final Supplier<M> factory;
    private final Function<T, K> keyExtractor;
    private final Function<T, V> valueExtractor;
    private final TernaryFunction<K, V, V, V> combiner;

    @Override
    public M apply(Iterable<? extends T> subject) {
        val target = factory.get();
        for (val element : subject) {
            val key = keyExtractor.apply(element);
            val value = valueExtractor.apply(element);
            val resolved = target.containsKey(key) ? combiner.apply(key, target.get(key), value) : value;
            target.put(key, resolved);
        }
        return target;
    }

    public static <T, K, V, M extends Map<K, V>> UnaryFunction<Iterable<? extends T>, M> create(
            @NonNull Supplier<M> factory,
            @NonNull Function<T, K> keyExtractor,
            @NonNull Function<T, V> valueExtractor,
            @NonNull TernaryFunction<K, V, V, V> combiner
    ) {
        return new ToMap<>(factory, keyExtractor, valueExtractor, combiner);
    }

    public static <T, K, V> UnaryFunction<Iterable<? extends T>, Map<K, V>> create(
            @NonNull Function<T, K> keyExtractor,
            @NonNull Function<T, V> valueExtractor,
            @NonNull TernaryFunction<K, V, V, V> combiner
    ) {
        return create(Factories.hashMap(), keyExtractor, valueExtractor, combiner);
    }

    @SuppressWarnings("unchecked")
    public static <T, K, V, M extends Map<K, V>> UnaryFunction<Iterable<? extends T>, M> create(
            @NonNull Supplier<M> factory,
            @NonNull Function<T, K> keyExtractor,
            @NonNull Function<T, V> valueExtractor
    ) {
        return create(factory, keyExtractor, valueExtractor, ThrowingCombiner.INSTANCE);
    }

    public static <T, K, V> UnaryFunction<Iterable<? extends T>, Map<K, V>> create(
            @NonNull Function<T, K> keyExtractor,
            @NonNull Function<T, V> valueExtractor
    ) {
        return create(Factories.hashMap(), keyExtractor, valueExtractor);
    }

    public static <T> UnaryFunction<Iterable<? extends T>, Map<T, T>> create() {
        return create(Identity.create(), Identity.create());
    }

    private static class ThrowingCombiner<K, V> implements TernaryFunction<K, V, V, V> {
        public static final ThrowingCombiner INSTANCE = new ThrowingCombiner();

        @Override
        public V apply(K key, V alpha, V beta) {
            String message = "Key collision found in ToMap function, but no merge function was supplied. " +
                    "Conflicting key was `" + key + "`, values were `" + alpha + "` and `" + beta + "`.";
            throw new IllegalArgumentException(message);
        }
    }
}
