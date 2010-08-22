<%
	//TestMainPageData data =
		//(TestMainPageData )session.getAttribute(SessionConstants.TEST_MAINPAGE_QUESTION_DETAIL);
	//out.print("noofQuestions:"+data.getNoOfQuestions());
	//out.print("questionToAttempt:"+data.getQuestionToAttemptCount());
	//int noOfLines =  ((float)data.getNoOfQuestions() /(float)noOfQuestion_perLine);
	String domainName = (String)application.getAttribute("DOMAIN_NAME");
	TreeMap testQuestions =	session.
			getAttribute(SessionConstants.TEST_SAMPLETEST_QUESTIONS) == null ?
						null : (TreeMap)session.getAttribute(SessionConstants.TEST_SAMPLETEST_QUESTIONS);
	
	String questionCompleted = request.getAttribute("questionCompleted")== null ? "0" : (String)request.getAttribute("questionCompleted");
	String questionToRevisit = request.getAttribute("questionRevisit") == null ? "0" : (String)request.getAttribute("questionRevisit");
	String questionToAttempt = request.getAttribute("questionToAttempt") == null ? "0" : (String)request.getAttribute("questionToAttempt");
	
	//String questionCompleted = "10";
	//String questionToRevisit = "10";
	//String questionToAttempt = "10";
	//TreeMap testQuestions = new TreeMap();
	//out.print("questionCompleted:"+ questionCompleted);
	//out.print("questionToRevisit:"+questionToRevisit);
	//out.print("questionToAttempt:"+questionToAttempt);
	
%>
<%!
	int noOfQuestion_perLine = 10;
	int newRow = noOfQuestion_perLine;
	boolean firstLine = true;	
	String getClass(String name){
		String className = "";
		if(name.equals(EducationConstant.TEST_QUESTION_STATUS_COMPLETED))
			className= "GreenBox";
		else if(name.equals(EducationConstant.TEST_QUESTION_STATUS_TOATTEMPT))
			className= "RedBox";
		else if(name.equals(EducationConstant.TEST_QUESTION_STATUS_TOREVIST))
			className= "YellowBox";
		return className;
	}
%>

<%@page import="com.education.Session.SessionConstants"%>
<%@page import="com.education.formbeans.TestMainPageData"%>
<%@taglib prefix="logic" uri="http://struts.apache.org/tags-logic" %>

<%@page import="com.education.util.EducationConstant"%>
<%@page import="java.util.TreeSet"%>
<%@page import="com.education.transferobj.TestQuestion"%>
<%@page import="java.util.HashSet"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.TreeMap"%>
<table width="100%" border="0" align="center" cellpadding="0"
	cellspacing="10" bgcolor="#F0F0FF">
	<form id="testMainPageFormId" action="takeTest.do" method="post">
		<input type="hidden" name="questionID" id="questionID" />
		<input type="hidden" name="action" id="action" value="displayQuestion" />
		<input type="hidden" name="questionNumber" id="questionPaperNum" />
	</form>
	<tr>
		<td colspan="4" class="blue_bold">Questions Status for Test</td>
	</tr>
	 <tr>
		<td width="25%" class="input_label">No. of Questions</td>

		<td><input style="color: black" name="No_Completed" 
			Value="<%= questionToAttempt %>" type="text" size="4"
			readonly="readonly" class="RedBox" /></td>
		<td><input style="color: black" name="No_Completed" 
			Value="<%=questionCompleted %>" type="text" size="4"
			readonly="readonly" class="GreenBox" /></td>
		<td><input style="color: black" name="No_ToRe-visit" 
			Value="<%=questionToRevisit %>" type="text" size="4"
			readonly="readonly" class="YellowBox" /></td>
	</tr> 

	<tr>

		<td colspan="5" align="center">
		<table id="test_table_style" cellspacing="10" cellpadding="5">
			<%
				Iterator keyItr = testQuestions.keySet().iterator();
			
			while(keyItr.hasNext()){				
				Integer key = (Integer)keyItr.next(); 
				TestQuestion question = (TestQuestion)testQuestions.get(key);
				if(newRow == noOfQuestion_perLine ){
					newRow = 1;
						System.out.println("-firstLine--------"+newRow);
					
						System.out.println("------noOfQuestion_perLine------"+noOfQuestion_perLine);
					%>							
						<tr>
							<td colspan="1" class="<%=getClass(question.getQuestionStatus()) %>">								
								<a 	onclick="displayTestQuestion('<%=question.getQuestionID() %>',
											'<%=question.getTestpaperQuestionNumber()%>')"
									class="url">
								 <%=question.getTestpaperQuestionNumber() %>
								</a>
							</td>
						
					
						
				
				<%}
				else{
					
				%>
					<td colspan="1" class="<%=getClass(question.getQuestionStatus()) %>">
						<a onclick="displayTestQuestion('<%=question.getQuestionID() %>',
							  	'<%=question.getTestpaperQuestionNumber()%>')"
							class="url">
							<%=question.getTestpaperQuestionNumber()%>
						</a>
					</td>
					<%newRow++;
					
				}
			}
			out.print("</tr>");	
			%>		
		 </table> 
		</td>
	
	<tr>
		<td width="25%" class="input_label">Legends:</td>
		<td><input  style="color:black" name="YetToAttempt" Value="Yet To Attempt" type="text"
			size="18" readonly="readonly" class="RedBox" /></td>
		<td><input  style="color:black" name="Completed" Value="Completed" type="text"
			size="18" readonly="readonly" class="GreenBox" /></td>
		<td><input  style="color:black" name="ToRe-visit" Value="To Re-visit" type="text"
			size="14" readonly="readonly" class="YellowBox" /></td>
	</tr>

</table>