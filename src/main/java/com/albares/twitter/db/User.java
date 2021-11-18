
package com.albares.twitter.db;

import static com.albares.twitter.utils.SHAUtils.sha256;
import com.albares.twitter.utils.Secrets;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.security.NoSuchAlgorithmException;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {
    private Integer id;
    private String name;
    private String pass;
    
    
    //Fuera de BBDD
    private String token;

    public User() {
    }

    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
    
    @JsonIgnore //Esta variable no aparece en el JSON de Jackson
    public String getEncodedPass() throws NoSuchAlgorithmException{
        return sha256(this.getPass()+Secrets.SALT_PASS);
    }
    
}
