package io.ayte.utility.function.api;

/**
 * A function that takes three operands of same type and returns value
 * of that type.
 *
 * @param <T> Operator type.
 *
 * @since 0.1.0
 */
@FunctionalInterface
public interface TernaryOperator<T> extends TernaryFunction<T, T, T, T> {}
