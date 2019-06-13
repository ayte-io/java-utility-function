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

import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

@SuppressWarnings("squid:S1452")
@ToString
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class ToList<I, O, L extends List<O>> implements AugmentedFunction<Iterable<? extends I>, L> {
    private final Supplier<L> factory;
    private final Function<I, O> transformer;

    @Override
    public L apply(Iterable<? extends I> subject) {
        val target = factory.get();
        for (val element : subject) {
            target.add(transformer.apply(element));
        }
        return target;
    }

    public static <I, O, L extends List<O>> ToList<I, O, L> create(
            @NonNull Supplier<L> factory,
            @NonNull Function<I, O> transformer
    ) {
        return new ToList<>(factory, transformer);
    }

    public static <I, O> UnaryFunction<Iterable<? extends I>, List<O>> create(@NonNull Function<I, O> transformer) {
        return create(Factories.arrayList(), transformer);
    }

    public static <T> UnaryFunction<Iterable<? extends T>, List<T>> create() {
        return create(Identity.create());
    }

    public static <T, L extends List<T>> UnaryFunction<Iterable<? extends T>, L> create(@NonNull Supplier<L> factory) {
        return create(factory, Identity.create());
    }
}
