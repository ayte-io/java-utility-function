package io.ayte.utility.function.api;

import java.util.function.BinaryOperator;

public interface EnrichedBinaryOperator<T> extends BinaryOperator<T>, BinaryFunction<T, T, T> {}
