
package com.albares.twitter.api;

import com.albares.twitter.db.User;
import com.albares.twitter.utils.JWTUtils;
import com.albares.twitter.utils.Parameters;
import com.albares.twitter.utils.Response;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/addUser")
public class addUser {
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addUser(User user){   
        try{
            Parameters.users.put(Parameters.idUsers.incrementAndGet(),user);
            
            
            
            Response r = new Response();
            User u = new User();
            u.setToken(JWTUtils.generateToken(Parameters.idUsers.get()));
            r.setUser(u);
            r.setResponseCode(1);
            return r;   
        }catch(Exception e){
            Response r = new Response();
            r.setResponseCode(0);
            return r;
        }
    }    
    
    
}







