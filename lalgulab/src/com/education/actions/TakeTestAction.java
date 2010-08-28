package com.education.actions;

import java.io.IOException;
import java.util.Iterator;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.expframework.data.ExceptionDisplayDTO;
import org.expframework.exceptions.BaseAppException;

import com.education.Session.SessionConstants;
import com.education.Session.UserSessionInfo;
import com.education.exceptions.BussTestCannotGeneratedException;
import com.education.formbeans.TestQuestionBeanActionForm;
import com.education.services.TestGenerationService;
import com.education.transferobj.QuestionBankTO;
import com.education.transferobj.TestQuestion;
import com.education.util.EducationConstant;
import com.education.util.Utilities;

public class TakeTestAction  extends EducationBaseAction {

	@Override
	public ActionForward displayAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws BaseAppException,ServletException,IOException {
		//Set the Exception Handling details ActionForward, Context Senstive Exception detail
		ExceptionDisplayDTO expDTO = new ExceptionDisplayDTO("testMainPage","");
		expDisplayDetails.set(expDTO);
		String actionFwdName = "testMainPage";
		String classID = request.getAttribute("classID")==null ? "-1" : (String)request.getAttribute("classID");
		String subjectID = request.getAttribute("subjectID")==null ? "-1" : (String)request.getAttribute("subjectID");
	
		TestGenerationService service = new TestGenerationService();
		int sampleTestQuestionCount = Integer.parseInt(Utilities.getApplicationProperty("sampleTestnoOfQuestion"));
		if(!service.isSampleTestValid_forClassId(Integer.parseInt(classID),Integer.parseInt(subjectID),sampleTestQuestionCount)){
			updateQuestionSummary(request,0,0,0);
			throw new BussTestCannotGeneratedException("SampleTest Cannot Generated due to Less Question in QuestionBank");
		}
		TreeMap testQuestionList = service.getSampleTestQuestions_byClassId(Integer.parseInt(classID),Integer.parseInt(subjectID),sampleTestQuestionCount);	
		request.getSession(false).setAttribute(SessionConstants.TEST_SAMPLETEST_QUESTIONS,testQuestionList);
		updateQuestionSummary(request,0,sampleTestQuestionCount,0);
		return mapping.findForward(actionFwdName);
	}

	@Override
	public ActionForward performAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws BaseAppException {
		//Set the Exception Handling details ActionForward, Context Senstive Exception detail
		ExceptionDisplayDTO expDTO = new ExceptionDisplayDTO("testQuestionPage","");
		expDisplayDetails.set(expDTO);
		
		String actionFwdName = "";
		TestQuestionBeanActionForm bean = (TestQuestionBeanActionForm)form; 
		String actionPerformed = getAction(request);
		TreeMap questionMap = (TreeMap)request.getSession(false).
				getAttribute(SessionConstants.TEST_SAMPLETEST_QUESTIONS);
		if(questionMap.size() > 0) {
			String totalNoQuesDisplayed  = String.valueOf(questionMap.size());
			request.setAttribute("totalNoQuesDisplayed",totalNoQuesDisplayed);
		}
		String noOfQuestion = Utilities.getApplicationProperty("sampleTestnoOfQuestion");
		
		if(actionPerformed!=null && actionPerformed.equalsIgnoreCase("displayquestion")){
			actionFwdName = "testQuestionPage";
			//Get Question Details
			TestQuestion questionDetails = (TestQuestion)questionMap.get(Integer.valueOf(bean.getQuestionNumber()));
			setQuestionDetails(request, bean,bean.getQuestionID(),bean.getQuestionNumber(),noOfQuestion,questionDetails.getAnswer());
			
		}else if(actionPerformed!=null && actionPerformed.equalsIgnoreCase("previous")){
			updateQuestionDetails(request, questionMap, bean, actionPerformed);
			actionFwdName = "testQuestionPage";
			//Get previous Question Details
			TestQuestion previousQuestion = (TestQuestion)questionMap.get(
					new Integer(Integer.valueOf(bean.getQuestionNumber())-1));			
			setQuestionDetails(request,bean, ""+previousQuestion.getQuestionID(),
							""+previousQuestion.getTestpaperQuestionNumber(),
							noOfQuestion,previousQuestion.getAnswer());
			resetOptions(bean);			
			//set questionID in requestionScope
//			request.setAttribute("qId",bean.getQuestionID());
			
		}else if(actionPerformed!=null && actionPerformed.equalsIgnoreCase("next")){
			updateQuestionDetails(request,questionMap, bean, actionPerformed);
			actionFwdName = "testQuestionPage";
			//Get Next Question Details
			TestQuestion nextQuestion = (TestQuestion)questionMap.get(
					new Integer(Integer.valueOf(bean.getQuestionNumber())+1));			
			setQuestionDetails(request,bean, ""+nextQuestion.getQuestionID(),
							""+nextQuestion.getTestpaperQuestionNumber(),noOfQuestion,nextQuestion.getAnswer());
			resetOptions(bean);
			//set questionID in requestionScope
//			request.setAttribute("qId",bean.getQuestionID());
			
		}else if(actionPerformed!=null && actionPerformed.equalsIgnoreCase("revisit")){
			updateQuestionDetails(request,questionMap, bean, actionPerformed);
			actionFwdName = "testQuestionPage";			
			//Displays same Question Details
			TestQuestion testQuestion = (TestQuestion)questionMap.get(
					new Integer(Integer.valueOf(bean.getQuestionNumber())));			
			setQuestionDetails(request,bean, ""+testQuestion.getQuestionID(),
							""+testQuestion.getTestpaperQuestionNumber(),noOfQuestion,bean.getAnswer());
			
		}else if(actionPerformed!=null && actionPerformed.equalsIgnoreCase("teststatus")){
			//updateQuestionDetails(questionMap, bean, actionPerformed);
			actionFwdName = "testMainPage";
			calculate_Update_QuestionSummary(questionMap,request);
			
		}else if(actionPerformed!=null && actionPerformed.equalsIgnoreCase("finish")){
			updateQuestionDetails(request,questionMap, bean, actionPerformed);
			
			HttpSession httpSession = request.getSession();
			UserSessionInfo objUserinfo = (UserSessionInfo)httpSession.getAttribute(SessionConstants.user_info);
			if (objUserinfo == null) 
				actionFwdName = "home";
			else
			actionFwdName = "myPage";
		}
		
		return mapping.findForward(actionFwdName);
	}
	
	@Override
	protected boolean byPassSessionTracking_BeforeLogin() {
		return true;
	}
	
	private void setQuestionDetails(HttpServletRequest request, TestQuestionBeanActionForm bean,String questionID,
			String questionNumber,String noOfQuestions,String answer) throws BaseAppException{
		TestGenerationService service = new TestGenerationService();
		
		QuestionBankTO questionTO = service.getSampleTestQuestionDetails(questionID);
		
		 bean.setQuestionID(questionID);
		 bean.setOption1(questionTO.getOption1());
		 bean.setOption2(questionTO.getOption2());
		 bean.setOption3(questionTO.getOption3());
		 bean.setOption4(questionTO.getOption4());
		 bean.setQuestionDescription(questionTO.getQuestion());
		 bean.setQuestionNumber(questionNumber);
		 bean.setNoOfquestions(noOfQuestions);
		 //Set Image details
		 bean.setHasOption1Img(questionTO.getHasOption1Img());
		 bean.setHasOption2Img(questionTO.getHasOption2Img());
		 bean.setHasOption3Img(questionTO.getHasOption3Img());
		 bean.setHasOption4Img(questionTO.getHasOption4Img());
		 bean.setHasOption5Img(questionTO.getHasOption5Img());
		 if(!Utilities.isNullOrBlank(answer))
			 bean.setAnswer(answer);
		 else
			 resetOptions(bean);
	
		//set questionNumber in requestionScope
		 request.setAttribute("questionNumber",questionNumber);
		 //Set maxNoOfQuestions in requestionScope
		 request.setAttribute("maxNoOfQuestions",noOfQuestions);
		//set questionID in requestionScope
		request.setAttribute("qId",bean.getQuestionID());
	}

	private void updateQuestionDetails(HttpServletRequest request, TreeMap questionSet,TestQuestionBeanActionForm bean,String actionPerformed ){
		Integer key = new Integer(bean.getQuestionNumber());
		TestQuestion question = (TestQuestion)questionSet.get(key);
		//If Answer update the answer Question Status to Completed
		if( actionPerformed.equalsIgnoreCase("revisit") ){			
			question.setQuestionStatus(EducationConstant.TEST_QUESTION_STATUS_TOREVIST);
		}else if(!Utilities.isNullOrBlank(bean.getAnswer()) ){			
			question.setQuestionStatus(EducationConstant.TEST_QUESTION_STATUS_COMPLETED);
			question.setAnswer(bean.getAnswer());
		}		
		questionSet.put(key,question);
		request.getSession(false).setAttribute(SessionConstants.TEST_SAMPLETEST_QUESTIONS,questionSet);
	}
	
	private void resetOptions(TestQuestionBeanActionForm bean){
		bean.setAnswer("");
	}
	
	private void calculate_Update_QuestionSummary(TreeMap questionMap,HttpServletRequest request){
		Iterator keyItr = questionMap.keySet().iterator();
		int questionToAttempt = 0;
		int questionCompleted = 0;
		int questionToRevisit = 0;
	while(keyItr.hasNext()){
			Integer key = (Integer)keyItr.next();
			TestQuestion question = (TestQuestion)questionMap.get(key);
			if(question.getQuestionStatus().equals(EducationConstant.TEST_QUESTION_STATUS_COMPLETED))
				questionCompleted +=1;
			else if(question.getQuestionStatus().equals(EducationConstant.TEST_QUESTION_STATUS_TOATTEMPT))
				questionToAttempt +=1;
			else if(question.getQuestionStatus().equals(EducationConstant.TEST_QUESTION_STATUS_TOREVIST))
				questionToRevisit +=1;
		}
		updateQuestionSummary(request, questionCompleted, questionToAttempt, questionToRevisit);
	}
	
	private void updateQuestionSummary(HttpServletRequest request, int questionCompleted,int questionToAttempt,
					int questionToRevisit){
		request.setAttribute("questionCompleted",String.valueOf(questionCompleted));
		request.setAttribute("questionRevisit",String.valueOf(questionToRevisit));
		request.setAttribute("questionToAttempt",String.valueOf(questionToAttempt));
	}
	
	private void deletePrevious_SampleTestDetails(HttpServletRequest request){
		HttpSession session = request.getSession(false); 
		//check if previous sample test details exist in session
		if(session.getAttribute(SessionConstants.TEST_SAMPLETEST_QUESTIONS)!=null)
			session.removeAttribute(SessionConstants.TEST_SAMPLETEST_QUESTIONS);
	}
}











