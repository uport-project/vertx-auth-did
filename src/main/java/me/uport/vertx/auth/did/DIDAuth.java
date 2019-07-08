/*
 * Copyright 2019 uPort?.
 *
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Apache License v2.0 
 *  which accompanies this distribution.
 *
 *  The Apache License v2.0 is available at
 *  http://www.opensource.org/licenses/apache2.0.php
 *
 *  You may elect to redistribute this code under either of these licenses.
 */

package me.uport.vertx.auth.did;

import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.AuthProvider;
import me.uport.vertx.auth.did.DIDAuthOptions;
import me.uport.vertx.auth.did.impl.DIDAuthProviderImpl;

/**Z
 * Factory interface for creating DID based {@link io.vertx.ext.auth.AuthProvider} instances.
 *
 * @author Andres Junge
 */
public interface DIDAuth extends AuthProvider {

  /**
   * Create a DID auth provider
   *
   * @param vertx the Vertx instance
   * @param config  the config
   * @return the auth provider
   */
  @Deprecated
  static DIDAuth create(Vertx vertx, JsonObject config) {
    return create(vertx, new DIDAuthOptions(config));
  }

  /**
   * Create a JWT auth provider
   *
   * @param vertx the Vertx instance
   * @param config  the config
   * @return the auth provider
   */
  static DIDAuth create(Vertx vertx, DIDAuthOptions config) {
    return new DIDAuthProviderImpl(vertx, config);
  }

}