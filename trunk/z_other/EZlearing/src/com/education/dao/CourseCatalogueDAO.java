package com.education.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.expframework.exceptions.BaseAppException;

import com.education.transferobj.CourseCatalogueTO;
import com.education.util.GetConnection;

public class CourseCatalogueDAO extends AbstractDAO {

	public List<CourseCatalogueTO> getDefaultValues() throws BaseAppException {
  	List<CourseCatalogueTO> courseCatalogueTOList = new ArrayList<CourseCatalogueTO>();
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "select b.classSubId, a.ClassCertId, a.CertificateName, "
				+ "c.subject_Id, c.subjectValue, b.subjectCost,d.boardtype_description "
				+ "from t_class_cert as a,t_class_subject_cost as b,t_subjects as c,t_board d "
				+ "where a.ClassCertId = b.classId and b.subjectId = c.subject_Id";
		try {

			con = GetConnection.getSimpleConnection();
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				CourseCatalogueTO to = new CourseCatalogueTO();
				to.setClassSubjectId(rs.getInt(1));
				to.setClassId(rs.getInt(2));
				to.setClassName(rs.getString(3));
				to.setSubjectId(rs.getInt(4));
				to.setSubjectName(rs.getString(5));
				to.setSubjectCost(rs.getDouble(6));
				to.setBoardName(rs.getString(7));
				courseCatalogueTOList.add(to);
			}

		} catch (SQLException sqld) {
			throw new RuntimeException(sqld);
		} finally {
			closeQuietly(con, stmt,rs);
		}
		return courseCatalogueTOList;
	}
	
	public static void main(String[] args) {
		try {
			List<CourseCatalogueTO> courseCatalogueTOList = new CourseCatalogueDAO().getDefaultValues();
			System.out.println(courseCatalogueTOList);
		} catch (BaseAppException e) {			
			e.printStackTrace();
		}
	}

	public void addCourseCatalogue(List<CourseCatalogueTO> selectedToList) throws BaseAppException {
		for(int i=0;i < selectedToList.size();i++){
			String query = "INSERT INTO `T_STUDENT_SELECTED_CLASSSUB` (`classSubId`,`userId`) values (" 
				+ selectedToList.get(i).getClassSubjectId() + "," + selectedToList.get(i).getUserId() + ")";
			insertQuery(query);
		}
	}
}