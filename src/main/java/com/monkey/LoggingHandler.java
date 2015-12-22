package com.monkey;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ratpack.handling.Context;
import ratpack.handling.Handler;

/**
 * An example of a handler implicitly set up by a module
 *
 * @see AppModule
 */
public class LoggingHandler implements Handler {

  final Logger log = LoggerFactory.getLogger(LoggingHandler.class);

  @Override
  public void handle(Context context) {
    log.debug("Received: " + context.getRequest().getUri());
    context.next();
  }
}