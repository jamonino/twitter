
package com.albares.twitter.utils;

import com.albares.twitter.db.User;


public class Response {
    
    private Integer responseCode;
    private User user;

    public Integer getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(Integer responseCode) {
        this.responseCode = responseCode;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
}
