package com.education.util;

import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.struts.action.ActionForm;
import org.expframework.AppConfig;
import org.expframework.exceptions.BaseAppException;

import com.education.Session.UserSessionInfo;
import com.education.dao.OracleQuestionbankDAO;
import com.education.exceptions.BussMasterDataMissingException;
import com.education.services.EZBusinessServices;
import com.education.services.QuestionBankService;
import com.education.transferobj.ClassCertTO;
import com.education.transferobj.DropDownOption;
import com.education.transferobj.LinkQTOClassTO;
import com.education.transferobj.TopicSubTopicTO;
import com.education.transferobj.UserTO;

public class Utilities implements Serializable {

	public static DateFormat IN_TIMESTAMP_FORMAT = new SimpleDateFormat(
			"d/M/yy H:mm:ss.SSS");
	public static DateFormat IN_DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
	public static DateFormat IN_TIME_FORMAT = new SimpleDateFormat("H:mm:ss");
	public static DateFormat IN_DATETIME_FORMAT = new SimpleDateFormat(
			"dd/MM/yyyy H:mm:ss");

	public class Option extends ActionForm implements Serializable {

		private String value = "";
		private String label = "";

		public Option() {
			// TODO Auto-generated constructor stub
		}

		public Option(String label, String value) {
			this.label = label;
			this.value = value;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}

		public String getLabel() {
			return label;
		}

		public void setLabel(String label) {
			this.label = label;
		}
	}

	public ArrayList<Option> getDropdownValue(int nDropdown_name)
			throws BaseAppException {

		ArrayList objOptions = new ArrayList<Option>();
		ArrayList list = new ArrayList();
		Iterator itr = null;
		EZBusinessServices eZservice = new EZBusinessServices();
		String dropDownName = "";
		switch (nDropdown_name) {
		case EducationConstant.CLASS_DROPDOWN_VALUE:
			objOptions.add(new Option("Please Specify", ""));
			itr = eZservice.getClassORCertList().iterator();
			while (itr.hasNext()) {
				ClassCertTO classObj = (ClassCertTO) itr.next();
				objOptions.add(new Option(classObj.getClassCertName(), String
						.valueOf(classObj.getClassCertId())));
			}
			dropDownName = "Class";
			break;
		case EducationConstant.SUBJECT_DROPDOWN_VALUE:
			objOptions.add(new Option("Please Specify", ""));
			itr = eZservice.getSubjects().iterator();
			while (itr.hasNext()) {
				TopicSubTopicTO subjectObj = (TopicSubTopicTO) itr.next();
				objOptions.add(new Option(subjectObj.getSubjectValue(), String
						.valueOf(subjectObj.getSubjectId())));
			}
			dropDownName = "Subject";
			break;
		case EducationConstant.QUESTION_STATUS_DROPDOWN_VALUE:
			objOptions.add(new Option("Please Specify", ""));
			objOptions.add(new Option("Approved", String
					.valueOf(EducationConstant.QUESTION_STATUS_APPROVED)));
			objOptions.add(new Option("Inactive", String
					.valueOf(EducationConstant.QUESTION_STATUS_INACTIVE)));
			objOptions.add(new Option("Pending", String
					.valueOf(EducationConstant.QUESTION_STATUS_PENDING)));
			objOptions.add(new Option("Rejected", String
					.valueOf(EducationConstant.QUESTION_STATUS_REJECTED)));
			dropDownName = "Question Status";
			break;
		case EducationConstant.QUESTION_COMPLEXITY_DROPDOWN_VALUE:
			objOptions.add(new Option("Please Specify", ""));
			itr = eZservice.getQuestionComplexity().iterator();
			while (itr.hasNext()) {
				LinkQTOClassTO classComplexityObj = (LinkQTOClassTO) itr.next();
				objOptions.add(new Option(classComplexityObj
						.getComplexityValue(), classComplexityObj
						.getComplexityId()));
			}
			dropDownName = "Question Complexity";
			break;
		case EducationConstant.TOPICS_DROPDOWN_VALUE:
			objOptions.add(new Option("Please Specify", ""));
			itr = eZservice.getTopicList().iterator();
			while (itr.hasNext()) {
				TopicSubTopicTO topicsObj = (TopicSubTopicTO) itr.next();
				objOptions.add(new Option(topicsObj.getTopicvalue(), String
						.valueOf(topicsObj.getTopicId())));
			}
			dropDownName = "Topic";
			break;
		case EducationConstant.SUBTOPICS_DROPDOWN_VALUE:
			objOptions.add(new Option("Please Specify", ""));
			itr = eZservice.getSubTopicList().iterator();
			while (itr.hasNext()) {
				TopicSubTopicTO subTopicsObj = (TopicSubTopicTO) itr.next();
				objOptions.add(new Option(subTopicsObj.getSubTopicValue(),
						String.valueOf(subTopicsObj.getSubjectTopicId())));
			}
			dropDownName = "SubTopic";
			break;
		case EducationConstant.USER_REGISTRATION_STATUS_DROPDOWN_VALUE:
			// objOptions.add(new Option("Please Specify", ""));
			list = eZservice.getRegistration_Status_List();
			list.add(0, new DropDownOption("Please Specify", ""));
			objOptions = list;
			dropDownName = "Registration Status";
			break;
		case EducationConstant.CONTENT_TYPE_DROPDOWN_VALUE:
			// objOptions.add(new Option("Please Specify", ""));
			list = eZservice.getContent_TypeList();
			list.add(0, new DropDownOption("Please Specify", ""));
			objOptions = list;
			dropDownName = "ContentType";
			break;
		}
		// Condt to check MasterData Missing
		if (objOptions.size() == 0)
			throw new BussMasterDataMissingException("Master Data Missing for-"
					+ dropDownName);
		return objOptions;
	}

	/**
	 * For String - checks for null or its content is "" For Object - any other
	 * objects checks for null value
	 * 
	 * @param str
	 *            - String Object , User any object type
	 * @return boolean true For String if its null or value is "" For other
	 *         Objects if its null false For String if its not null or value is
	 *         not empty For other Objects if its not null
	 * 
	 */
	public static boolean isNullOrBlank(Object str) {
		return str == null ? true : (str.getClass().getName().equals(
				"java.lang.String") ? (String.valueOf(str).equals("") ? true
				: false) : false);
	}

	public static Object getDAOInstance(String p_DAOClassName) {

		return null;
	}

	public static Object copyToTransferObject(Object p_objSrc, Object p_objDest) {

		try {
			PropertyUtils.copyProperties(p_objDest, p_objSrc);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return p_objDest;
	}

	public static String getRoleNameByID(String strRoleId) {

		String roleName = "";
		if (EducationConstant.AFFILIATE_USER_ROLE.equals(strRoleId)) {
			roleName = "Affiliate";
		} else if (EducationConstant.AGENT_USER_ROLE.equals(strRoleId)) {
			roleName = "Agent";
		} else if (EducationConstant.TEACHER_USER_ROLE.equals(strRoleId)) {
			roleName = "Teacher";
		} else if (EducationConstant.REVIEWER_USER_ROLE.equals(strRoleId)) {
			roleName = "Reviewer";
		} else if (EducationConstant.ADMIN_USER_ROLE.equals(strRoleId)) {
			roleName = "Admin";
		} else if (EducationConstant.GUEST_USER_ROLE.equals(strRoleId)) {
			roleName = "Guest";
		}
		return roleName;
	}

	public static String getQuestionStatusByID(int nStatusId) {
		String strStatus = "";

		switch (nStatusId) {
		case EducationConstant.QUESTION_STATUS_PENDING:
			strStatus = "Pending";
			break;
		case EducationConstant.QUESTION_STATUS_APPROVED:
			strStatus = "Approved";
			break;
		case EducationConstant.QUESTION_STATUS_INACTIVE:
			strStatus = "Inactive";
			break;
		case EducationConstant.QUESTION_STATUS_REJECTED:
			strStatus = "Rejected";
			break;
		default:
			strStatus = "";
			break;
		}
		return strStatus;
	}

	public static void copyProperties(Object dest, Object orig) {
		try {
			BeanUtils.copyProperties(dest, orig);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	public static long getCurrentDB_Date() {
		Long long_date = null;
		String curr_date = new SimpleDateFormat(
				EducationConstant.DB_DATE_FORMAT).format(new java.util.Date());

		try {
			long_date = new SimpleDateFormat(EducationConstant.DB_DATE_FORMAT)
					.parse(curr_date).getTime();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return long_date;
	}

	public static String getDate_DBFormat(Object date, String inputDateFormat) {
		java.util.Date disp_format_Date = null;
		String db_formatted_date = null;
		try {

			// check if instance of String
			if (date instanceof java.lang.String) {
				disp_format_Date = new SimpleDateFormat(inputDateFormat)
						.parse((String) date);
				db_formatted_date = new SimpleDateFormat(
						EducationConstant.DB_DATE_FORMAT)
						.format(disp_format_Date);

			} else if (date instanceof java.util.Date) {
				db_formatted_date = new SimpleDateFormat(
						EducationConstant.DB_DATE_FORMAT)
						.format((java.util.Date) date);

			}

		} catch (ParseException e) {
			e.printStackTrace();
		}
		// System.out.println("display_format_date: "+ display_format_date);
		return db_formatted_date;

	}

	public static String getDate_displayFormat(Object date) {
		java.util.Date db_Date = null;
		String display_format_date = null;
		try {

			// check if instance of String
			if (date instanceof java.lang.String) {
				db_Date = new SimpleDateFormat(EducationConstant.DB_DATE_FORMAT)
						.parse((String) date);
				display_format_date = new SimpleDateFormat(
						EducationConstant.DISPLAY_DATE_FORMAT).format(db_Date);

			} else if (date instanceof java.util.Date) {
				display_format_date = new SimpleDateFormat(
						EducationConstant.DISPLAY_DATE_FORMAT)
						.format((java.util.Date) date);

			}

		} catch (ParseException e) {
			e.printStackTrace();
		}
		// System.out.println("display_format_date: "+ display_format_date);
		return display_format_date;

	}

	/**
	 * Based on type to meathod returns the MIME Header
	 * 
	 * @param type
	 *            - type of content
	 * @return - String
	 */
	public static String getMIMEHeader(int type) {
		String mimeString = null;
		switch (type) {
		case EducationConstant.CONTENT_TYPE_JPEG:
			mimeString = "image/jpeg";
			break;
		case EducationConstant.CONTENT_TYPE_AVI:
			System.out.println("video selected");
			mimeString = "video/x-msvideo";
			break;
		case EducationConstant.CONTENT_TYPE_DOC:
			mimeString = "application/msword";
			break;
		case EducationConstant.CONTENT_TYPE_GIF:
			mimeString = "image/gif";
			break;
		case EducationConstant.CONTENT_TYPE_MINDMAP:
			mimeString = "mm";
			break;
		case EducationConstant.CONTENT_TYPE_MPEG:
			mimeString = "video/mpeg";
			break;
		case EducationConstant.CONTENT_TYPE_PDF:
			mimeString = "application/pdf";
			break;
		case EducationConstant.CONTENT_TYPE_PPT:
			mimeString = "application/vnd.ms-powerpoint";
			break;
		case EducationConstant.CONTENT_TYPE_TEXT:
			mimeString = "text/plain";
			break;
		case EducationConstant.CONTENT_TYPE_ZIP:
			mimeString = "application/zip";
			break;
		case EducationConstant.CONTENT_TYPE_XLS:
			mimeString = "application/vnd.ms-excel";
			break;
		case EducationConstant.CONTENT_TYPE_DOCX:
			mimeString = "application/msword";
			break;
		case EducationConstant.CONTENT_TYPE_PPTX:
			mimeString = "application/vnd.ms-powerpoint";
			break;
		case EducationConstant.CONTENT_TYPE_XLSX:
			mimeString = "application/vnd.ms-excel";
			break;
		case EducationConstant.CONTENT_TYPE_WMV:
			mimeString = "video/x-msvideo";
			break;
		case EducationConstant.CONTENT_TYPE_SWF:
			mimeString = "application/x-shockwave-flash";
			break;
		default:
			mimeString = null;
			break;
		}
		return mimeString;
	}

	/**
	 * Based on type to meathod returns the MIME Header
	 * 
	 * @param type
	 *            - type of content
	 * @return - String
	 */
	public static String getContentType_AsString(int type) {
		String mimeString = null;
		switch (type) {
		case EducationConstant.CONTENT_TYPE_JPEG:
			mimeString = "JPEG File";
			break;
		case EducationConstant.CONTENT_TYPE_AVI:
			mimeString = "AVI File";
			break;
		case EducationConstant.CONTENT_TYPE_DOC:
			mimeString = "DOC File";
			break;
		case EducationConstant.CONTENT_TYPE_GIF:
			mimeString = "GIF File";
			break;
		case EducationConstant.CONTENT_TYPE_MINDMAP:
			mimeString = "MindMap File";
			break;
		case EducationConstant.CONTENT_TYPE_MPEG:
			mimeString = "MPEG Viedo";
			break;
		case EducationConstant.CONTENT_TYPE_PDF:
			mimeString = "PDF File";
			break;
		case EducationConstant.CONTENT_TYPE_PPT:
			mimeString = "PPT File";
			break;
		case EducationConstant.CONTENT_TYPE_TEXT:
			mimeString = "Text File";
			break;
		case EducationConstant.CONTENT_TYPE_ZIP:
			mimeString = "Zip File";
			break;
		case EducationConstant.CONTENT_TYPE_XLS:
			mimeString = "XLS File";
			break;
		case EducationConstant.CONTENT_TYPE_DOCX:
			mimeString = "DOCX File";
			break;
		case EducationConstant.CONTENT_TYPE_PPTX:
			mimeString = "PPTX File";
			break;
		case EducationConstant.CONTENT_TYPE_XLSX:
			mimeString = "XLSX File";
			break;
		case EducationConstant.CONTENT_TYPE_SWF:
			mimeString = "Flash File";
			break;
		default:
			mimeString = null;
			break;
		}
		return mimeString;
	}

	/**
	 * Takes in the contentType integer and returns the extension
	 * 
	 * @param contentType
	 *            - contentType as Int
	 */
	public static String getContentType_Extension(int contentType) {

		String extension = null;

		switch (contentType) {
		case EducationConstant.CONTENT_TYPE_JPEG:
			extension = "jpg";
			break;
		case EducationConstant.CONTENT_TYPE_AVI:
			extension = "avi";
			break;
		case EducationConstant.CONTENT_TYPE_DOC:
			extension = "doc";
			break;
		case EducationConstant.CONTENT_TYPE_GIF:
			extension = "gif";
			break;
		case EducationConstant.CONTENT_TYPE_MINDMAP:
			extension = "mm";
			break;
		case EducationConstant.CONTENT_TYPE_MPEG:
			extension = "mpeg";
			break;
		case EducationConstant.CONTENT_TYPE_PDF:
			extension = "pdf";
			break;
		case EducationConstant.CONTENT_TYPE_PPT:
			extension = "ppt";
			break;
		case EducationConstant.CONTENT_TYPE_TEXT:
			extension = "txt";
			break;
		case EducationConstant.CONTENT_TYPE_ZIP:
			extension = "zip";
			break;
		case EducationConstant.CONTENT_TYPE_XLS:
			extension = "xls";
			break;
		case EducationConstant.CONTENT_TYPE_DOCX:
			extension = "docx";
			break;
		case EducationConstant.CONTENT_TYPE_PPTX:
			extension = "pptx";
			break;
		case EducationConstant.CONTENT_TYPE_XLSX:
			extension = "xlsx";
			break;
		case EducationConstant.CONTENT_TYPE_WMV:
			extension = "wmv";
			break;
		case EducationConstant.CONTENT_TYPE_SWF:
			extension ="swf";
			break;
		default:
			extension = null;
			break;
		}
		return extension;
	}

	/**
	 * populates details to SessionObj extracting the details from UserTO object
	 * 
	 * @param userTo
	 *            - UserTo Object holding the details of user
	 * @return - Populated UserSessionInfo Object with user details
	 */
	public static UserSessionInfo populateUserDetails_ToSession(UserTO userTo,String loginType) {
		//Smita - 10th Aug 10
		UserSessionInfo objUserInfo = new UserSessionInfo();
		objUserInfo.setLoginType(loginType);
		objUserInfo.setEmailId(userTo.getEmailId());
		objUserInfo.setFirstName(userTo.getFirstName());
		objUserInfo.setLastName(userTo.getLastName());
		objUserInfo.setDoneFullRegistration(userTo.isDoneFullRegistration());
		objUserInfo.setRegistrationDate(userTo.getRegistrationDate());
		objUserInfo.setRoleId(String.valueOf(userTo.getRoleId()));
		objUserInfo.setUserloginName(userTo.getUserloginID());
		objUserInfo.setUserId(userTo.getUserId());
		objUserInfo.setUserTo(userTo);
		return objUserInfo;
	}

	public static String encryptData(String valueForEncryption) {

		String md5val = "";
		MessageDigest algorithm = null;

		try {
			algorithm = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException nsae) {
			System.out.println("Cannot find digest algorithm");
			// System.exit(1);
		}

		byte[] defaultBytes = valueForEncryption.getBytes();
		algorithm.reset();
		algorithm.update(defaultBytes);
		byte messageDigest[] = algorithm.digest();
		StringBuffer hexString = new StringBuffer();

		for (int i = 0; i < messageDigest.length; i++) {
			String hex = Integer.toHexString(0xFF & messageDigest[i]);
			if (hex.length() == 1) {
				hexString.append('0');
			}
			hexString.append(hex);
		}
		md5val = hexString.toString();
		// System.out.println("Encrypted string for "+valueForEncryption+":"+md5val);
		return md5val;
	}

	public static String getDataMissingExceptionColumnName(String msg) {
		String columnName = null;
		// msg=" Incorrect integer value: 'abc' for column 'classId' at row 1";
		int startIndex = msg.indexOf("column") + 6;
		int endIndex = msg.indexOf("at");
		columnName = msg.substring(startIndex, endIndex);
		return columnName.replace(" ", "");
	}

	public static String getDataMissingExceptionColumValue(String msg) {
		String columnValue = null;
		// msg=" Incorrect integer value: 'abc' for column 'classId' at row 1";
		int startIndex = msg.indexOf(":") + 1;
		int endIndex = msg.indexOf("for");
		columnValue = msg.substring(startIndex, endIndex);
		return columnValue.replace(" ", "");
	}

	public static String getDataTooLongExceptionColumnName(String msg) {
		String columnName = null;
		// msg=" Data truncation: Data too long for column 'contentStream' at row 1";
		int startIndex = msg.indexOf("column") + 6;
		int endIndex = msg.lastIndexOf("at");
		columnName = msg.substring(startIndex, endIndex);
		return columnName.replace(" ", "");
	}

	public static String getApplicationProperty(String key) {
		return AppConfig.getInstance().get(key);
	}

	/**
	 * This method will be use to send mail. method will delegate call to
	 * MailUtility.
	 * 
	 * @param mailTo
	 * @param subject
	 * @param msg
	 * @return result of send mail action.
	 * @author Shree
	 * 
	 */
	public static boolean sendMail(String[] mailTo, String subject, String msg) {
		ResourceBundle rBundle = ResourceBundle.getBundle("mail");
		String mailFrom = rBundle.getString("mailFrom");
		String password = rBundle.getString("password");
		String host = rBundle.getString("host");
		String port = rBundle.getString("port");
		String starttls = rBundle.getString("starttls");
		String auth = rBundle.getString("auth");
		boolean debug = false;
		final String socketFactoryClass = "javax.net.ssl.SSLSocketFactory";
		String fallback = rBundle.getString("fallback");
		String[] cc = { rBundle.getString("cc") };
		String[] bcc = { rBundle.getString("bcc") };
		return MailUtility.sendMail(mailFrom, password, host, port, starttls,
				auth, debug, socketFactoryClass, fallback, mailTo, cc, bcc,
				subject, msg);
	}

	/**
	 * This method will return a new randomly generated password.
	 * 
	 * @return
	 */
	public static String getNewPassword() {
		return "patil";
	}

	/**
	 * Convert an Object to a Timestamp.
	 */
	public static java.sql.Timestamp toTimestamp(Object value)
			throws ParseException {
		if (value == null)
			return null;
		if (value instanceof java.sql.Timestamp)
			return (java.sql.Timestamp) value;
		if (value instanceof String) {
			if ("".equals((String) value))
				return null;
			return new java.sql.Timestamp(IN_TIMESTAMP_FORMAT.parse(
					(String) value).getTime());
		}

		return new java.sql.Timestamp(IN_TIMESTAMP_FORMAT.parse(
				value.toString()).getTime());
	}

	/**
	 * Convert an Object to a Time.
	 */
	public static java.sql.Time toTime(Object value) throws ParseException {
		if (value == null)
			return null;
		if (value instanceof java.sql.Time)
			return (java.sql.Time) value;
		if (value instanceof String) {
			if ("".equals((String) value))
				return null;
			return new java.sql.Time(IN_TIME_FORMAT.parse((String) value)
					.getTime());
		}

		return new java.sql.Time(IN_TIME_FORMAT.parse(value.toString())
				.getTime());
	}

	/**
	 * Convert an Object to a Date.
	 */
	public static java.sql.Date toDate(Object value) throws ParseException {
		if (value == null)
			return null;
		if (value instanceof java.sql.Date)
			return (java.sql.Date) value;
		if (value instanceof String) {
			if ("".equals((String) value))
				return null;
			return new java.sql.Date(IN_DATE_FORMAT.parse((String) value)
					.getTime());
		}

		return new java.sql.Date(IN_DATE_FORMAT.parse(value.toString())
				.getTime());
	}

	/**
	 * Convert an Object to a DateTime.
	 */
	public static java.util.Date toDateTime(Object value) throws ParseException {
		if (value == null)
			return null;
		if (value instanceof java.util.Date)
			return (java.util.Date) value;
		if (value instanceof String) {
			if ("".equals((String) value))
				return null;
			return IN_DATE_FORMAT.parse((String) value);
		}

		return IN_DATE_FORMAT.parse(value.toString());
	}

	/**
	 * This method is used to
	 * 
	 * @param fileName
	 * @throws IOException
	 */
	public void downloadFile(String fileName) throws IOException {
		/*
		 * File file = null; d file = new File(fileName);
		 */
	}

	public ArrayList<Option> getDropdownValue(int nDropdown_name, int userId) throws BaseAppException {
		ArrayList objOptions = new ArrayList<Option>();
		ArrayList list = new ArrayList();
		Iterator itr = null;
		EZBusinessServices eZservice = new EZBusinessServices();
		String dropDownName = "";
		switch (nDropdown_name) {
		case EducationConstant.CLASS_DROPDOWN_VALUE:
			objOptions.add(new Option("Please Specify", ""));
			itr = new QuestionBankService().getSelectedClassOfStudent(userId).iterator();
			while (itr.hasNext()) {
				ClassCertTO classObj = (ClassCertTO) itr.next();
				objOptions.add(new Option(classObj.getClassCertName(), String
						.valueOf(classObj.getClassCertId())));
			}
			dropDownName = "Class";
			break;
		case EducationConstant.SUBJECT_DROPDOWN_VALUE:
			objOptions.add(new Option("Please Specify", ""));
			itr = new QuestionBankService().getSelectedSubjectOfStudent(userId).iterator();
			while (itr.hasNext()) {
				TopicSubTopicTO subjectObj = (TopicSubTopicTO) itr.next();
				objOptions.add(new Option(subjectObj.getSubjectValue(), String
						.valueOf(subjectObj.getSubjectId())));
			}
			dropDownName = "Subject";
			break;
		}
		if (objOptions.size() == 0)
			throw new BussMasterDataMissingException("Master Data Missing for-"
						+ dropDownName);
		return objOptions;
	}
}
