package com.monkey.payment;

import com.monkey.AppModule;
import com.monkey.model.ClientToken;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import javax.inject.Inject;
import javax.inject.Singleton;
import ratpack.handling.Context;
import ratpack.handling.Handler;

/**
 * A handler implementation that is created via dependency injection.
 *
 * @see AppModule
 */
@Singleton
public class PaymentGetTokenHandler implements Handler {

  private final PaymentService paymentService;
  private final Moshi moshi;

  @Inject
  public PaymentGetTokenHandler(PaymentService paymentService, Moshi moshi) {
    this.paymentService = paymentService;
    this.moshi = moshi;
  }

  @Override
  public void handle(Context context)  throws Exception {
    JsonAdapter<ClientToken> jsonAdapter = moshi.adapter(ClientToken.class);
    context.render(jsonAdapter.toJson(paymentService.getClientToken()));
  }
}