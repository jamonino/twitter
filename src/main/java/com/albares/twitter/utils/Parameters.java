
package com.albares.twitter.utils;

import com.albares.twitter.db.User;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;


public final class Parameters {

    public Parameters() {
    }
    
    public static final String PROJECT_NAME = "Twitter";
        
    
    public static final String DB_URL = "jdbc:postgresql://localhost:5432/twitter_db";
    public static final String DB_USER = "twitter_user";
    public static final String DB_PASS = Secrets.DB_PASS;
    
}
