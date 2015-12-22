package com.monkey.payment;

import com.braintreegateway.BraintreeGateway;
import com.monkey.model.ClientToken;
import javax.inject.Inject;

public class BraintreeService implements PaymentService {

  private final BraintreeGateway gateway;

  @Inject
  public BraintreeService(BraintreeGateway gateway) {
    this.gateway = gateway;
  }

  @Override public ClientToken getClientToken() {
    return ClientToken.create(gateway.clientToken().generate());
  }
}
