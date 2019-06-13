package io.ayte.utility.function.kit;

import io.ayte.utility.function.api.BinaryFunction;
import io.ayte.utility.function.api.UnaryFunction;
import io.ayte.utility.function.kit.binary.capture.AllArgumentsCapturedBinaryFunction;
import io.ayte.utility.function.kit.binary.capture.AlphaCapturedBinaryFunction;
import io.ayte.utility.function.kit.binary.capture.BetaCapturedBinaryFunction;
import io.ayte.utility.function.kit.binary.standard.BinaryComposition;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

public interface AugmentedBinaryFunction<T1, T2, R> extends BinaryFunction<T1, T2, R> {
    @Override
    default UnaryFunction<T2, R> captureAlpha(T1 alpha) {
        return AlphaCapturedBinaryFunction.create(this, alpha);
    }

    @Override
    default UnaryFunction<T1, R> captureBeta(T2 beta) {
        return BetaCapturedBinaryFunction.create(this, beta);
    }

    @Override
    default Supplier<R> capture(T1 alpha, T2 beta) {
        return AllArgumentsCapturedBinaryFunction.create(this, alpha, beta);
    }

    @Override
    default <V> BiFunction<T1, T2, V> andThen(Function<? super R, ? extends V> after) {
        return BinaryComposition.create(this, after);
    }
}
