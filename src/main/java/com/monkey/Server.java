package com.monkey;

import com.monkey.payment.PaymentGetTokenHandler;
import ratpack.guice.Guice;
import ratpack.rx.RxRatpack;
import ratpack.server.RatpackServer;

public class Server {
  public static void main(String... args) throws Exception {
    RxRatpack.initialize();
    RatpackServer.start(server -> server
        .registry(Guice.registry(b -> b.module(AppModule.class)))
        .handlers(chain -> chain
            .prefix("v1", vChain -> vChain.prefix("payment",
                pChain -> pChain.get("token", PaymentGetTokenHandler.class))
            )
            .all(ctx -> ctx.render("Hello!"))
        )
    );
  }
}
