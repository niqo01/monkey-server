package com.monkey;

import ratpack.handling.Chain;
import ratpack.handling.Handler;
import ratpack.registry.Registry;
import ratpack.server.ServerConfig;

public class PaymentChain implements Chain {
  @Override public ServerConfig getServerConfig() {
    return null;
  }

  @Override public Registry getRegistry() throws IllegalStateException {
    return null;
  }

  @Override public Chain all(Handler handler) {
    return null;
  }
}
