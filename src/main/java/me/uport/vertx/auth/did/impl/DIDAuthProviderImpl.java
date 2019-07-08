package me.uport.vertx.auth.did.impl;

import java.util.concurrent.CompletableFuture;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.User;
import me.uport.sdk.jwt.model.JwtPayload;
import me.uport.sdk.jwtjava.JWTTools;
import me.uport.vertx.auth.did.DIDAuth;
import me.uport.vertx.auth.did.DIDAuthOptions;

public class DIDAuthProviderImpl implements DIDAuth {

    JWTTools tools;

    public DIDAuthProviderImpl(Vertx vertx, DIDAuthOptions config) {
        //TODO: Add config for private registry
        tools = new JWTTools();
    }

    @Override
    public void authenticate(JsonObject authInfo, Handler<AsyncResult<User>> resultHandler) {
        
        if(!authInfo.containsKey("jwt")){
            resultHandler.handle(Future.failedFuture("Missing JWT token."));
        }

        final String jwt = authInfo.getString("jwt");

        
        CompletableFuture<JwtPayload> jwtPayloadCompletableFuture = tools.verifyAsync(jwt,false,null);

        JwtPayload result = jwtPayloadCompletableFuture.join();   
        
        resultHandler.handle(Future.succeededFuture(new DIDUser(result, "")));
        return;
    }
    
}