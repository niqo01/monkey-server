package com.monkey;

import com.braintreegateway.BraintreeGateway;
import com.braintreegateway.Environment;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.multibindings.Multibinder;
import com.monkey.model.ClientToken;
import com.monkey.payment.BraintreeService;
import com.monkey.payment.PaymentGetTokenHandler;
import com.monkey.payment.PaymentService;
import com.squareup.moshi.Moshi;
import java.io.IOException;
import java.util.Properties;

import javax.inject.Singleton;
import ratpack.handling.HandlerDecorator;


public class AppModule extends AbstractModule {

  /**
   * Adds a service impl to the application, and registers a decorator so that all requests are logged.
   * Registered implementations of {@link ratpack.handling.HandlerDecorator} are able to decorate the application handler.
   *
   * @see PaymentGetTokenHandler
   */
  protected void configure() {
    bind(PaymentService.class).to(BraintreeService.class).in(Singleton.class);
    bind(PaymentGetTokenHandler.class);
    Multibinder.newSetBinder(binder(), HandlerDecorator.class).addBinding().toInstance(HandlerDecorator.prepend(new LoggingHandler()));
  }

  @Provides
  @Singleton
  private Properties getConfigProperties(){
    Properties props = new Properties();
    try {
      props.load(getClass().getResourceAsStream("/config.properties"));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return props;
  }

  @Provides
  @Singleton
  private BraintreeGateway provideBraintreeGateway(Properties properties) {
    String env = properties.getProperty("env");
    Environment environment;
    switch (env){
      case "dev":
        environment = Environment.SANDBOX;
        break;
      case "prod":
        environment = Environment.PRODUCTION;
        break;
      default:
        throw new IllegalStateException("Unknown Env:" + env);
    }
    return new BraintreeGateway(
        environment,
        properties.getProperty("braintree.merchantId"),
        properties.getProperty("braintree.publicKey"),
        properties.getProperty("braintree.privateKey")
    );
  }

  @Provides
  @Singleton
  private Moshi provideMoshi(){
    return new Moshi.Builder()
        .add(ClientToken.typeAdapterFactory())
        .build();
  }
}