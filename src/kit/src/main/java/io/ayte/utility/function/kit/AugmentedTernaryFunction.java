package io.ayte.utility.function.kit;

import io.ayte.utility.function.api.BinaryFunction;
import io.ayte.utility.function.api.TernaryFunction;
import io.ayte.utility.function.api.UnaryFunction;
import io.ayte.utility.function.kit.ternary.capture.AllArgumentsCapturedTernaryFunction;
import io.ayte.utility.function.kit.ternary.capture.AlphaBetaCapturedTernaryFunction;
import io.ayte.utility.function.kit.ternary.capture.AlphaCapturedTernaryFunction;
import io.ayte.utility.function.kit.ternary.capture.BetaCapturedTernaryFunction;
import io.ayte.utility.function.kit.ternary.capture.GammaCapturedTernaryFunction;
import io.ayte.utility.function.kit.ternary.standard.TernaryComposition;

import java.util.function.Function;
import java.util.function.Supplier;

public interface AugmentedTernaryFunction<T1, T2, T3, R> extends TernaryFunction<T1, T2, T3, R> {
    @Override
    default <R2> TernaryFunction<T1, T2, T3, R2> andThen(Function<? super R, ? extends R2> transformer) {
        return TernaryComposition.create(this, transformer);
    }

    @Override
    default BinaryFunction<T2, T3, R> captureAlpha(T1 alpha) {
        return AlphaCapturedTernaryFunction.create(this, alpha);
    }

    @Override
    default BinaryFunction<T1, T3, R> captureBeta(T2 beta) {
        return BetaCapturedTernaryFunction.create(this, beta);
    }

    @Override
    default BinaryFunction<T1, T2, R> captureGamma(T3 gamma) {
        return GammaCapturedTernaryFunction.create(this, gamma);
    }

    @Override
    default UnaryFunction<T3, R> capture(T1 alpha, T2 beta) {
        return AlphaBetaCapturedTernaryFunction.create(this, alpha, beta);
    }

    @Override
    default Supplier<R> capture(T1 alpha, T2 beta, T3 gamma) {
        return AllArgumentsCapturedTernaryFunction.create(this, alpha, beta, gamma);
    }
}
