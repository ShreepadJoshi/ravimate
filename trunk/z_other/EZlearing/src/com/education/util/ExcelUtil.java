package com.education.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;

import org.apache.commons.dbutils.DbUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ExcelUtil {
	
	private static final Logger _log = Logger.getLogger(ExcelUtil.class.getName());
	
	public boolean exportToExcel(String sql, ArrayList<?> params) throws SQLException
	{
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		ResultSetMetaData rsmd = null;
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("sheet1");
		int i = 0;
		
		try
		{
			if (params == null) throw new SQLException("Parameters Are Null, Parameters Cannot be null");
			if (sql == null) throw new SQLException("Query is Null, Query Cannot be null");
			
			
			if(_log.isDebugEnabled()){
				_log.debug("Query for Download==>"+sql);
				_log.debug("parameters to the query==>"+params.toArray());
			}
			
			con = GetConnection.getSimpleConnection();
			ps = con.prepareStatement(sql);
			for(Object o : params)
			{
				if(o == null)
					throw new SQLException("Parameters cannot be null");
				
				if (o instanceof Integer)
				{
					ps.setInt(i+1, ((Integer) o).intValue());
					continue;
				}
				else if (o instanceof Float)
				{
					ps.setFloat(i+1, ((Float) o).floatValue());
					continue;
				}
				else if(o instanceof Double)
				{
					ps.setDouble(i+1, ((Double) o).doubleValue());
					continue;
				}
				else if(o instanceof Timestamp)
				{
					ps.setTimestamp(i+1,Utilities.toTimestamp(o));
					continue;
				}
				else if(o instanceof Date)
				{
					ps.setDate(i+1, Utilities.toDate(o));
					continue;
				}
				else if(o instanceof java.util.Date)
				{
					ps.setDate(i+1, new Date(((java.util.Date) o).getTime()));
					continue;
				}
				else
				{
					ps.setString(i+1, o.toString());
				}
			}
			
			
			rs = ps.executeQuery();
			
			int j=0;
			while(rs.next())
			{
				rsmd = rs.getMetaData();
				HSSFRow row = sheet.createRow(j);
				for(i=1;i<=rsmd.getColumnCount();i++)
				{
					HSSFCell cell = row.createCell(i-1);
					int colType = rsmd.getColumnType(i);
					if(_log.isDebugEnabled()) _log.debug("Column Type==>"+colType);
					switch(colType)
					{
						case Types.DATE : cell.setCellValue(rs.getDate(i)); break;
						case Types.DOUBLE : cell.setCellValue(rs.getDouble(i)); break;
						case Types.FLOAT : cell.setCellValue(rs.getFloat(i)); break;
						//case Types.TIMESTAMP : cell.setCellValue(Utilities.toTimestamp(rs.getDate(i).toString())); break;
						default : cell.setCellValue(rs.getString(i)); break;
					}
				}
				j++;
			}
			
//			File f = new File(this.getClass().getResource("/WEB-INF/")+"workbook.xls");
			File f = new File("D:/workbook.xls");
			if (!f.exists())
				f.createNewFile();
			
			OutputStream out = new FileOutputStream(f);
			wb.write(out);
			if(_log.isDebugEnabled()) _log.debug("Xls file created successfully");
			return true;
		}
		catch(SQLException ex)
		{
			_log.error(ex);
			throw ex;
		}
		catch(Exception ie)
		{
			_log.error(ie);
		}
		finally
		{
			DbUtils.closeQuietly(con, ps, rs);
		}
		return false;
	}
	
	public HSSFWorkbook exportExcelToStream(String sql, ArrayList<?> params) throws SQLException
	{
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		ResultSetMetaData rsmd = null;
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("sheet1");
		int i = 0;
		
		try
		{
			if (params == null) throw new SQLException("Parameters Are Null, Parameters Cannot be null");
			if (sql == null) throw new SQLException("Query is Null, Query Cannot be null");
			
			
			if(_log.isDebugEnabled()){
				_log.debug("Query for Download==>"+sql);
				_log.debug("parameters to the query==>"+params.toArray());
			}
			
			con = GetConnection.getSimpleConnection();
			ps = con.prepareStatement(sql);
			for(Object o : params)
			{
				if(o == null)
					throw new SQLException("Parameters cannot be null");
				
				if (o instanceof Integer)
				{
					ps.setInt(i+1, ((Integer) o).intValue());
					continue;
				}
				else if (o instanceof Float)
				{
					ps.setFloat(i+1, ((Float) o).floatValue());
					continue;
				}
				else if(o instanceof Double)
				{
					ps.setDouble(i+1, ((Double) o).doubleValue());
					continue;
				}
				else if(o instanceof Timestamp)
				{
					ps.setTimestamp(i+1,Utilities.toTimestamp(o));
					continue;
				}
				else if(o instanceof Date)
				{
					ps.setDate(i+1, Utilities.toDate(o));
					continue;
				}
				else if(o instanceof java.util.Date)
				{
					ps.setDate(i+1, new Date(((java.util.Date) o).getTime()));
					continue;
				}
				else
				{
					ps.setString(i+1, o.toString());
				}
			}
			
			
			rs = ps.executeQuery();
			
			int j=0;
			while(rs.next())
			{
				rsmd = rs.getMetaData();
				HSSFRow row = sheet.createRow(j);
				for(i=1;i<=rsmd.getColumnCount();i++)
				{
					HSSFCell cell = row.createCell(i-1);
					int colType = rsmd.getColumnType(i);
					if(_log.isDebugEnabled()) _log.debug("Column Type==>"+colType);
					switch(colType)
					{
						case Types.DATE : cell.setCellValue(rs.getDate(i)); break;
						case Types.DOUBLE : cell.setCellValue(rs.getDouble(i)); break;
						case Types.FLOAT : cell.setCellValue(rs.getFloat(i)); break;
						//case Types.TIMESTAMP : cell.setCellValue(Utilities.toTimestamp(rs.getDate(i).toString())); break;
						default : cell.setCellValue(rs.getString(i)); break;
					}
				}
				j++;
			}
			if(_log.isDebugEnabled()) _log.debug("Xls file created successfully");
			return wb;
		}
		catch(SQLException ex)
		{
			_log.error(ex);
			throw ex;
		}
		catch(Exception ie)
		{
			_log.error(ie);
		}
		finally
		{
			DbUtils.closeQuietly(con, ps, rs);
		}
		return wb;
	}


}
