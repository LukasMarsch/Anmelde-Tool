package org.jufe;

import java.util.NoSuchElementException;
import java.util.Objects;

public class Result<T> {

    private final T t;

    private final Throwable e;

    private Result(T t) {
        this.t = t;
        this.e = null;
    }

    private Result(Throwable e) {
        this.t = null;
        this.e = e;
    }

    public static <T> Result<T> of(T t) {
        return new Result<>(Objects.requireNonNull(t));
    }

    public static <T, E extends Throwable> Result<T> of(E e) {
        return new Result<>(Objects.requireNonNullElseGet(e, () -> new NullPointerException("Cannot create Result from null value")));
    }

    public T orElse(T other) {
        if (null == this.t) {
            return other;
        }
        return this.t;
    }

    public boolean isEmpty() {
        return null != this.e;
    }

    public boolean isPreset() {
        return null != this.t;
    }

    public T get() {
        if (this.e != null) {
            throw new NoSuchElementException("Element is not present!", e);
        } else {
            return this.t;
        }
    }

    public Throwable getCause() {
        return this.e;
    }

}
