package io.ayte.utility.function.kit.unary.iterable;

import io.ayte.utility.function.api.UnaryFunction;
import io.ayte.utility.function.kit.AugmentedFunction;
import io.ayte.utility.function.kit.unary.operator.standard.Identity;
import io.ayte.utility.supplier.kit.Factories;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.val;

import java.util.Set;
import java.util.function.Function;
import java.util.function.Supplier;

@SuppressWarnings("squid:S1452")
@ToString
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class ToSet<I, O, S extends Set<O>> implements AugmentedFunction<Iterable<? extends I>, S> {
    private final Supplier<S> factory;
    private final Function<I, O> transformer;

    @Override
    public S apply(@NonNull Iterable<? extends I> subject) {
        val target = factory.get();
        for (val element : subject) {
            target.add(transformer.apply(element));
        }
        return target;
    }

    public static <I, O, S extends Set<O>> UnaryFunction<Iterable<? extends I>, S> create(
            @NonNull Supplier<S> factory,
            @NonNull Function<I, O> transformer
    ) {
        return new ToSet<>(factory, transformer);
    }

    public static <I, O> UnaryFunction<Iterable<? extends I>, Set<O>> create(@NonNull Function<I, O> transformer) {
        return create(Factories.hashSet(), transformer);
    }

    public static <E, S extends Set<E>> UnaryFunction<Iterable<? extends E>, S> create(@NonNull Supplier<S> factory) {
        return create(factory, Identity.create());
    }

    public static <E> UnaryFunction<Iterable<? extends E>, Set<E>> create() {
        return create(Identity.create());
    }
}
