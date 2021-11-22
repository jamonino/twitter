
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

@Path("/getUser")
public class getUser {
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getUser(User user){  
        try{
            user.setId(JWTUtils.checkJWTandGetUserId(user.getToken()));
            Response r = new Response();
            
            Db myDb = new Db();
            myDb.connect();
            
            PreparedStatement ps = myDb.prepareStatement(
                    "SELECT id,name,pass FROM users WHERE id = ?;"
            );
            ps.setInt(1, user.getId());
            ResultSet rs = myDb.executeQuery(ps);
            
            User userResponse = new User();
            
            rs.next();
            
            userResponse.setId(rs.getInt(1));
            userResponse.setName(rs.getString(2));
            userResponse.setPass(rs.getString(3));
            
            myDb.disconnect();
            
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

