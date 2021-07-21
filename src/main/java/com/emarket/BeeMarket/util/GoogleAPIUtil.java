package com.emarket.BeeMarket.util;

import com.emarket.BeeMarket.model.GoogleUser;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;

public class GoogleAPIUtil {

    private static final String GOOGLE_CLIENT_ID = "826429247341-ma24lpmujq81e5v9ddm89dq6rpg0bh01.apps.googleusercontent.com";
    private static final String GOOGLE_SECRET = "7Z00BF6Qa876L4TSDwpwXLJ0";

    public static GoogleUser verifyIDTokens(String idTokenString) {
        HttpTransport transport = new NetHttpTransport();
        JsonFactory jsonFactory = new GsonFactory();
        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(transport, jsonFactory)
                .setAudience(Collections.singletonList(GOOGLE_CLIENT_ID))
                .build();

        GoogleIdToken idToken;
        try {
            idToken = verifier.verify(idTokenString);
            if (idToken != null) {
                Payload payload = idToken.getPayload();

                GoogleUser googleUser = new GoogleUser();
                googleUser.setUserId(payload.getSubject());
                googleUser.setEmail(payload.getEmail());
                googleUser.setEmailVerified(payload.getEmailVerified());
                googleUser.setName((String) payload.get("name"));
                googleUser.setPictureUrl((String) payload.get("picture"));
                googleUser.setLocale((String) payload.get("locale"));
                googleUser.setFamilyName((String) payload.get("family_name"));
                googleUser.setGivenName((String) payload.get("given_name"));
                return googleUser;
            }
        } catch (GeneralSecurityException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
