package io.ayte.utility.function.api;

import java.util.function.UnaryOperator;

public interface EnrichedUnaryOperator<T> extends UnaryOperator<T>, UnaryFunction<T, T> {
}
