package com.weather.progressor.session;


import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class SessionManager {

    private final Map<String, Object> sessionStore = new ConcurrentHashMap<>();

    public void createSession(String sessionId, String username) {
        sessionStore.put(sessionId, username);
    }

    public boolean isValid(String sessionId){
        return sessionStore.containsKey(sessionId);
    }
}
