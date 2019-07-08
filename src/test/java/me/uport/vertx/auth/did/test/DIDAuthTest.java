package me.uport.vertx.auth.did.test;

import me.uport.vertx.auth.did.DIDAuth;
import me.uport.vertx.auth.did.DIDAuthOptions;

import org.junit.Test;

import io.vertx.core.json.JsonObject;
import io.vertx.test.core.VertxTestBase;

public class DIDAuthTest extends VertxTestBase {

  private DIDAuth authProvider;

  @Override
  public void setUp() throws Exception {
    super.setUp();
    authProvider = DIDAuth.create(vertx, getConfig());
  }

  private DIDAuthOptions getConfig() {
    return null;
  }

  @Test
  public void testValidJWT() {
    JsonObject authInfo = new JsonObject().put("jwt", "ey.some.thing");
    authProvider.authenticate(authInfo, onSuccess(res -> {
        assertNotNull(res);
        testComplete();
    }));
    await();
  }
}