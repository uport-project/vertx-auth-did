package me.uport.vertx.auth.did.impl;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.auth.AbstractUser;
import io.vertx.ext.auth.AuthProvider;
import me.uport.sdk.jwt.model.JwtPayload;

public class DIDUser extends AbstractUser {

    private static final Logger log = LoggerFactory.getLogger(DIDUser.class);

    private JwtPayload jwt;
    private JsonArray permissions;

    public DIDUser() {
        // required if the object is serialized, however this is not a good idea
        // because JWT are supposed to be used in stateless environments
        log.info("You are probably serializing the JWT User, JWT are supposed to be used in stateless servers!");
    }

    public DIDUser(JwtPayload jwt, String permissionsClaimKey) {
        this.jwt = jwt;
        this.permissions = new JsonArray();
    }

    @Override
    public JsonObject principal() {
        return (new JsonObject()).put("iss",this.jwt.getIss());
    }

    @Override
    public void setAuthProvider(AuthProvider authProvider) {

    }

    @Override
    protected void doIsPermitted(String permission, Handler<AsyncResult<Boolean>> resultHandler) {

    }

}