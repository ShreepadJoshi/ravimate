package com.education.dao;

import com.education.transferobj.UserTO;
import com.education.util.GetConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.expframework.exceptions.BaseAppException;

public class LoginDAO extends AbstractDAO{

    public UserTO loginValidation(UserTO userTo)throws BaseAppException {
        boolean flag = false;
        String email = userTo.getEmailId();
        String password = userTo.getPassword1();
        Connection con;
        PreparedStatement psmt = null;
        ResultSet rs;
        String sql = "SELECT UserId, EmailId , Password ,FirstName , LastName , " +
        			 " RoleId FROM t_user"+
        			 " WHERE EmailId='" + email +
        			 "' AND Password ='" + password + "'"+
        			 " AND IsApproved=1";
        try {
            con = GetConnection.getSimpleConnection();
            psmt = con.prepareStatement(sql);
            rs = psmt.executeQuery();
            if (rs.next()) {
                flag = true;
                userTo.setEmailId(rs.getString("EmailId"));
                //userTo.setUsername(rs.getString("FirstName") + " " + rs.getString("LastName"));
                userTo.setRoleId(rs.getInt("RoleId"));
                userTo.setUserId(rs.getInt("UserId"));
                //updateUnsuccessfulAttempt(email)
            }else{
            	userTo = null;
            }

        } catch (SQLException sqld) {
            throw new RuntimeException(sqld);
        } finally {
            try {
                if (null != psmt) {
                    psmt.close();
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        return userTo;

    }
    
    /**
     * Update Unsuccessfull Attemp by user.
     */
    private void updateUnsuccessfulAttempt(String email)throws BaseAppException{
    	Connection con;
        PreparedStatement psmt = null;
        ResultSet rs;
        
        
        String sql = "Update t_user set UnsuccessfulAttempt = UnsuccessfulAttempt + 1 where EmailId =" + email;
        
    	try {
            con = GetConnection.getSimpleConnection();
            psmt = con.prepareStatement(sql);
            rs = psmt.executeQuery();
    	} catch (SQLException sqld) {
            throw new RuntimeException(sqld);
        } finally {
            try {
                if (null != psmt) {
                    psmt.close();
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
   
    }
}
