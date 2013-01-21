package com.education.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.expframework.exceptions.BaseAppException;

import com.education.exceptions.DBDataSourceException;
import com.education.exceptions.DBInvalidDataInsertionException;
import com.education.transferobj.AdvertisementTO;
import com.education.util.GetConnection;
import com.education.util.Utilities;

public class AdvertisementDAO extends AbstractDAO {

	
	
	public int InsertAdvertisement(AdvertisementTO advertisementTO){
		try {
			return insertQuery("INSERT INTO t_advertisement (`id`,`picture`,`text`,`keyword`,`type`,`category`,`Target`,`Status`,`startDate`,`endDate`) values (2,NULL,'Shree','shree','Shree','shree','class7','A','2001-02-10','2020-01-10')");
		} catch (BaseAppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	/**
	 * Get Advertisement
	 * @return AdvertisementTo
	 * @throws BaseAppException 
	 */
	public AdvertisementTO getAdvertisement() throws BaseAppException{
		int advid = 2;//todo
		AdvertisementTO advertisementTO =new AdvertisementTO();		
		String query = "select * from t_advertisement";
		
		Connection con;
        PreparedStatement psmt = null;
        ResultSet rs;
        
        try {
            con = GetConnection.getSimpleConnection();
            psmt = con.prepareStatement(query);
            rs = psmt.executeQuery();
            if (rs.next()) {               
            	advertisementTO.setId(rs.getInt("Id"));
            	advertisementTO.setCategory(rs.getString("category"));
            	advertisementTO.setStartDate(rs.getDate("startDate"));
            	advertisementTO.setEndDate(rs.getDate("endDate"));            	
            	advertisementTO.setKeyword(rs.getString("keyword"));
            	advertisementTO.setStatus(rs.getString("status"));
            	advertisementTO.setTarget(rs.getString("target"));
            	advertisementTO.setText(rs.getString("text"));
            	advertisementTO.setType(rs.getString("type"));            
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
		return advertisementTO;
	}
	
	public static void main(String[] args) {
		new AdvertisementDAO().InsertAdvertisement(null); 
	}
	

}
