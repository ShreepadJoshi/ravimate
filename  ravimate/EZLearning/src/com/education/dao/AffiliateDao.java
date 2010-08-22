package com.education.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;


import com.education.exceptions.TAffiliateDaoException;
import com.education.transferobj.AffiliateTO;
import com.education.transferobj.TAffiliatePk;
import com.education.util.GetConnection;

public class AffiliateDao extends AbstractDAO{
	
	/** 
	 * The factory class for this DAO has two versions of the create() method - one that
takes no arguments and one that takes a Connection argument. If the Connection version
is chosen then the connection will be stored in this attribute and will be used by all
calls to this DAO, otherwise a new Connection will be allocated for each operation.
	 */
	protected java.sql.Connection userConn;

	/** 
	 * All finder methods in this class use this SELECT constant to build their queries
	 */
	protected final String SQL_SELECT = "SELECT affiliate_id, affiliate_org_name, affiliatee_url, affiliate_type, aff_contact_person, affiliate_addr, aff_acct_dtls, aff_subs_type, aff_base_fee FROM " + getTableName() + "";

	/** 
	 * Finder methods will pass this value to the JDBC setMaxRows method
	 */
	protected int maxRows;

	/** 
	 * SQL INSERT statement for this table
	 */
	protected final String SQL_INSERT = "INSERT INTO " + getTableName() + " ( affiliate_id, affiliate_org_name, affiliatee_url, affiliate_type, aff_contact_person, affiliate_addr, aff_acct_dtls, aff_subs_type, aff_base_fee ) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ? )";

	/** 
	 * SQL UPDATE statement for this table
	 */
	protected final String SQL_UPDATE = "UPDATE " + getTableName() + " SET affiliate_id = ?, affiliate_org_name = ?, affiliatee_url = ?, affiliate_type = ?, aff_contact_person = ?, affiliate_addr = ?, aff_acct_dtls = ?, aff_subs_type = ?, aff_base_fee = ? WHERE affiliate_id = ?";

	/** 
	 * SQL DELETE statement for this table
	 */
	protected final String SQL_DELETE = "DELETE FROM " + getTableName() + " WHERE affiliate_id = ?";

	/** 
	 * Index of column affiliate_id
	 */
	protected static final int COLUMN_AFFILIATE_ID = 1;

	/** 
	 * Index of column affiliate_org_name
	 */
	protected static final int COLUMN_AFFILIATE_ORG_NAME = 2;

	/** 
	 * Index of column affiliatee_url
	 */
	protected static final int COLUMN_AFFILIATEE_URL = 3;

	/** 
	 * Index of column affiliate_type
	 */
	protected static final int COLUMN_AFFILIATE_TYPE = 4;

	/** 
	 * Index of column aff_contact_person
	 */
	protected static final int COLUMN_AFF_CONTACT_PERSON = 5;

	/** 
	 * Index of column affiliate_addr
	 */
	protected static final int COLUMN_AFFILIATE_ADDR = 6;

	/** 
	 * Index of column aff_acct_dtls
	 */
	protected static final int COLUMN_AFF_ACCT_DTLS = 7;

	/** 
	 * Index of column aff_subs_type
	 */
	protected static final int COLUMN_AFF_SUBS_TYPE = 8;

	/** 
	 * Index of column aff_base_fee
	 */
	protected static final int COLUMN_AFF_BASE_FEE = 9;

	/** 
	 * Number of columns
	 */
	protected static final int NUMBER_OF_COLUMNS = 9;

	/** 
	 * Index of primary-key column affiliate_id
	 */
	protected static final int PK_COLUMN_AFFILIATE_ID = 1;

	/** 
	 * Inserts a new row in the t_affiliate table.
	 */
	public TAffiliatePk insert(AffiliateTO dto) throws TAffiliateDaoException
	{
		long t1 = System.currentTimeMillis();
		// declare variables
		final boolean isConnSupplied = (userConn != null);
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			// get the user-specified connection or get a connection from the ResourceManager
			conn = isConnSupplied ? userConn : GetConnection.getSimpleConnection();
		
			stmt = conn.prepareStatement( SQL_INSERT );
			int index = 1;
			stmt.setString( index++, dto.getAffiliateId() );
			stmt.setString( index++, dto.getAffiliateOrgName() );
			stmt.setString( index++, dto.getAffiliateeUrl() );
			stmt.setString( index++, dto.getAffiliateType() );
			stmt.setString( index++, dto.getAffContactPerson() );
			stmt.setString( index++, dto.getAffiliateAddr() );
			stmt.setString( index++, dto.getAffAcctDtls() );
			stmt.setString( index++, dto.getAffSubsType() );
			if (dto.isAffBaseFeeNull()) {
				stmt.setNull( index++, java.sql.Types.DOUBLE );
			} else {
				stmt.setDouble( index++, dto.getAffBaseFee() );
			}
		
			System.out.println( "Executing " + SQL_INSERT + " with DTO: " + dto );
			int rows = stmt.executeUpdate();
			long t2 = System.currentTimeMillis();
			System.out.println( rows + " rows affected (" + (t2-t1) + " ms)" );
			reset(dto);
			return dto.createPk();
		}
		catch (Exception _e) {
			_e.printStackTrace();
			throw new TAffiliateDaoException( "Exception: " + _e.getMessage(), _e );
		}
		finally {
			closeQuietly(conn, stmt, rs);
		}
		
	}

	/** 
	 * Updates a single row in the t_affiliate table.
	 */
	public void update(TAffiliatePk pk, AffiliateTO dto) throws TAffiliateDaoException
	{
		long t1 = System.currentTimeMillis();
		// declare variables
		final boolean isConnSupplied = (userConn != null);
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			// get the user-specified connection or get a connection from the ResourceManager
			conn = isConnSupplied ? userConn : GetConnection.getSimpleConnection();
		
			System.out.println( "Executing " + SQL_UPDATE + " with DTO: " + dto );
			stmt = conn.prepareStatement( SQL_UPDATE );
			int index=1;
			stmt.setString( index++, dto.getAffiliateId() );
			stmt.setString( index++, dto.getAffiliateOrgName() );
			stmt.setString( index++, dto.getAffiliateeUrl() );
			stmt.setString( index++, dto.getAffiliateType() );
			stmt.setString( index++, dto.getAffContactPerson() );
			stmt.setString( index++, dto.getAffiliateAddr() );
			stmt.setString( index++, dto.getAffAcctDtls() );
			stmt.setString( index++, dto.getAffSubsType() );
			if (dto.isAffBaseFeeNull()) {
				stmt.setNull( index++, java.sql.Types.DOUBLE );
			} else {
				stmt.setDouble( index++, dto.getAffBaseFee() );
			}
		
			stmt.setString( 10, pk.getAffiliateId() );
			int rows = stmt.executeUpdate();
			reset(dto);
			long t2 = System.currentTimeMillis();
			System.out.println( rows + " rows affected (" + (t2-t1) + " ms)" );
		}
		catch (Exception _e) {
			_e.printStackTrace();
			throw new TAffiliateDaoException( "Exception: " + _e.getMessage(), _e );
		}
		finally {
			closeQuietly(conn, stmt, null);
		}
		
	}

	/** 
	 * Deletes a single row in the t_affiliate table.
	 */
	public void delete(TAffiliatePk pk) throws TAffiliateDaoException
	{
		long t1 = System.currentTimeMillis();
		// declare variables
		final boolean isConnSupplied = (userConn != null);
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			// get the user-specified connection or get a connection from the ResourceManager
			conn = isConnSupplied ? userConn : GetConnection.getSimpleConnection();
		
			System.out.println( "Executing " + SQL_DELETE + " with PK: " + pk );
			stmt = conn.prepareStatement( SQL_DELETE );
			stmt.setString( 1, pk.getAffiliateId() );
			int rows = stmt.executeUpdate();
			long t2 = System.currentTimeMillis();
			System.out.println( rows + " rows affected (" + (t2-t1) + " ms)" );
		}
		catch (Exception _e) {
			_e.printStackTrace();
			throw new TAffiliateDaoException( "Exception: " + _e.getMessage(), _e );
		}
		finally {
			closeQuietly(conn, stmt, null);
		}
		
	}

	/** 
	 * Returns the rows from the t_affiliate table that matches the specified primary-key value.
	 */
	public AffiliateTO findByPrimaryKey(TAffiliatePk pk) throws TAffiliateDaoException
	{
		return findByPrimaryKey( pk.getAffiliateId() );
	}

	/** 
	 * Returns all rows from the t_affiliate table that match the criteria 'affiliate_id = :affiliateId'.
	 */
	public AffiliateTO findByPrimaryKey(String affiliateId) throws TAffiliateDaoException
	{
		AffiliateTO ret[] = findByDynamicSelect( SQL_SELECT + " WHERE affiliate_id = ?", new Object[] { affiliateId } );
		return ret.length==0 ? null : ret[0];
	}

	/** 
	 * Returns all rows from the t_affiliate table that match the criteria ''.
	 */
	public AffiliateTO[] findAll() throws TAffiliateDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " ORDER BY affiliate_id", null );
	}

	/** 
	 * Returns all rows from the t_affiliate table that match the criteria 'affiliate_id = :affiliateId'.
	 */
	public AffiliateTO[] findWhereAffiliateIdEquals(String affiliateId) throws TAffiliateDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " WHERE affiliate_id = ? ORDER BY affiliate_id", new Object[] { affiliateId } );
	}

	/** 
	 * Returns all rows from the t_affiliate table that match the criteria 'affiliate_org_name = :affiliateOrgName'.
	 */
	public AffiliateTO[] findWhereAffiliateOrgNameEquals(String affiliateOrgName) throws TAffiliateDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " WHERE affiliate_org_name = ? ORDER BY affiliate_org_name", new Object[] { affiliateOrgName } );
	}

	/** 
	 * Returns all rows from the t_affiliate table that match the criteria 'affiliatee_url = :affiliateeUrl'.
	 */
	public AffiliateTO[] findWhereAffiliateeUrlEquals(String affiliateeUrl) throws TAffiliateDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " WHERE affiliatee_url = ? ORDER BY affiliatee_url", new Object[] { affiliateeUrl } );
	}

	/** 
	 * Returns all rows from the t_affiliate table that match the criteria 'affiliate_type = :affiliateType'.
	 */
	public AffiliateTO[] findWhereAffiliateTypeEquals(String affiliateType) throws TAffiliateDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " WHERE affiliate_type = ? ORDER BY affiliate_type", new Object[] { affiliateType } );
	}

	/** 
	 * Returns all rows from the t_affiliate table that match the criteria 'aff_contact_person = :affContactPerson'.
	 */
	public AffiliateTO[] findWhereAffContactPersonEquals(String affContactPerson) throws TAffiliateDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " WHERE aff_contact_person = ? ORDER BY aff_contact_person", new Object[] { affContactPerson } );
	}

	/** 
	 * Returns all rows from the t_affiliate table that match the criteria 'affiliate_addr = :affiliateAddr'.
	 */
	public AffiliateTO[] findWhereAffiliateAddrEquals(String affiliateAddr) throws TAffiliateDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " WHERE affiliate_addr = ? ORDER BY affiliate_addr", new Object[] { affiliateAddr } );
	}

	/** 
	 * Returns all rows from the t_affiliate table that match the criteria 'aff_acct_dtls = :affAcctDtls'.
	 */
	public AffiliateTO[] findWhereAffAcctDtlsEquals(String affAcctDtls) throws TAffiliateDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " WHERE aff_acct_dtls = ? ORDER BY aff_acct_dtls", new Object[] { affAcctDtls } );
	}

	/** 
	 * Returns all rows from the t_affiliate table that match the criteria 'aff_subs_type = :affSubsType'.
	 */
	public AffiliateTO[] findWhereAffSubsTypeEquals(String affSubsType) throws TAffiliateDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " WHERE aff_subs_type = ? ORDER BY aff_subs_type", new Object[] { affSubsType } );
	}

	/** 
	 * Returns all rows from the t_affiliate table that match the criteria 'aff_base_fee = :affBaseFee'.
	 */
	public AffiliateTO[] findWhereAffBaseFeeEquals(double affBaseFee) throws TAffiliateDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " WHERE aff_base_fee = ? ORDER BY aff_base_fee", new Object[] {  new Double(affBaseFee) } );
	}

	/**
	 * Method 'TAffiliateDaoImpl'
	 * 
	 */
	public AffiliateDao()
	{
	}

	/**
	 * Method 'TAffiliateDaoImpl'
	 * 
	 * @param userConn
	 */
	public AffiliateDao(final java.sql.Connection userConn)
	{
		this.userConn = userConn;
	}

	/** 
	 * Sets the value of maxRows
	 */
	public void setMaxRows(int maxRows)
	{
		this.maxRows = maxRows;
	}

	/** 
	 * Gets the value of maxRows
	 */
	public int getMaxRows()
	{
		return maxRows;
	}

	/**
	 * Method 'getTableName'
	 * 
	 * @return String
	 */
	public String getTableName()
	{
		return "db_education.t_affiliate";
	}

	/** 
	 * Fetches a single row from the result set
	 */
	protected AffiliateTO fetchSingleResult(ResultSet rs) throws SQLException
	{
		if (rs.next()) {
			AffiliateTO dto = new AffiliateTO();
			populateDto( dto, rs);
			return dto;
		} else {
			return null;
		}
		
	}

	/** 
	 * Fetches multiple rows from the result set
	 */
	protected AffiliateTO[] fetchMultiResults(ResultSet rs) throws SQLException
	{
		Collection resultList = new ArrayList();
		while (rs.next()) {
			AffiliateTO dto = new AffiliateTO();
			populateDto( dto, rs);
			resultList.add( dto );
		}
		
		AffiliateTO ret[] = new AffiliateTO[ resultList.size() ];
		resultList.toArray( ret );
		return ret;
	}

	/** 
	 * Populates a DTO with data from a ResultSet
	 */
	protected void populateDto(AffiliateTO dto, ResultSet rs) throws SQLException
	{
		dto.setAffiliateId( rs.getString( COLUMN_AFFILIATE_ID ) );
		dto.setAffiliateOrgName( rs.getString( COLUMN_AFFILIATE_ORG_NAME ) );
		dto.setAffiliateeUrl( rs.getString( COLUMN_AFFILIATEE_URL ) );
		dto.setAffiliateType( rs.getString( COLUMN_AFFILIATE_TYPE ) );
		dto.setAffContactPerson( rs.getString( COLUMN_AFF_CONTACT_PERSON ) );
		dto.setAffiliateAddr( rs.getString( COLUMN_AFFILIATE_ADDR ) );
		dto.setAffAcctDtls( rs.getString( COLUMN_AFF_ACCT_DTLS ) );
		dto.setAffSubsType( rs.getString( COLUMN_AFF_SUBS_TYPE ) );
		dto.setAffBaseFee( rs.getDouble( COLUMN_AFF_BASE_FEE ) );
		if (rs.wasNull()) {
			dto.setAffBaseFeeNull( true );
		}
		
	}

	/** 
	 * Resets the modified attributes in the DTO
	 */
	protected void reset(AffiliateTO dto)
	{
	}

	/** 
	 * Returns all rows from the t_affiliate table that match the specified arbitrary SQL statement
	 */
	public AffiliateTO[] findByDynamicSelect(String sql, Object[] sqlParams) throws TAffiliateDaoException
	{
		// declare variables
		final boolean isConnSupplied = (userConn != null);
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			// get the user-specified connection or get a connection from the ResourceManager
			conn = isConnSupplied ? userConn : GetConnection.getSimpleConnection();
		
			// construct the SQL statement
			final String SQL = sql;
		
		
			System.out.println( "Executing " + SQL );
			// prepare statement
			stmt = conn.prepareStatement( SQL );
			stmt.setMaxRows( maxRows );
		
			// bind parameters
			for (int i=0; sqlParams!=null && i<sqlParams.length; i++ ) {
				stmt.setObject( i+1, sqlParams[i] );
			}
		
		
			rs = stmt.executeQuery();
		
			// fetch the results
			return fetchMultiResults(rs);
		}
		catch (Exception _e) {
			_e.printStackTrace();
			throw new TAffiliateDaoException( "Exception: " + _e.getMessage(), _e );
		}
		finally {
			closeQuietly(conn, stmt, rs);
		}
	}

	/** 
	 * Returns all rows from the t_affiliate table that match the specified arbitrary SQL statement
	 */
	public AffiliateTO[] findByDynamicWhere(String sql, Object[] sqlParams) throws TAffiliateDaoException
	{
		// declare variables
		final boolean isConnSupplied = (userConn != null);
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			// get the user-specified connection or get a connection from the ResourceManager
			conn = isConnSupplied ? userConn : GetConnection.getSimpleConnection();
		
			// construct the SQL statement
			final String SQL = SQL_SELECT + " WHERE " + sql;
		
		
			System.out.println( "Executing " + SQL );
			// prepare statement
			stmt = conn.prepareStatement( SQL );
			stmt.setMaxRows( maxRows );
		
			// bind parameters
			for (int i=0; sqlParams!=null && i<sqlParams.length; i++ ) {
				stmt.setObject( i+1, sqlParams[i] );
			}
		
		
			rs = stmt.executeQuery();
		
			// fetch the results
			return fetchMultiResults(rs);
		}
		catch (Exception _e) {
			_e.printStackTrace();
			throw new TAffiliateDaoException( "Exception: " + _e.getMessage(), _e );
		}
		finally {
			closeQuietly(conn,stmt);			
		}
		
	}
}
