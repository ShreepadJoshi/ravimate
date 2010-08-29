package com.education.actions;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUpload;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.expframework.data.ExceptionDisplayDTO;
import org.expframework.exceptions.BaseAppException;
import org.springframework.context.ApplicationContext;

import com.education.Session.SessionConstants;
import com.education.displaytag.IExtendedPaginatedList;
import com.education.displaytag.PaginatedListImpl;
import com.education.formbeans.ContentUploadActionForm;
import com.education.services.ContentUploadService;
import com.education.services.QuestionBankService;
import com.education.services.SchoolContentUploadService;
import com.education.transferobj.ContentUploadTO;
import com.education.transferobj.TSchool;
import com.education.util.EducationConstant;
import com.education.util.SpringContextAware;
import com.education.util.Utilities;

public class SchoolContentUploadAction extends EducationBaseAction{

	@Override
	public ActionForward displayAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws BaseAppException {
//		ContentUploadActionForm bean = (ContentUploadActionForm)form;
		//Set the Exception Handling details ActionForward, Context Senstive Exception detail
		ExceptionDisplayDTO expDTO = new ExceptionDisplayDTO("displayPage","");
		expDisplayDetails.set(expDTO);
		
		//condition to not display search results by default
		/*if(isNavigationDone(request)){
			getPaginatedSearchResults(request,bean,null);
		}*/
		
		return mapping.findForward("displayPage");
	}

	@Override
	public ActionForward performAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {		
//		ContentUploadActionForm contentUploadBean = (ContentUploadActionForm)form;
		QuestionBankService service = new QuestionBankService();
		String strActionFwd = "actionSuccess";
		//Set the Exception Handling details ActionForward, Context Senstive Exception detail
		ExceptionDisplayDTO expDTO = new ExceptionDisplayDTO("actionSuccess","");
		expDisplayDetails.set(expDTO);
		
		
		if(FileUpload.isMultipartContent(request))
		{
			
			ServletFileUpload upload = new ServletFileUpload();
			String value= upload.CONTENT_TYPE;
			FileItemIterator itr = upload.getItemIterator(request);
			//List item = upload.parseRequest(request);
			
			while(itr.hasNext())
			{
				FileItemStream item = itr.next();
				InputStream stream = item.openStream();
				if(!item.isFormField())
				{
					String fileName = item.getName();
					int slash = fileName.lastIndexOf("/");
					if (slash < 0)
						slash = fileName.lastIndexOf("\\");
					if (slash > 0)
						fileName = fileName.substring(slash + 1);
					
					if(!fileName.endsWith("xls") || !fileName.endsWith("XLS"))
					{
							dispInvalid_contentUpload_ErrMsg(request);
							return mapping.findForward("displayPage");
					}
					
					if(stream != null)
					{
						
					//	XSSFWorkbook wb = new XSSFWorkbook(stream);
						
						HSSFWorkbook wb = new HSSFWorkbook(stream);
						ArrayList<TSchool> schoolList = validateAndUploadExcel(wb);
						ApplicationContext ctx = SpringContextAware.getApplicationContext();
						SchoolContentUploadService schoolService = (SchoolContentUploadService)ctx.getBean("schoolContentSerivce");
						int rowsInserted = schoolService.insertIntoSchool(schoolList);
						dispRecordAdditionMessage(request, rowsInserted);
					}
				}
			}
		}
		request.setAttribute("action","display");
		return mapping.findForward("displayPage");
		//Check if User Performed Action
/*		String strPerformedAction = getAction(request); 
		if( strPerformedAction != null ){			
			IExtendedPaginatedList cachedPage =  getPaginatedListFromSession(request,
					SessionConstants.SCH_RESULTS_CONTENTUPLOAD_LIST);			
			String[] strIds = getCheckedIds(request);
			
			if(cachedPage != null){
				//Reset Search Criteria stored in session first then get the paginated search results
				ContentUploadSearchCriteria searchCriteria = (ContentUploadSearchCriteria)getCahedSearchCriteria(request, SessionConstants.SCH_RESULTS_CONTENTUPLOAD_LIST);
				Utilities.copyProperties(contentUploadBean, searchCriteria);
			}
			
			if( strPerformedAction.equalsIgnoreCase("updatecontent")){
				if(strIds == null || strIds.length == 0){
					dispRecordSelectionError(request,cachedPage,contentUploadBean,"Update");
					request.setAttribute("action","display");
					contentUploadBean.setPgSearchResults(cachedPage);
					return mapping.findForward(strActionFwd);	
				}
				dispUpdateContentPanel(request, contentUploadBean, cachedPage, strIds[0]);
				strActionFwd = "updateContent";
				
			}else if(strPerformedAction.equalsIgnoreCase("cancel")){ //end update content action
				contentUploadBean.setPgSearchResults(cachedPage); //reset search results
				contentUploadBean.setActionPerformed("");
				
			}else if(strPerformedAction.equalsIgnoreCase("addcontent")){			
				dispAddContentPanel(request, contentUploadBean, cachedPage);
				strActionFwd = "addContent";
				
			}else if(strPerformedAction.equalsIgnoreCase("update")){
				
				//Condt - To check that Mindmap is updated with Other MindMap
				//Or Other docs are not updated with Mindmap
				int previous_contentTypeID = contentUploadBean.getContentTypeID();
				int updated_ContentTypeID = contentUploadBean.getUptContentTypeID();
				 
				if(previous_contentTypeID == EducationConstant.CONTENT_TYPE_MINDMAP && 
						!(updated_ContentTypeID == EducationConstant.CONTENT_TYPE_MINDMAP)){
					String updated_ContentTypeName = Utilities.getContentType_AsString(updated_ContentTypeID);
					String previous_ContentTypeName = Utilities.getContentType_AsString(previous_contentTypeID);
					dispMessage(request,"",new ActionMessage("error.updating.ContentType",
							new String[]{previous_ContentTypeName,updated_ContentTypeName,
							previous_ContentTypeName}));
					request.setAttribute("action","display");
					setUpdatePanelDropDowns(contentUploadBean);
					contentUploadBean.setPgSearchResults(cachedPage);
					return mapping.findForward("updateContent");
				}
				//Condt to check upoloaded contentType matches selected ContentType
				String uploadContent_extension =  contentUploadBean.getContentFileStream().getFileName();
				uploadContent_extension = uploadContent_extension.substring(uploadContent_extension.indexOf('.')+1);				
				if(!isValid_UploadedContent(contentUploadBean.getUptContentTypeID(),uploadContent_extension)
						&& contentUploadBean.getContentFileStream().getFileSize() >0 ){
					dispInvalid_contentUpload_ErrMsg(request);
					setUpdatePanelDropDowns(contentUploadBean);
					contentUploadBean.setPgSearchResults(cachedPage);
					return mapping.findForward("updateContent");
				}
				
				int updateCount = updateContent(request, contentUploadBean, cachedPage);
				getPaginatedSearchResults(request,contentUploadBean,cachedPage); //get updated results
				dispRecordUpdationMessage(request,updateCount);
				
			}else if(strPerformedAction.equalsIgnoreCase("add")){				
				
				//Condt to check uploaded contentType matches selected ContentType
				String uploadContent_extension =  contentUploadBean.getContentFileStream().getFileName();
				uploadContent_extension = uploadContent_extension.substring(uploadContent_extension.lastIndexOf('.')+1);				
				if(!isValid_UploadedContent(EducationConstant.CONTENT_TYPE_XLS,uploadContent_extension) ){
					dispInvalid_contentUpload_ErrMsg(request);
					dispAddContentPanel(request, contentUploadBean, cachedPage);
					return mapping.findForward("addContent");
				}
				
				
				
				
				int recordAddedcount = addContent(request, contentUploadBean, cachedPage);			
				getPaginatedSearchResults(request,contentUploadBean,cachedPage); //get updated results
				dispRecordAdditionMessage(request, recordAddedcount);
				
			}else if(strPerformedAction.equalsIgnoreCase("close")){
				strActionFwd = "close";
				session_RemoveAttribute(request,SessionConstants.SCH_RESULTS_CONTENTUPLOAD_LIST);
				
			}else if(strPerformedAction.equalsIgnoreCase("delete")){
				if(strIds == null || strIds.length == 0){
					dispRecordSelectionError(request,cachedPage,contentUploadBean,"Delete");
					return mapping.findForward(strActionFwd);	
				}
				int recDeleteCount = deleteContent(request, contentUploadBean, cachedPage,strIds[0]);				
				int nNewRecCount = new ContentUploadService().getContentUploadCount(contentUploadBean.getSch_classType(), contentUploadBean.getSch_subject());
				getNewPage_AfterDelete(nNewRecCount, cachedPage);
				getPaginatedSearchResults(request,contentUploadBean,cachedPage); //get updated results
				dispRecordDeletionMessage(request, recDeleteCount);
			}
		}else{
			getPaginatedSearchResults(request, contentUploadBean, null);
		}*/
		
	/*	request.setAttribute("action","display");
		return mapping.findForward(strActionFwd);*/
	}
	
	/**
	 * Gets partial list of records for current page
	 * and sets the partial search results to FormBean
	 * Store current search results in session.
	 * @param request - request containing details of currently navigated page
	 * @param contentuploadBean - FormBean to which search results are set
	 */
	private void getPaginatedSearchResults(HttpServletRequest request,
			ContentUploadActionForm contentuploadBean,IExtendedPaginatedList thePage) throws BaseAppException{
		
		thePage = thePage == null ? getPaginatedListFromRequest(request) : thePage ;
		ContentUploadService service = new ContentUploadService();
		
		int totalNoOfRecords = service.getContentUploadCount(contentuploadBean.getSch_classType(), 
																contentuploadBean.getSch_subject());
		
		//int frmRecNo = thePage.getFirstRecordIndex()+1;
		//int toRecNo = frmRecNo + thePage.getPageSize()-1;
		
		int frmRecNo = thePage.getFirstRecordIndex();
		int noOfRecords  = thePage.getPageSize();
		
		thePage.setTotalNumberOfRows(totalNoOfRecords);
		ContentUploadTO objTo = new ContentUploadTO();
		copyDetailsFromBeanTO_TransferObj(contentuploadBean, objTo);
		ArrayList searchResults = service.getContentUploadDetails(objTo, frmRecNo,noOfRecords);
		
		thePage.setList(searchResults);
		contentuploadBean.setPgSearchResults(thePage);
		
		//Take backup of Search Criteria 
		ContentUploadSearchCriteria searchCriteria = new ContentUploadSearchCriteria();  
		Utilities.copyProperties(searchCriteria , contentuploadBean);
				
		//set Search Criteria to page results
		((PaginatedListImpl)thePage).setSearchCriteria(searchCriteria);
		
		//cache search results in session
		cacheSearchResult(request,SessionConstants.SCH_RESULTS_CONTENTUPLOAD_LIST, thePage);
	}

	private void copyDetailsFromBeanTO_TransferObj(ContentUploadActionForm bean, ContentUploadTO contentTo){
		contentTo.setClassId(bean.getSch_classType());
		contentTo.setSubjectId(bean.getSch_subject());		
	}

	private void dispUpdateContentPanel(HttpServletRequest request,
				ContentUploadActionForm contentUploadBean,
				IExtendedPaginatedList cachedPage,String selectedContentID) throws BaseAppException{
		ArrayList searchList =  getCahedSearchResults(request,SessionConstants.SCH_RESULTS_CONTENTUPLOAD_LIST);
		Iterator itr = searchList.iterator();
		String subjectId = null;
		//loop through list and get selected contentId to update
		while(itr.hasNext()){
			ContentUploadTO objTo =(ContentUploadTO)itr.next();
			if(objTo.getContentId().equals(selectedContentID)){
				contentUploadBean.setUptContentid(objTo.getContentId());
				subjectId = objTo.getSubjectId();
				contentUploadBean.setUptClassId(objTo.getClassId());
				contentUploadBean.setUptsubjectId(objTo.getSubjectId());
				contentUploadBean.setUptTopicId(objTo.getTopicId());
				contentUploadBean.setUptsubTopicId(objTo.getSubTopicId());
				contentUploadBean.setUptHasExistingSuppFile(objTo.getHasSupportingFiles());						
				contentUploadBean.setUptcontentFileName(objTo.getContentFileName());
				contentUploadBean.setUptsuppFileName(objTo.getSupportingtFileName());
				contentUploadBean.setUptContentTypeID(objTo.getContentTypeID());
				//backup the contentTypeID used to checking while updating content
				contentUploadBean.setContentTypeID(objTo.getContentTypeID()); 
				
				contentUploadBean.setUptContentTypeName(
						Utilities.getContentType_AsString(objTo.getContentTypeID()));
				break;
			}
		}// end while
		contentUploadBean.setPgSearchResults(cachedPage); //reset search results
		contentUploadBean.setActionPerformed("updatecontent");
		
		//sets All the dropdowns used in UpdatePanel
		setUpdatePanelDropDowns(contentUploadBean);
	}
	
	private void setUpdatePanelDropDowns(ContentUploadActionForm contentUploadBean) throws BaseAppException{
		//set Topic and subTopic drop down value
		Utilities util = new Utilities();
		contentUploadBean.setSubjectOptions(util.getDropdownValue(EducationConstant.SUBJECT_DROPDOWN_VALUE));
		contentUploadBean.setTopicOptions(util.getDropdownValue(EducationConstant.TOPICS_DROPDOWN_VALUE));
		contentUploadBean.setSubTopicOptions(util.getDropdownValue(EducationConstant.SUBTOPICS_DROPDOWN_VALUE));
		contentUploadBean.setContentTypeOptions(util.getDropdownValue(EducationConstant.CONTENT_TYPE_DROPDOWN_VALUE));
		//set class and subject drop down values
		contentUploadBean.setClassTypeOptions(util.getDropdownValue(EducationConstant.CLASS_DROPDOWN_VALUE));
		contentUploadBean.setSubjectOptions(util.getDropdownValue(EducationConstant.SUBJECT_DROPDOWN_VALUE));
		util = null;
	}
	
	private void dispAddContentPanel(HttpServletRequest request,
			ContentUploadActionForm contentUploadBean,
			IExtendedPaginatedList cachedPage) throws BaseAppException{
		contentUploadBean.setActionPerformed("addcontent");
		contentUploadBean.setPgSearchResults(cachedPage); //reset search results
		
		//Sets all the drop downs in Add Panel
		setAddPanelDropDowns(contentUploadBean);
	}
	
	private void setAddPanelDropDowns(ContentUploadActionForm contentUploadBean)throws BaseAppException{
		//set Topic and subTopic drop down value
		Utilities util = new Utilities();
		contentUploadBean.setTopicOptions(util.getDropdownValue(EducationConstant.TOPICS_DROPDOWN_VALUE));
		contentUploadBean.setSubTopicOptions(util.getDropdownValue(EducationConstant.SUBTOPICS_DROPDOWN_VALUE));
		//set class and subject drop down values
		contentUploadBean.setClassTypeOptions(util.getDropdownValue(EducationConstant.CLASS_DROPDOWN_VALUE));
		contentUploadBean.setSubjectOptions(util.getDropdownValue(EducationConstant.SUBJECT_DROPDOWN_VALUE));
		contentUploadBean.setContentTypeOptions(util.getDropdownValue(EducationConstant.CONTENT_TYPE_DROPDOWN_VALUE));
		util = null;
	}
	
	private int updateContent(HttpServletRequest request,ContentUploadActionForm contentUploadBean,
			IExtendedPaginatedList cachedPage) throws BaseAppException,Exception{
		
		ContentUploadService service = new ContentUploadService();
		//set details to ContentUploadTO
		ContentUploadTO objTo = new ContentUploadTO();
		objTo.setContentId(contentUploadBean.getUptContentid());
		objTo.setClassId(contentUploadBean.getUptClassId());
		objTo.setSubjectId(contentUploadBean.getUptsubjectId());
		objTo.setTopicId(contentUploadBean.getUptTopicId());
		objTo.setSubTopicId(contentUploadBean.getUptsubTopicId());
		objTo.setHasSupportingFiles(contentUploadBean.getUptHasExistingSuppFile());
		objTo.setContentTypeID(contentUploadBean.getUptContentTypeID());
		setFileStream_Details(contentUploadBean, objTo);
		int updateCount = service.updateContentUploadDetails(objTo);
		
		//set class and subject drop down values
		contentUploadBean.setClassTypeOptions(new Utilities().getDropdownValue(EducationConstant.CLASS_DROPDOWN_VALUE));
		contentUploadBean.setSubjectOptions(new Utilities().getDropdownValue(EducationConstant.SUBJECT_DROPDOWN_VALUE));
		return updateCount;
	}
	
	private int addContent(HttpServletRequest request,ContentUploadActionForm contentUploadBean,
			IExtendedPaginatedList cachedPage)throws Exception{
		ContentUploadService service = new ContentUploadService();
		//set details to ContentUploadTO
		ContentUploadTO objTo = new ContentUploadTO();
		objTo.setClassId(contentUploadBean.getUptClassId());
		objTo.setSubjectId(contentUploadBean.getUptsubjectId());
		objTo.setTopicId(contentUploadBean.getUptTopicId());
		objTo.setSubTopicId(contentUploadBean.getUptsubTopicId());
		objTo.setContentTypeID(contentUploadBean.getUptContentTypeID());
		
		setFileStream_Details(contentUploadBean, objTo);
		
		if(contentUploadBean.getSuppFileStream().getFileSize()>0){
			objTo.setHasSupportingFiles(1);
		}else{
			objTo.setHasSupportingFiles(0);
		}
		int recordAddedCount = service.insertContentUploadDetails(objTo);
		return recordAddedCount;
	}
	
	private int deleteContent(HttpServletRequest request,ContentUploadActionForm contentUploadBean,IExtendedPaginatedList cachedPage,String selectedId)throws Exception{
	
		ContentUploadService service = new ContentUploadService();
		int recDeleteCount = service.deleteContentUploadDetails( Integer.parseInt(selectedId) );
		return recDeleteCount;
	}
	
	private void setFileStream_Details(ContentUploadActionForm contentUploadBean,
			ContentUploadTO objTo)throws BaseAppException,Exception{
		
		if(contentUploadBean.getContentFileStream().getFileSize() > 0){
			objTo.setContentFileStream(contentUploadBean.getContentFileStream().getInputStream());
			objTo.setContentFileName(contentUploadBean.getContentFileStream().getFileName());
			objTo.setContentStreamLength( contentUploadBean.getContentFileStream().getFileSize());
		}else{
			objTo.setContentFileStream(null);
			objTo.setContentFileName(null);
			objTo.setContentStreamLength(0);
		}
		
		if(contentUploadBean.getSuppFileStream().getFileSize()>0){
			objTo.setSupportingFileStream(contentUploadBean.getSuppFileStream().getInputStream());
			objTo.setSupportingtFileName(contentUploadBean.getSuppFileStream().getFileName());
			objTo.setSupportingStreamLength(contentUploadBean.getSuppFileStream().getFileSize());										
		}else{
			objTo.setSupportingFileStream(null);
			objTo.setSupportingtFileName(null);
			objTo.setSupportingStreamLength(0);
		}
	}
	
	private void dispRecordSelectionError(HttpServletRequest request,
			IExtendedPaginatedList cachedPage,ContentUploadActionForm contentUploadBean,String operationType ){
		dispRecordSelection_ErrorMsg(request,operationType);
		contentUploadBean.setPgSearchResults(cachedPage);
		request.setAttribute("action","display");
	}
	
	@Override 
    /**
    * Return true for user as Admin Role
    */
    protected boolean hasValidPermission(HttpServletRequest request) {    	
    	String strRole = getUserRoleFromSession(request);    	
    	if(strRole==null || !strRole.equals(EducationConstant.ADMIN_USER_ROLE))
    		return false;
    	else
    		return true;
    }
	/**
	 * Compares 2 strings if does not match returns false else true 
	 * @param selectedContentType - value selected for ContentType ddropDowm
	 * @param uploadedContentType - 
	 * @return
	 */
	protected boolean isValid_UploadedContent(int selectedContentType,String uploadedContentType){
		String selectedFile_extension = Utilities.getContentType_Extension(selectedContentType);
		if(selectedFile_extension.equalsIgnoreCase(uploadedContentType))
			return true;
		else
			return false;		
	}
	
	protected void dispInvalid_contentUpload_ErrMsg(HttpServletRequest request){
		dispMessage(request,"",new ActionMessage("error.invalid.contentUpload"));
	}
	
	
	
	private ArrayList<TSchool> validateAndUploadExcel(HSSFWorkbook wb)
	{
		ArrayList<TSchool> schoolList = null;
		schoolList = new ArrayList<TSchool>();
		Map<Integer,String> schoolIndexNameMap = null; 
		if (wb != null)
		{
			HSSFSheet sheet = wb.getSheetAt(0);
			HSSFRow firstRow = sheet.getRow(0);
			
			if (firstRow != null && firstRow.getCell(0) != null && firstRow.getCell(0).getCellType() != HSSFCell.CELL_TYPE_BLANK)
			{
				for(Iterator itr = sheet.rowIterator();itr.hasNext();)
				{
					HSSFRow row = (HSSFRow)itr.next();
					int rowNum = row.getRowNum();
					if (rowNum > 0)
					{
						TSchool school = new TSchool();
						int i = row.getFirstCellNum();
						while(i<row.getLastCellNum())
						{
							if(schoolIndexNameMap != null && schoolIndexNameMap.size() > 0)
							{
								int j = i++;
								if(schoolIndexNameMap.containsKey(j) && schoolIndexNameMap.get(j).toString().startsWith("Name of Institution"))
								{
									school.setSchoolName(row.getCell(j).getStringCellValue());
									continue;
								}
								if(schoolIndexNameMap.containsKey(j) && schoolIndexNameMap.get(j).toString().startsWith("Board")){
									school.setSchoolBoard(row.getCell(j).getStringCellValue());
								continue;
								}
								if(schoolIndexNameMap.containsKey(j) && schoolIndexNameMap.get(j).toString().startsWith("State")){
									school.setSchoolState(row.getCell(j).getStringCellValue());
								continue;
								}
								if(schoolIndexNameMap.containsKey(j) && schoolIndexNameMap.get(j).toString().startsWith("District")){
									school.setSchoolDistrict(row.getCell(j).getStringCellValue());
								continue;
								}
								if(schoolIndexNameMap.containsKey(j) && schoolIndexNameMap.get(j).toString().startsWith("Postal Address")){
									school.setSchoolPostalAddr(row.getCell(j).getStringCellValue());
								continue;
								}
								if(schoolIndexNameMap.containsKey(j) && schoolIndexNameMap.get(j).toString().startsWith("Phone No. with STD Code")){
									if(row.getCell(j).getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
										school.setSchoolName(String.valueOf(row.getCell(j).getNumericCellValue()))	;
									else
										school.setSchoolPhoneNo(row.getCell(j).getStringCellValue());
								continue;
								}
								if(schoolIndexNameMap.containsKey(j) && schoolIndexNameMap.get(j).toString().startsWith("Pin Code")){
									school.setSchoolPinCode(String.valueOf(row.getCell(j).getNumericCellValue()));
								continue;
								}
								if(schoolIndexNameMap.containsKey(j) && schoolIndexNameMap.get(j).toString().startsWith("Office")){
									school.setSchoolOffice(row.getCell(j).getStringCellValue());
								continue;
								}	
								if(schoolIndexNameMap.containsKey(j) && schoolIndexNameMap.get(j).toString().startsWith("Email"))
								{
									school.setSchoolEmail(row.getCell(j).getStringCellValue());
								continue;
								}
								if(schoolIndexNameMap.containsKey(j) && schoolIndexNameMap.get(j).toString().startsWith("Website"))
								{
									school.setSchoolWebsite(row.getCell(j).getStringCellValue());
								continue;
							}
								if(schoolIndexNameMap.containsKey(j) && schoolIndexNameMap.get(j).toString().startsWith("Year of Foundation"))
								{
									if(row.getCell(j).getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
										school.setYearoffoundation(new Double(row.getCell(j).getNumericCellValue()).intValue());
									else
										school.setYearoffoundation(new Double(Double.parseDouble(row.getCell(j).getStringCellValue())).intValue());
								continue;
							}
								if(schoolIndexNameMap.containsKey(j) && schoolIndexNameMap.get(j).toString().startsWith("Name of Principal/ Head of Institution"))
								{
									school.setPrincipalName(row.getCell(j).getStringCellValue());
								continue;
							}
								if(schoolIndexNameMap.containsKey(j) && schoolIndexNameMap.get(j).toString().startsWith("Status of The School"))
								{
									school.setSchoolStatus(row.getCell(j).getStringCellValue());
								continue;
							}
								if(schoolIndexNameMap.containsKey(j) && schoolIndexNameMap.get(j).toString().startsWith("Name of Trust/ Society/ Managing Committee"))
								{
									school.setTrustName(row.getCell(j).getStringCellValue());
								continue;
							}
								if(schoolIndexNameMap.containsKey(j) && schoolIndexNameMap.get(j).toString().startsWith("Category of School"))
								{
									school.setSchoolCategory(row.getCell(j).getStringCellValue());
								continue;
							}
								if(schoolIndexNameMap.containsKey(j) && schoolIndexNameMap.get(j).toString().startsWith("Medium of Instruction"))
								{
									school.setMediumofInstruction(row.getCell(j).getStringCellValue());
								continue;
							}
								if(schoolIndexNameMap.containsKey(j) && schoolIndexNameMap.get(j).toString().startsWith("Types of School"))
								{
									school.setSchoolType(row.getCell(j).getStringCellValue());
								continue;
							}
							}
						}	
						schoolList.add(school);
					}
					else
					{
						int i = row.getFirstCellNum();
						schoolIndexNameMap = new HashMap<Integer, String>();
						while(i<row.getLastCellNum())
						{ 	int j = i++;
							schoolIndexNameMap.put(j, row.getCell(j).getStringCellValue());
						}
					}
				}
			}
			else
			{
				
			}
		}
		
		return schoolList;
	}
	
	class ContentUploadSearchCriteria {
		private String sch_subject = "";
		private String sch_classType = "";
		public String getSch_subject() {
			return sch_subject;
		}
		public void setSch_subject(String sch_subject) {
			this.sch_subject = sch_subject;
		}
		public String getSch_classType() {
			return sch_classType;
		}
		public void setSch_classType(String sch_classType) {
			this.sch_classType = sch_classType;
		}		
	}

}
