
package com.albares.twitter.api;

import com.albares.twitter.db.User;
import com.albares.twitter.utils.Db;
import com.albares.twitter.utils.JWTUtils;
import com.albares.twitter.utils.Response;
import com.albares.twitter.utils.ResponseCodes;
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
            user.setToken(JWTUtils.generateToken(user.insertAndGetId_DB(myDb)));
            myDb.disconnect();

            User userResponse = new User();
            userResponse.setToken(user.getToken());
            
            Response r = new Response();
            r.setUser(userResponse);
            r.setResponseCode(ResponseCodes.OK);
            return r;   
        }catch(Exception e){
            Response r = new Response();
            r.setResponseCode(ResponseCodes.ERROR);
            return r;   
        }
    }    
}

