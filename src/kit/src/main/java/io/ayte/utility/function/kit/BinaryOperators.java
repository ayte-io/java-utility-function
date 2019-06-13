package io.ayte.utility.function.kit;

import io.ayte.utility.function.api.EnrichedBinaryOperator;
import io.ayte.utility.function.kit.binary.operator.comparing.Max;
import io.ayte.utility.function.kit.binary.operator.comparing.Min;
import io.ayte.utility.function.kit.binary.operator.standard.OperatorAugmentationWrapper;
import io.ayte.utility.function.kit.binary.operator.standard.FunctionToOperatorWrapper;
import io.ayte.utility.function.kit.binary.operator.standard.Producer;
import io.ayte.utility.function.kit.binary.operator.standard.UsingFirst;
import io.ayte.utility.function.kit.binary.operator.standard.UsingSecond;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.Comparator;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Supplier;

/**
 * @since 0.1.0
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BinaryOperators {
    /**
     * @param comparator Comparator to find out relationships between
     * values.
     * @param <T> Input type.
     * @return Binary operator that selects minimum of two values.
     * @see #min()
     * @since 0.1.0
     */
    public static <T> EnrichedBinaryOperator<T> min(@NonNull Comparator<? super T> comparator) {
        return Min.create(comparator);
    }

    /**
     * @param comparator Comparator to find out relationships between
     * values.
     * @param <T> Input type.
     * @return Binary operator that selects maximum of two values.
     * @see #max()
     * @since 0.1.0
     */
    public static <T> EnrichedBinaryOperator<T> max(@NonNull Comparator<? super T> comparator) {
        return Max.create(comparator);
    }

    /**
     * @param <T> Input type.
     * @return Binary operator that selects minimum of two values.
     * @see #min(Comparator)
     * @since 0.1.0
     */
    public static <T extends Comparable<T>> EnrichedBinaryOperator<T> min() {
        return Min.create();
    }

    /**
     * @param <T> Input type.
     * @return Binary operator that selects maximum of two values.
     * @see #max(Comparator)
     * @since 0.1.0
     */
    public static <T extends Comparable<T>> EnrichedBinaryOperator<T> max() {
        return Max.create();
    }

    /**
     * @param <T> Input type.
     * @return Operator that always returns it's first argument.
     * @see #second()
     * @since 0.1.0
     */
    public static <T> EnrichedBinaryOperator<T> first() {
        return UsingFirst.create();
    }

    /**
     * @param <T> Input type.
     * @return Operator that always returns it's second argument.
     * @see #first()
     * @since 0.1.0
     */
    public static <T> EnrichedBinaryOperator<T> second() {
        return UsingSecond.create();
    }

    /**
     * Returns provided operator coerced (by casting or wrapping) to
     * target interface.
     *
     * @param subject Operator to convert.
     * @param <T> Operator type.
     * @return Wrapped or casted operator.
     * @since 0.1.0
     */
    public static <T> AugmentedBinaryOperator<T> augment(@NonNull BinaryOperator<T> subject) {
        return OperatorAugmentationWrapper.create(subject);
    }

    /**
     * Returns provided function as binary operator, either via casting
     * or wrapping.
     *
     * @param subject Function to convert.
     * @param <T> Operator/Function type.
     * @return Provided function converted to binary operator
     * @since 0.1.0
     */
    public static <T> BinaryOperator<T> create(@NonNull BiFunction<T, T, T> subject) {
        return FunctionToOperatorWrapper.create(subject);
    }

    /**
     * @param producer Supplier that will be used to generate return
     * values.
     * @param <T> Operator type.
     * @return Operator that uses provided supplier to create returned
     * values, completely ignoring input.
     * @since 0.1.0
     */
    public static <T> EnrichedBinaryOperator<T> create(@NonNull Supplier<T> producer) {
        return Producer.create(producer);
    }
}
