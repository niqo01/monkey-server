package com.monkey.model;

import com.google.auto.value.AutoValue;
import com.squareup.moshi.JsonAdapter;

@AutoValue
public abstract class ClientToken {

  public static ClientToken create(String token) {
    return new AutoValue_ClientToken(token);
  }

  public abstract String token();

  public static JsonAdapter.Factory typeAdapterFactory() {
    return AutoValue_ClientToken.typeAdapterFactory();
  }
}
