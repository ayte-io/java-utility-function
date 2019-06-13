package io.ayte.utility.function.kit;

import io.ayte.utility.function.api.TernaryFunction;
import io.ayte.utility.function.api.TernaryOperator;
import io.ayte.utility.function.kit.ternary.operator.comparing.Max;
import io.ayte.utility.function.kit.ternary.operator.comparing.Min;
import io.ayte.utility.function.kit.ternary.operator.standard.OperatorAugmentationWrapper;
import io.ayte.utility.function.kit.ternary.operator.standard.Producer;
import io.ayte.utility.function.kit.ternary.operator.standard.FunctionToOperatorWrapper;
import io.ayte.utility.function.kit.ternary.operator.standard.UsingFirst;
import io.ayte.utility.function.kit.ternary.operator.standard.UsingSecond;
import io.ayte.utility.function.kit.ternary.operator.standard.UsingThird;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.Comparator;
import java.util.function.Supplier;

/**
 * @since 0.1.0
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TernaryOperators {
    /**
     * @param <T> Operation type.
     * @return Ternary operator that always returns first argument.
     * @since 0.1.0
     */
    public static <T> TernaryOperator<T> first() {
        return UsingFirst.create();
    }

    /**
     * @param <T> Operation type.
     * @return Ternary operator that always returns second argument.
     * @since 0.1.0
     */
    public static <T> TernaryOperator<T> second() {
        return UsingSecond.create();
    }

    /**
     * @param <T> Operation type.
     * @return Ternary operator that always returns third argument.
     * @since 0.1.0
     */
    public static <T> TernaryOperator<T> third() {
        return UsingThird.create();
    }

    /**
     * @param comparator Comparator used to find out values relationships.
     * @param <T> Operation type.
     * @return Operator that returns smallest of provided arguments.
     * @since 0.1.0
     */
    public static <T> TernaryOperator<T> min(@NonNull Comparator<T> comparator) {
        return Min.create(comparator);
    }

    /**
     * @param comparator Comparator used to find out values relationships.
     * @param <T> Operation type.
     * @return Operator that returns biggest of provided arguments.
     * @since 0.1.0
     */
    public static <T> TernaryOperator<T> max(@NonNull Comparator<T> comparator) {
        return Max.create(comparator);
    }

    /**
     * @see #min(Comparator)
     * @param <T> Operation type.
     * @return Operator that returns smallest of provided arguments.
     * @since 0.1.0
     */
    public static <T extends Comparable<T>> TernaryOperator<T> min() {
        return Min.create();
    }

    /**
     * @see #max(Comparator)
     * @param <T> Operation type.
     * @return Operator that returns biggest of provided arguments.
     * @since 0.1.0
     */
    public static <T extends Comparable<T>> TernaryOperator<T> max() {
        return Max.create();
    }

    /**
     * Wraps or casts operator so the result implements corresponding
     * interface.
     *
     * @param subject Operator to convert to augmented operator.
     * @param <T> Operator type.
     * @return Casted or wrapped operator.
     * @since 0.1.0
     */
    public static <T> AugmentedTernaryOperator<T> augment(@NonNull TernaryOperator<T> subject) {
        return OperatorAugmentationWrapper.create(subject);
    }

    /**
     * @param subject Function to convert into operator.
     * @param <T> Operator/Function type.
     * @return Subject converted or casted to ternary operator.
     * @since 0.1.0
     */
    public static <T> TernaryOperator<T> create(@NonNull TernaryFunction<T, T, T, T> subject) {
        return FunctionToOperatorWrapper.create(subject);
    }

    /**
     * @param supplier Supplier to be used as a source of values.
     * @param <T> Operator type.
     * @return Ternary operator that always fetches it's value from
     * supplier.
     * @since 0.1.0
     */
    public static <T> TernaryOperator<T> create(@NonNull Supplier<? extends T> supplier) {
        return Producer.create(supplier);
    }
}
