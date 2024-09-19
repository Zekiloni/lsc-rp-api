package net.lscrp.ucp.common;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class GenericBuilder<T> {

    private final T instance;

    private GenericBuilder(Supplier<T> supplier) {
        this.instance = supplier.get();
    }

    public static <T> GenericBuilder<T> of(Supplier<T> supplier) {
        return new GenericBuilder<>(supplier);
    }

    public GenericBuilder<T> with(Consumer<T> consumer) {
        consumer.accept(instance);
        return this;
    }

    public T build() {
        return instance;
    }
}