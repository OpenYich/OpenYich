package com.openyich.framework.boot.utils;

import java.util.Optional;
import java.util.function.Supplier;

import org.springframework.lang.NonNull;

/**
 * Assert support for OpenYich validation.
 */
public class OpenYichAssert {

  public static <T> T notNull(@NonNull Supplier<T> supplier) throws Exception {
    return notNull(supplier, () -> new Exception("Return object is null"));
  }

  public static <T> T notNull(@NonNull Supplier<T> supplier,
      @NonNull Supplier<? extends Exception> exceptionSupplier) throws Exception {
    return Optional.ofNullable(supplier.get()).orElseThrow(exceptionSupplier);
  }

  public static <T> T notNullWithFailover(@NonNull Supplier<T> supplier, Supplier<T> failover)
      throws Exception {
    return notNullWithFailover(supplier, failover, () -> new Exception("Return object is null"));
  }

  public static <T> T notNullWithFailover(@NonNull Supplier<T> supplier,
      @NonNull Supplier<T> failover, @NonNull Supplier<? extends Exception> exceptionSupplier)
      throws Exception {
    return Optional.ofNullable(Optional.ofNullable(supplier.get()).orElseGet(failover))
        .orElseThrow(exceptionSupplier);
  }

}
