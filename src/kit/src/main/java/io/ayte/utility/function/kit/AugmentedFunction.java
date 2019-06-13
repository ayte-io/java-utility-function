package io.ayte.utility.function.kit;

import io.ayte.utility.function.api.UnaryFunction;
import io.ayte.utility.function.kit.unary.standard.Composition;

import java.util.function.Function;

public interface AugmentedFunction<T, R> extends UnaryFunction<T, R> {
    @Override
    default <V> Function<V, R> compose(Function<? super V, ? extends T> before) {
        return Composition.create(before, this);
    }

    @Override
    default <V> Function<T, V> andThen(Function<? super R, ? extends V> after) {
        return Composition.create(this, after);
    }
}
