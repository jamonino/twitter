
package com.albares.twitter.api;

import com.albares.twitter.db.User;
import com.albares.twitter.utils.Db;
import com.albares.twitter.utils.JWTUtils;
import com.albares.twitter.utils.Response;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Path("/addUser")
public class addUser {
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addUser(User user){  
        try{
            Db myDb = new Db();
            myDb.connect();
            
            PreparedStatement ps = myDb.prepareStatement(
                    "INSERT INTO users (name, pass) VALUES (?, ?) returning id;"
            );
            ps.setString(1, user.getName());
            ps.setString(2, user.getEncodedPass());
            ResultSet rs = myDb.executeQuery(ps);
            
            User userResponse = new User();
            
            rs.next();
            userResponse.setToken(JWTUtils.generateToken(rs.getInt(1)));
            
            Response r = new Response();
            
            r.setUser(userResponse);
            r.setResponseCode(1);
            return r;   
        }catch(Exception e){
            Response r = new Response();
            r.setResponseCode(0);
            return r;   
        }
    }    
}

