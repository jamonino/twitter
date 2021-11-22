
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

@Path("/getUser")
public class getUser {
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getUser(User user){  
        Response r = new Response();
        try{
            user.setId(JWTUtils.checkJWTandGetUserId(user.getToken()));
            
            User userResonse = new User();
            
            userResonse.setId(user.getId());
            
            Db myDb = new Db();
            myDb.connect();
            userResonse.selectNamePass_DB(myDb);
            myDb.disconnect();
            
            r.setUser(userResonse);
            r.setResponseCode(ResponseCodes.OK);
            return r;
        }catch(Exception e){
            r.setResponseCode(ResponseCodes.ERROR);
            return r;
        }
        
    }    
    
}

