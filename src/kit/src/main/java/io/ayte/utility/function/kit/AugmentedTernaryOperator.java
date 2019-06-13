package io.ayte.utility.function.kit;

import io.ayte.utility.function.api.TernaryOperator;

public interface AugmentedTernaryOperator<T> extends TernaryOperator<T>, AugmentedTernaryFunction<T, T, T, T> {}
