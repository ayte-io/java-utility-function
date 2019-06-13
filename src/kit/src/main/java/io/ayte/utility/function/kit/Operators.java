package io.ayte.utility.function.kit;

import io.ayte.utility.function.api.EnrichedUnaryOperator;
import io.ayte.utility.function.kit.unary.operator.standard.OperatorAugmentationWrapper;
import io.ayte.utility.function.kit.unary.operator.standard.Producer;
import io.ayte.utility.function.kit.unary.operator.standard.Conditional;
import io.ayte.utility.function.kit.unary.operator.standard.Identity;
import io.ayte.utility.function.kit.unary.operator.standard.FunctionToOperatorWrapper;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

/**
 * @since 0.1.0
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Operators {
    /**
     * @param <T> Operation type.
     * @return Simplest operator that returns whatever's been passed.
     * @since 0.1.0
     */
    public static <T> EnrichedUnaryOperator<T> identity() {
        return Identity.create();
    }

    /**
     * @param trigger Helper to make a decision whether to carry out
     * operation or not.
     * @param operator Operator.
     * @param <T> Operation type.
     * @return Operator that overwrites value with computed by provided
     * factory if trigger predicate returns true on provided value.
     * @since 0.1.0
     */
    public static <T> EnrichedUnaryOperator<T> conditional(@NonNull Predicate<T> trigger, @NonNull UnaryOperator<T> operator) {
        return Conditional.create(trigger, operator);
    }

    /**
     * @param trigger Helper to make a decision whether to carry out
     * operation or not.
     * @param factory Value factory.
     * @param <T> Operation type.
     * @return Operator that overwrites value with computed by provided
     * factory if trigger predicate returns true on provided value.
     * @since 0.1.0
     */
    public static <T> EnrichedUnaryOperator<T> conditional(@NonNull Predicate<T> trigger, @NonNull Supplier<T> factory) {
        return Conditional.create(trigger, factory);
    }

    /**
     * Creates unary operator from supplier
     *
     * @param supplier Value factory.
     * @param <T> Operation type.
     * @return Operator that always overwrites value with computed by
     * provided factory.
     * @since 0.1.0
     */
    public static <T> EnrichedUnaryOperator<T> create(@NonNull Supplier<T> supplier) {
        return Producer.create(supplier);
    }

    /**
     * Casts or wraps provided operator so it can be used as augmented
     * unary operator.
     *
     * @param subject Operator to process.
     * @param <T> Operator type.
     * @return Casted or wrapped operator.
     * @since 0.1.0
     */
    public static <T> AugmentedUnaryOperator<T> augment(@NonNull UnaryOperator<T> subject) {
        return OperatorAugmentationWrapper.create(subject);
    }

    /**
     * Wraps or casts function so it satisfies operator interface.
     *
     * @param subject Function to convert.
     * @param <T> Function/operator type.
     * @return Function converted into operator.
     * @since 0.1.0
     */
    public static <T> UnaryOperator<T> create(@NonNull Function<T, T> subject) {
        return FunctionToOperatorWrapper.create(subject);
    }
}
