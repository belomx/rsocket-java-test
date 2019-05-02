package com.rsocket.interceptor;

import io.rsocket.RSocket;
import io.rsocket.plugins.RSocketInterceptor;
import java.util.Objects;

public final class SessionRSocketInterceptor implements RSocketInterceptor {

  @Override
  public SessionRSocket apply(RSocket delegate) {
    Objects.requireNonNull(delegate, "delegate must not be null");

    return new SessionRSocket(delegate);
  }
}

