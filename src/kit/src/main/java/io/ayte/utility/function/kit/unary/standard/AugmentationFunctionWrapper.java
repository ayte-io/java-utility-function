package io.ayte.utility.function.kit.unary.standard;

import io.ayte.utility.function.kit.AugmentedFunction;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.function.Function;

@ToString(includeFieldNames = false)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class AugmentationFunctionWrapper<T, R> implements AugmentedFunction<T, R> {
    private final Function<T, R> delegate;

    @Override
    public R apply(T subject) {
        return delegate.apply(subject);
    }

    public static <T, R> AugmentedFunction<T, R> create(@NonNull Function<T, R> delegate) {
        if (delegate instanceof AugmentedFunction) {
            return (AugmentedFunction<T, R>) delegate;
        }
        return new AugmentationFunctionWrapper<>(delegate);
    }
}
