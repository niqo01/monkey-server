package com.monkey;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import ratpack.test.MainClassApplicationUnderTest;

import static com.google.common.truth.Truth.assertThat;

@RunWith(JUnit4.class)
public class MonkeysArmyServerTest {

  MainClassApplicationUnderTest aut = new MainClassApplicationUnderTest(Server.class);

  @After
  public void tearDown() {
    aut.close();
  }

  @Test
  public void fooHandler() {
    assertThat(get("foo")).isEqualTo("Hello foo!");
  }

  private String get(String path) {
    return aut.getHttpClient().getText(path);
  }
}
