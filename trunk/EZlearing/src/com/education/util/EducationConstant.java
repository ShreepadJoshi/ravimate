package com.education.util;
 
public class EducationConstant {

	public static final String TEACHER_USER_ROLE = "2";
	public static final String AFFILIATE_USER_ROLE = "3";
	public static final String AGENT_USER_ROLE = "4";
	public static final String GUEST_USER_ROLE = "1";
	public static final String ADMIN_USER_ROLE = "5";
	public static final String REVIEWER_USER_ROLE = "6";
	
	public static final String SESSION_FALIURE = "session Falire"; 
	
	/** DROP DOWN VALUES IN APPLICATION **/
	public static final int CLASS_DROPDOWN_VALUE =1;
	public static final int SUBJECT_DROPDOWN_VALUE =2;
	public static final int QUESTION_STATUS_DROPDOWN_VALUE =3;
	public static final int USER_REGISTRATION_STATUS_DROPDOWN_VALUE =4;
	public static final int QUESTION_COMPLEXITY_DROPDOWN_VALUE =5;
	public static final int TOPICS_DROPDOWN_VALUE =6;
	public static final int SUBTOPICS_DROPDOWN_VALUE =7;
	public static final int CONTENT_TYPE_DROPDOWN_VALUE =8;
	
	/** Different modes for page **/
	public static final String PAGE_VIEW_MODE ="view";
	public static final String PAGE_EDIT_MODE ="edit";
	public static final String PAGE_CREATE_MODE ="create";
	
	/** Struts Action related **/
	public static final String TEACHER_PAGE_DELETE_OPERATION ="delete";
	
	/** Different Question Status **/
	public static final int QUESTION_STATUS_PENDING =1;
	public static final int QUESTION_STATUS_APPROVED =2;
	public static final int QUESTION_STATUS_INACTIVE =3;
	public static final int QUESTION_STATUS_REJECTED =4;
	
	/** Different Question Status **/
	public static final int QUESTION_COMPLEXITY_SIMPLE = 1;
	public static final int QUESTION_COMPLEXITY_MEDIUM = 2;
	public static final int QUESTION_COMPLEXITY_COMPLEX =3;
	public static final int QUESTION_COMPLEXITY_VERYCOMPLEX = 4; 
	
	/** Different User Registration Status **/
	public static final String REG_STATUS_APPROVED = "1";
	public static final String REG_STATUS_NOT_APPROVED = "2";
	public static final String REG_STATUS_DISABLED = "3";
	public static final int REG_STATUS_WAIT_FOR_ADMIN_APPROVAL = 2;
	
	/** Question Verification **/
	public static final int QUESTION_VERIFIED =1;
	public static final int QUESTION_NOT_VERIFIED =0;
	
	/** Image Rendering Conatants **/	
	public static final int IMAGE_FOR_OPTION1 =1;
	public static final int IMAGE_FOR_OPTION2=2;
	public static final int IMAGE_FOR_OPTION3 =3;
	public static final int IMAGE_FOR_OPTION4 =4;
	public static final int IMAGE_FOR_OPTION5 =5;
	public static final int IMAGE_FOR_ANSWER_EXPLANATION =6;
	public static final int IMAGE_FOR_QUESTION =7;
	
	/** date format **/
	public static final String DISPLAY_DATE_FORMAT = "dd-MMM-yyyy";
	public static final String DB_DATE_FORMAT = "yyyy-MM-dd";
	
	/** List of different content type suppported in application **/
	public static final int CONTENT_TYPE_JPEG = 1;
	public static final int CONTENT_TYPE_GIF = 2;
	public static final int CONTENT_TYPE_MPEG = 3;
	public static final int CONTENT_TYPE_MINDMAP = 4;
	public static final int CONTENT_TYPE_PDF = 5;
	public static final int CONTENT_TYPE_DOC = 6;
	public static final int CONTENT_TYPE_PPT = 7;
	public static final int CONTENT_TYPE_AVI = 8;
	public static final int CONTENT_TYPE_TEXT = 9;
	public static final int CONTENT_TYPE_ZIP = 10;
	public static final int CONTENT_TYPE_XLS = 11;
	public static final int CONTENT_TYPE_DOCX = 12;
	public static final int CONTENT_TYPE_PPTX = 13;
	public static final int CONTENT_TYPE_XLSX = 14;
	public static final int CONTENT_TYPE_WMV = 15;
	public static final int CONTENT_TYPE_SWF = 16;
	
	/** For Question Edit Page **/
	public static String EDIT_ACTION_UPDATE = "update";
	public static String EDIT_ACTION_REMOVE = "remove";
	public static String EDIT_ACTION_DONOTINHG = "none";
	
	/** Test Main Page **/
	public static String TEST_QUESTION_STATUS_COMPLETED = "Completed";
	public static String TEST_QUESTION_STATUS_TOATTEMPT = "toAttempt";
	public static String TEST_QUESTION_STATUS_TOREVIST = "toRevisit";
	public static String TEST_TYPE_SAMPLETEST = "sampleTest";
	
	public static Integer MAX_UNSUCCESSFULL_ATTEMPTS = 5;
	
	public static String AGENT_REG_FORM = "4";
	
}







