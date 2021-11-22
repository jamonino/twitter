
package com.albares.twitter.db;

import com.albares.twitter.utils.Db;
import com.albares.twitter.utils.JWTUtils;
import static com.albares.twitter.utils.SHAUtils.sha256;
import com.albares.twitter.utils.Secrets;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
    
    public int insertAndGetId_DB(Db myDb) throws SQLException, NoSuchAlgorithmException{
        PreparedStatement ps = myDb.prepareStatement(
                    "INSERT INTO users (name, pass) VALUES (?, ?) returning id;"
            );
            ps.setString(1, this.getName());
            ps.setString(2, this.getEncodedPass());
            ResultSet rs = myDb.executeQuery(ps);
            rs.next();
            this.setId(rs.getInt(1));
            return this.getId();
    }
    
    public void updateName_DB(Db myDb) throws SQLException{
    
        PreparedStatement ps = myDb.prepareStatement(
                    "UPDATE users SET name = ? WHERE id = ?;"
            );
        ps.setString(1, this.getName());
        ps.setInt(2, this.getId());
        myDb.executUpdate(ps);
    }
    
    public void selectNamePass_DB(Db myDb) throws SQLException{
        PreparedStatement ps = myDb.prepareStatement(
                    "SELECT name,pass FROM users WHERE id = ?;"
            );
        ps.setInt(1, this.getId());
        ResultSet rs = myDb.executeQuery(ps);

        rs.next();

        this.setName(rs.getString(1));
        this.setPass(rs.getString(2));
    }
    
    
    
    
}
