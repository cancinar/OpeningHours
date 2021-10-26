package com.cinar.openinghours.base.usecase;

import com.cinar.openinghours.base.usecase.io.Input;
import com.cinar.openinghours.base.usecase.io.Output;
import java.util.function.Function;

public interface UseCase<I extends Input, O extends Output> extends Function<I, O> {

  default O execute(I input) {
    validate(input);
    return apply(input);
  }

  void validate(I input);
}
