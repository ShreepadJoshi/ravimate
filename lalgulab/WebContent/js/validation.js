
function valManageTopic_SubTopicSchPg(formID){
	
	var subjectResult = false;
	var classResult = false;	
	var subject = document.getElementById('sch_subject').value;
	var classType = document.getElementById('sch_classType').value;
	
	if(subject == null || subject == ""){
		document.getElementById('sch_subject_ErrMsg').style.display="";
		subjectResult = false;
	}else{
		document.getElementById('sch_subject_ErrMsg').style.display="none";
		subjectResult=true;
	}
	
	if(classType == null || classType == ""){
		document.getElementById('sch_classType_ErrMsg').style.display="";
		classResult = false;
	}else{
		document.getElementById('sch_classType_ErrMsg').style.display="none";
		classResult=true;
	}
	
	if(subjectResult && classResult){
		document.getElementById(formID).submit();		
	}		
}

function valContentUploadPg(mode){
	
	var classId = document.getElementById('uptClassId').value;	
	var subjectId = document.getElementById('uptsubjectId').value;
	var topic = document.getElementById('uptTopicId').value;	
	var subTopic = document.getElementById('uptsubTopicId').value;
	var uptContentType = document.getElementById('uptContentType').value;
	
	var classIdResult = false;
	var subjectIdResult= false;
	var topicResult = false;
	var subTopicResult = false;
	var contentTypeResult = false;
	var contentFileStreamResult = false;
	// alert('In valContentUploadPg() ');
	
	if(classId == null || classId == ""){
		document.getElementById('uptClassId_ErrMsg').style.display="";
		classIdResult = false;		
	}else{
		document.getElementById('uptClassId_ErrMsg').style.display="none";
		classIdResult=true;
	}
	if(subjectId == null || subjectId == ""){
		document.getElementById('uptsubjectId_ErrMsg').style.display="";
		subjectIdResult = false;
	}else{
		document.getElementById('uptsubjectId_ErrMsg').style.display="none";
		subjectIdResult=true;
	}
	if(topic == null || topic == ""){
		document.getElementById('uptTopic_ErrMsg').style.display="";
		topicResult = false;
	}else{
		document.getElementById('uptTopic_ErrMsg').style.display="none";
		topicResult=true;
	}
	if(subTopic == null || subTopic == ""){
		document.getElementById('uptsubTopic_ErrMsg').style.display="";
		subTopicResult = false;
	}else{
		document.getElementById('uptsubTopic_ErrMsg').style.display="none";
		subTopicResult=true;
	}
	
	if(uptContentType == null || uptContentType == ""){
			document.getElementById('uptContentType_ErrMsg').style.display="";
			contentTypeResult = false;
	}else{
			document.getElementById('uptContentType_ErrMsg').style.display="none";
			contentTypeResult=true;
	}
	
	if(mode != null && mode == "add"){	
		var contentFileStream = document.getElementById('contentFileStream').value;	
		
		if(contentFileStream == null || contentFileStream == ""){
			document.getElementById('contentFileStream_ErrMsg').style.display="";
			contentFileStreamResult = false;
		}else{
			document.getElementById('contentFileStream_ErrMsg').style.display="none";
			contentFileStreamResult=true;
		}
	}
	
	if(mode != null && mode == "add"){	
		if( classIdResult && subjectIdResult && topicResult && 
			subTopicResult && contentTypeResult && contentFileStreamResult)
			return true;
		else
			return false;	
	}	
	
	if(mode != null && mode == "update"){
// alert("classIdResult:"+classIdResult+"subjectIdResult:"+subjectIdResult+"topicResult:"+
// topicResult+"subTopicResult:"+subTopicResult+"contentTypeResult:"+contentTypeResult);
		if( classIdResult && subjectIdResult && topicResult && subTopicResult && contentTypeResult){		
			// alert("True");
			return true;
			
		}
		else{
			// alert("false");
			return false;
		}	
	}
	
}

function valSchoolContentUploadPg(mode) {
	var schoolContentFileStreamResult;
	if(mode != null && mode == "add") {	
		
		if(document.getElementById('contentFileStream') == null){
			document.getElementById('contentFileStream_ErrMsg').style.display="";
			schoolContentFileStreamResult = false;
		} else if(document.getElementById('contentFileStream') != null) {
			document.getElementById('contentFileStream_ErrMsg').style.display="none";
			schoolContentFileStreamResult = true;
		}
	}
return schoolContentFileStreamResult;
}

function validateCancel() {
	location.reload("index.jsp");
	return true;
	
}
function validateManageQ(){
		 
		var valResult = true;
		
		var subject = document.getElementById('subject').value;
		var topic = document.getElementById('topic').value;
		var subtopic = document.getElementById('subTopic').value;
		var question = document.getElementById('question').value;
		var option1 = document.getElementById('option1').value;
		var option2 = document.getElementById('option2').value;
		var option3 = document.getElementById('option3').value;
		var option4 = document.getElementById('option4').value;
		var option5 = document.getElementById('option5').value;
		var answer = document.getElementById('answer').value;
		//var explanation = document.getElementById('answerDiscription').value;
		var cert = document.getElementById('cert').value;
		
		var questionComplexity = document.getElementById('complexity').value;
		
		
		// Handle Image Details
		var question_Img = document.getElementById('questionImage').value;		
		var option1_Img = document.getElementById('ansOneImage').value;		
		var option2_Img = document.getElementById('ansTwoImage').value;
		var option3_Img = document.getElementById('ansThreeImage').value;		
		var option4_Img = document.getElementById('ansFourImage').value;
		var option5_Img = document.getElementById('ansFiveImage').value;
		var ans_Exp_Img = document.getElementById('ansExplanation').value;
		
		// var questionImg_editAction = document.getElementByName
		// ('questionImg_editAction').value;
		// var option1Img_editAction =
		// document.getElementByName('option1Img_editAction').value;
		// var option2Img_editAction =
		// document.getElementByName('option2Img_editAction').value;
		// alert('questionImg_editAction: '+ questionImg_editAction);
						
		if(subject == null || subject == ""){
			document.getElementById('subject_ErrMsg').style.display="";
			valResult = false;
		
		}else{
			document.getElementById('subject_ErrMsg').style.display="none";
		}
		if(topic == null || topic == ""){
			document.getElementById('topic_ErrMsg').style.display="";
			valResult = false;
		}else{
			document.getElementById('topic_ErrMsg').style.display="none";
		}
		if(subtopic == null || subtopic == ""){
			document.getElementById('subtopic_ErrMsg').style.display="";
			valResult = false;
		}else{
			document.getElementById('subtopic_ErrMsg').style.display="none";
		}
		if((question == null || question == "") && (question_Img == null || question_Img == "") ){
			document.getElementById('question_ErrMsg').style.display="";
			valResult = false;
		}else{
			document.getElementById('question_ErrMsg').style.display="none";
		}
		if((option1 == null || option1 == "") && (option1_Img == null || option1_Img == "" ) ){
			document.getElementById('option1_ErrMsg').style.display="";
			valResult = false;
		}else{
			document.getElementById('option1_ErrMsg').style.display="none";
		}
		if( (option2 == null || option2 == "") && (option2_Img == null || option2_Img == "" ) ){
			document.getElementById('option2_ErrMsg').style.display="";
			valResult = false;
		}else{
			document.getElementById('option2_ErrMsg').style.display="none";
		}
		
// if(option3 == null || option3 == ""){
// document.getElementById('option3_ErrMsg').style.display="";
// valResult = false;
// }else{
// document.getElementById('option3_ErrMsg').style.display="none";
// }
// if(option4 == null || option4 == ""){
// document.getElementById('option4_ErrMsg').style.display="";
// valResult = false;
// }else{
// document.getElementById('option4_ErrMsg').style.display="none";
// }
// if(option5 == null || option5 == ""){
// document.getElementById('option5_ErrMsg').style.display="";
// valResult = false;
// }else{
// document.getElementById('option5_ErrMsg').style.display="none";
// }
		if(answer == null || answer == ""){
			document.getElementById('answer_ErrMsg').style.display="";
			valResult = false;
		}else{
			document.getElementById('answer_ErrMsg').style.display="none";
		}
		
		if(cert == null || cert == ""){
			document.getElementById('cert_ErrMsg').style.display="";
			valResult = false;
		}else{
			document.getElementById('cert_ErrMsg').style.display="none";
		}
		
		if(questionComplexity == null || questionComplexity == ""){
			document.getElementById('complexity_ErrMsg').style.display="";
			valResult = false;
		}else{
			document.getElementById('complexity_ErrMsg').style.display="none";
		}
		
		//if(explanation == null || explanation == ""){
			//document.getElementById('explanation_ErrMsg').style.display="";
		//	valResult = false;
		//}else{
			//document.getElementById('explanation_ErrMsg').style.display="none";
		//}
		// alert('valResult: '+valResult);
		
		return valResult;
}
		
function validateInputFields() {
	var valResult = true;
	var classId = document.getElementById('sch_classID').value;
	var subject = document.getElementById('subjectID').value;
	
	if(classId == null || classId == ""){
		document.getElementById('sch_classType_ErrMsg').style.display="";
		valResult = false;
	
	}else{
		document.getElementById('sch_classType_ErrMsg').style.display="none";
	}
	if(subject == null || subject == ""){
		document.getElementById('sch_subject_ErrMsg').style.display="";
		valResult = false;
	
	}else{
		document.getElementById('sch_subject_ErrMsg').style.display="none";
	}
	return valResult;
}

// Function removes if error messages displayed for
// controls on form
function resetManageQ(){

		document.getElementById('subject_ErrMsg').style.display="none";
		document.getElementById('topic_ErrMsg').style.display="none";
		document.getElementById('subtopic_ErrMsg').style.display="none";
		document.getElementById('question_ErrMsg').style.display="none";
		document.getElementById('option1_ErrMsg').style.display="none";
		document.getElementById('option2_ErrMsg').style.display="none";
		document.getElementById('option3_ErrMsg').style.display="none";
		document.getElementById('option4_ErrMsg').style.display="none";
		document.getElementById('option5_ErrMsg').style.display="none";
		document.getElementById('answer_ErrMsg').style.display="none";
		document.getElementById('explanation_ErrMsg').style.display="none";	
}

function resetFullReg(){
	document.getElementById('firstName_ErrMsg').style.display="none";
	document.getElementById('lastName_ErrMsg').style.display="none";
	document.getElementById('emailID_ErrMsg').style.display="none";
	document.getElementById('password_ErrMsg').style.display="none";
	document.getElementById('repassword_ErrMsg').style.display="none";
	document.getElementById('address_ErrMsg').style.display="none";
	document.getElementById('city_ErrMsg').style.display="none";
	document.getElementById('pin_ErrMsg').style.display="none";
	document.getElementById('country_ErrMsg').style.display="none";
	document.getElementById('alternateEmailID_ErrMsg').style.display="none";
	document.getElementById('mobileno_ErrMsg').style.display="none";
	document.getElementById('landlineno_ErrMsg').style.display="none";
}

function validateFullReg(){
	
	
	var valResult = true;
	var ismandatoryFilled = true;
	
	var firstName = document.getElementById('firstName').value;
	var lastName = document.getElementById('lastName').value;
	var emailID = document.getElementById('emailID').value;
	var password = document.getElementById('password').value;
	var repassword = document.getElementById('repassword').value;
	
	var address = document.getElementById('address').value;
	var city = document.getElementById('city').value;
	var pin = document.getElementById('pin').value;
	var country = document.getElementById('country').value;
	// var alternateEmailID = document.getElementById('alternateEmailID').value;
	var mobNoPart1 = document.getElementById('mobNoPart1').value;
	var mobNoPart2 = document.getElementById('mobNoPart2').value;
	// var landNoPart1 = document.getElementById('landNoPart1').value;
	// var landNoPart2 = document.getElementById('landNoPart2').value;
	
	var state = document.getElementById('state').value;
	var region = document.getElementById('region').value;
	var std = document.getElementById('std').value;
	var isd = document.getElementById('isd').value;
	var landline = document.getElementById('landline').value;
	
	var Result_FirstName = false;
	var Result_LastName = false;
	var Result_EmailID = false;
	var Result_Password = false;
	var Result_rePassword = false;
	var Result_address = false;
	var Result_city = false;
	var Result_pin = false;
	var Result_Country = false;
	var Result_AltrEmailID = false;
	var Result_MobileNo = false;
	var Result_LandlineNo = false;
	var Result_Region = false;
	var Result_State = false;
	var Result_Std = false;
	var Result_Isd = false;
	var Result_Lnadline = false;
	var is_IsdSet = false;
	var is_StdSet = false;
	var is_landlineSet = false;
	var is_passwordEqual=false;
	
	// for mandatory field
	if(stripBlanks(firstName)==''){
		Result_FirstName = false;
		document.getElementById('firstName_ErrMsg').innerHTML = "FirstName is Required";
		document.getElementById('firstName_ErrMsg').style.display='';
	}else{		
		if(!isAlpha(firstName)){
			Result_FirstName = false;
			document.getElementById('firstName_ErrMsg').innerHTML = "Invalid Entry";
			document.getElementById('firstName_ErrMsg').style.display='';
		}else{
			Result_FirstName = true;
			document.getElementById('firstName_ErrMsg').style.display='none';	
		}
	}
	
	
	
	// for mandatory field
	if(stripBlanks(lastName)==''){
		Result_LastName = false;
		document.getElementById('lastName_ErrMsg').innerHTML = "LastName is Required";
		document.getElementById('lastName_ErrMsg').style.display='';
	}else{		
		if(!isAlpha(lastName)){
			Result_LastName = false;
			document.getElementById('lastName_ErrMsg').innerHTML = "Invalid Entry";
			document.getElementById('lastName_ErrMsg').style.display='';
		}else{
			Result_LastName = true;
			document.getElementById('lastName_ErrMsg').style.display='none';	
		}
	}	
	
	// for mandatory field
	if(stripBlanks(password)==''){
		Result_Password = false;		
		document.getElementById('password_ErrMsg').style.display='';
	}else{
		Result_Password = true;
		document.getElementById('password_ErrMsg').style.display='none';
	}
	
	if(stripBlanks(repassword)==''){
		Result_rePassword = false;		
		document.getElementById('repassword_ErrMsg').style.display='';
	}else{
		Result_rePassword = true;
		document.getElementById('repassword_ErrMsg').style.display='none';
	}
	
	// for password and retype password check
	if(password==repassword){
		is_passwordEqual=true;
		document.getElementById('repassword_ErrMsg').style.display='none';
	}else{
		document.getElementById('repassword_ErrMsg').innerHTML="Password and ReType Password Should be same";
		document.getElementById('repassword_ErrMsg').style.display='';
		is_passwordEqual=false;
	}
	
	// for mandatory field
	if(stripBlanks(address)==''){
		Result_address = false;		
		document.getElementById('address_ErrMsg').style.display='';
	}else{
		Result_address = true;
		document.getElementById('address_ErrMsg').style.display='none';
	}
	
	// for mandatory field
	if(stripBlanks(city)==''){
		Result_city = false;
		document.getElementById('city_ErrMsg').innerHTML = "City is Required";
		document.getElementById('city_ErrMsg').style.display='';
	}else{		
		if(!isAlpha(city)){
			Result_city = false;
			document.getElementById('city_ErrMsg').innerHTML = "Invalid Entry";
			document.getElementById('city_ErrMsg').style.display='';
		}else{
			Result_city = true;
			document.getElementById('city_ErrMsg').style.display='none';	
		}
	}
	
	// for mandatory field
	if(stripBlanks(pin)==''){
		Result_pin = false;
		document.getElementById('pin_ErrMsg').innerHTML = "Pin is Required";
		document.getElementById('pin_ErrMsg').style.display='';
	}else{		
		if(!isNumber(pin) || pin.length != 6){
			Result_pin = false;
			document.getElementById('pin_ErrMsg').innerHTML = "Invalid Entry";
			document.getElementById('pin_ErrMsg').style.display='';
		}else{
			Result_pin = true;
			document.getElementById('pin_ErrMsg').style.display='none';	
		}
	}
	
	// for mandatory field
	if(stripBlanks(country)==''){
		Result_Country = false;
		document.getElementById('country_ErrMsg').innerHTML = "Country is Required";
		document.getElementById('country_ErrMsg').style.display='';
	}else{
		Result_Country = true;
		document.getElementById('country_ErrMsg').style.display='none';
	}
	
	// for mandatory field
	if(stripBlanks(mobNoPart1)=='' || stripBlanks(mobNoPart2)=='' ){
		Result_MobileNo = false;
		document.getElementById('mobileno_ErrMsg').innerHTML = "MobileNo is Required";
		document.getElementById('mobileno_ErrMsg').style.display='';
	}else{		
		// alert('mob not blank condt');
		// alert('mobNoPart1: '+ mobNoPart1)
		// alert('!isNumber(mobNoPart2): '+ !isNumber(mobNoPart2));
		// alert("!( mobNoPart1 == '+91' || mobNoPart1 == '0')"+ !( mobNoPart1
		// == '+91' || mobNoPart1 == '0'));
		// alert('Lenght: '+mobNoPart2.length != 10);
		if(!isNumber(mobNoPart2) || (mobNoPart2.length != 10) || !( mobNoPart1 == '+91' || mobNoPart1 == '0') ){
			Result_MobileNo = false;
			document.getElementById('mobileno_ErrMsg').innerHTML = "Invalid Entry";
			document.getElementById('mobileno_ErrMsg').style.display='';
		}else{
			Result_MobileNo = true;
			document.getElementById('mobileno_ErrMsg').style.display='none';	
		}
	}
	
	// For Mandatory field
	if( stripBlanks(emailID)==''){
		Result_EmailID = false;
		document.getElementById('emailID_ErrMsg').innerHTML = "EmailID is Required";
		document.getElementById('emailID_ErrMsg').style.display='';	
	}else{
		if( !isValidEmail(emailID) ){			
			Result_EmailID = false;
			document.getElementById('emailID_ErrMsg').innerHTML = "Invalid EmailID";
			document.getElementById('emailID_ErrMsg').style.display='';
		}else{
			Result_EmailID = true;
			document.getElementById('emailID_ErrMsg').style.display='none';	
		}
	}
	
	// for mandatory field
	if(stripBlanks(region)==''){
		Result_Region = false;
		document.getElementById('region_ErrMsg').innerHTML = "Region is Required";
		document.getElementById('region_ErrMsg').style.display='';
	}else{
		Result_Region = true;
		document.getElementById('region_ErrMsg').style.display='none';
	}
	
	// for mandatory field
	if(stripBlanks(state)==''){
		Result_State = false;
		document.getElementById('state_ErrMsg').innerHTML = "State is Required";
		document.getElementById('state_ErrMsg').style.display='';
	}else{
		Result_State = true;
		document.getElementById('state_ErrMsg').style.display='none';
	}
	
	if( stripBlanks(isd)=='ISD'){
		document.getElementById('isd').value="";
	}
	if(stripBlanks(std)=='STD'){
		document.getElementById('std').value="";
	}
	if(stripBlanks(landline)=='LANDLINE'){
		document.getElementById('landline').value="";
	}
	
	// return true mandatory filled and validation is true
	if(Result_FirstName && Result_LastName && Result_EmailID
		&& Result_Password && Result_rePassword && Result_address && Result_city
		&& Result_pin && Result_Country && is_passwordEqual && Result_MobileNo ){
		return true;
	}else{
		return false;
	}
	
}


function validateSessionFalirePage(){
	// alert('In validateSessionFalirePage()');
	var username = document.getElementById('username').value;
	var password = document.getElementById('password').value;
	
	var Result_username = false;
	var Result_password = false;
	
	// for mandatory field username
	if(stripBlanks(username)==''){
		Result_username = false;
		document.getElementById('username_ErrMsg').style.display='';
	}else{
		Result_username = true;
		document.getElementById('username_ErrMsg').style.display='none';
	}
	
	// for mandatory field password
	if(stripBlanks(password)==''){
		Result_password = false;
		document.getElementById('password_ErrMsg').style.display='';
	}else{
		Result_password = true;
		document.getElementById('password_ErrMsg').style.display='none';
	}	
	
	if(Result_password && Result_username ){
		return true;
	}else{
		return false;
	}
}


var numb = '0123456789';
var lwr = 'abcdefghijklmnopqrstuvwxyz';
var upr = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ';

function isValid(parm,val) {
if (parm == "") return true;
for (i=0; i<parm.length; i++) {
if (val.indexOf(parm.charAt(i),0) == -1) return false;
}
return true;
}

function stripBlanks(fld) {
var result = "";
var c = 0;
for (i=0; i<fld.length; i++) {
if (fld.charAt(i) != " " || c > 0) {
result += fld.charAt(i);
if (fld.charAt(i) != " ") c = result.length;
}
}
return result.substr(0,c);
} 
 


function isNumber(parm) {return isValid(parm,numb);}
function isLower(parm) {return isValid(parm,lwr);}
function isUpper(parm) {return isValid(parm,upr);}
function isAlpha(parm) {return isValid(parm,lwr+upr);}
function isAlphanum(parm) {return isValid(parm,lwr+upr+numb);} 


function isValidEmail(str) {

		var at="@";
		var dot=".";
		var lat=str.indexOf(at);
		var lstr=str.length;
		var ldot=str.indexOf(dot);
		if (str.indexOf(at)==-1){
		   // alert("Invalid E-mail ID")
		   return false;
		}

		if (str.indexOf(at)==-1 || str.indexOf(at)==0 || str.indexOf(at)==lstr){
		  // alert("Invalid E-mail ID")
		   return false;
		}

		if (str.indexOf(dot)==-1 || str.indexOf(dot)==0 || str.indexOf(dot)==lstr){
		    // alert("Invalid E-mail ID")
		    return false;
		}

		 if (str.indexOf(at,(lat+1))!=-1){
		    // alert("Invalid E-mail ID")
		    return false;
		 }

		 if (str.substring(lat-1,lat)==dot || str.substring(lat+1,lat+2)==dot){
		    // alert("Invalid E-mail ID")
		    return false;
		 }

		 if (str.indexOf(dot,(lat+2))==-1){
		   // alert("Invalid E-mail ID")
		    return false;
		 }
		
		 if (str.indexOf(" ")!=-1){
		    // alert("Invalid E-mail ID")
		    return false;
		 }

 		 return true;					
	}

function ValidateForm(){
	var emailID=document.frmSample.txtEmail;
	
	if ((emailID.value==null)||(emailID.value=="")){
		// alert("Please Enter your Email ID")
		emailID.focus();
		return false;
	}
	if (echeck(emailID.value)==false){
		emailID.value="";
		emailID.focus();
		return false;
	}
	return true;
 }

function validateAgentFullReg() {
var valResult = true;
var ismandatoryFilled = true;

var firstName = document.getElementById('firstName').value;
var lastName = document.getElementById('lastName').value;
var emailID = document.getElementById('emailID').value;
var password = document.getElementById('password').value;
var repassword = document.getElementById('repassword').value;

var address = document.getElementById('address').value;
var city = document.getElementById('city').value;
var pin = document.getElementById('pin').value;
var country = document.getElementById('country').value;
// var alternateEmailID = document.getElementById('alternateEmailID').value;
var mobNoPart1 = document.getElementById('mobNoPart1').value;
var mobNoPart2 = document.getElementById('mobNoPart2').value;
// var landNoPart1 = document.getElementById('landNoPart1').value;
// var landNoPart2 = document.getElementById('landNoPart2').value;

var state = document.getElementById('state').value;
var region = document.getElementById('region').value;
var std = document.getElementById('std').value;
var isd = document.getElementById('isd').value;
var landline = document.getElementById('landline').value;

var Result_FirstName = false;
var Result_LastName = false;
var Result_EmailID = false;
var Result_Password = false;
var Result_rePassword = false;
var Result_address = false;
var Result_city = false;
var Result_pin = false;
var Result_Country = false;
var Result_AltrEmailID = false;
var Result_MobileNo = false;
var Result_LandlineNo = false;
var Result_Region = false;
var Result_State = false;
var Result_Std = false;
var Result_Isd = false;
var Result_Lnadline = false;
var is_IsdSet = false;
var is_StdSet = false;
var is_landlineSet = false;
var is_passwordEqual=false;

// for mandatory field
if(stripBlanks(firstName)==''){
	Result_FirstName = false;
	document.getElementById('firstName_ErrMsg').innerHTML = "FirstName is Required";
	document.getElementById('firstName_ErrMsg').style.display='';
}else{		
	if(!isAlpha(firstName)){
		Result_FirstName = false;
		document.getElementById('firstName_ErrMsg').innerHTML = "Invalid Entry";
		document.getElementById('firstName_ErrMsg').style.display='';
	}else{
		Result_FirstName = true;
		document.getElementById('firstName_ErrMsg').style.display='none';	
	}
}



// for mandatory field
if(stripBlanks(lastName)==''){
	Result_LastName = false;
	document.getElementById('lastName_ErrMsg').innerHTML = "LastName is Required";
	document.getElementById('lastName_ErrMsg').style.display='';
}else{		
	if(!isAlpha(lastName)){
		Result_LastName = false;
		document.getElementById('lastName_ErrMsg').innerHTML = "Invalid Entry";
		document.getElementById('lastName_ErrMsg').style.display='';
	}else{
		Result_LastName = true;
		document.getElementById('lastName_ErrMsg').style.display='none';	
	}
}	

// for mandatory field
if(stripBlanks(password)==''){
	Result_Password = false;		
	document.getElementById('password_ErrMsg').style.display='';
}else{
	Result_Password = true;
	document.getElementById('password_ErrMsg').style.display='none';
}

if(stripBlanks(repassword)==''){
	Result_rePassword = false;		
	document.getElementById('repassword_ErrMsg').style.display='';
}else{
	Result_rePassword = true;
	document.getElementById('repassword_ErrMsg').style.display='none';
}

// for password and retype password check
if(password==repassword){
	is_passwordEqual=true;
	document.getElementById('repassword_ErrMsg').style.display='none';
}else{
	document.getElementById('repassword_ErrMsg').innerHTML="Password and ReType Password Should be same";
	document.getElementById('repassword_ErrMsg').style.display='';
	is_passwordEqual=false;
}

// for mandatory field
if(stripBlanks(address)==''){
	Result_address = false;		
	document.getElementById('address_ErrMsg').style.display='';
}else{
	Result_address = true;
	document.getElementById('address_ErrMsg').style.display='none';
}

// for mandatory field
if(stripBlanks(city)==''){
	Result_city = false;
	document.getElementById('city_ErrMsg').innerHTML = "City is Required";
	document.getElementById('city_ErrMsg').style.display='';
}else{		
	if(!isAlpha(city)){
		Result_city = false;
		document.getElementById('city_ErrMsg').innerHTML = "Invalid Entry";
		document.getElementById('city_ErrMsg').style.display='';
	}else{
		Result_city = true;
		document.getElementById('city_ErrMsg').style.display='none';	
	}
}

// for mandatory field
if(stripBlanks(pin)==''){
	Result_pin = false;
	document.getElementById('pin_ErrMsg').innerHTML = "Pin is Required";
	document.getElementById('pin_ErrMsg').style.display='';
}else{		
	if(!isNumber(pin) || pin.length != 6){
		Result_pin = false;
		document.getElementById('pin_ErrMsg').innerHTML = "Invalid Entry";
		document.getElementById('pin_ErrMsg').style.display='';
	}else{
		Result_pin = true;
		document.getElementById('pin_ErrMsg').style.display='none';	
	}
}

// for mandatory field
if(stripBlanks(country)==''){
	Result_Country = false;
	document.getElementById('country_ErrMsg').innerHTML = "Country is Required";
	document.getElementById('country_ErrMsg').style.display='';
}else{
	Result_Country = true;
	document.getElementById('country_ErrMsg').style.display='none';
}

// for mandatory field
if(stripBlanks(mobNoPart1)=='' || stripBlanks(mobNoPart2)=='' ){
	Result_MobileNo = false;
	document.getElementById('mobileno_ErrMsg').innerHTML = "MobileNo is Required";
	document.getElementById('mobileno_ErrMsg').style.display='';
}else{		
	if(!isNumber(mobNoPart2) || (mobNoPart2.length != 10) || !( mobNoPart1 == '+91' || mobNoPart1 == '0') ){
		Result_MobileNo = false;
		document.getElementById('mobileno_ErrMsg').innerHTML = "Invalid Entry";
		document.getElementById('mobileno_ErrMsg').style.display='';
	}else{
		Result_MobileNo = true;
		document.getElementById('mobileno_ErrMsg').style.display='none';	
	}
}

// For Mandatory field
if( stripBlanks(emailID)==''){
	Result_EmailID = false;
	document.getElementById('emailID_ErrMsg').innerHTML = "EmailID is Required";
	document.getElementById('emailID_ErrMsg').style.display='';	
}else{
	if( !isValidEmail(emailID) ){			
		Result_EmailID = false;
		document.getElementById('emailID_ErrMsg').innerHTML = "Invalid EmailID";
		document.getElementById('emailID_ErrMsg').style.display='';
	}else{
		Result_EmailID = true;
		document.getElementById('emailID_ErrMsg').style.display='none';	
	}
}

// for mandatory field
if(stripBlanks(region)==''){
	Result_Region = false;
	document.getElementById('region_ErrMsg').innerHTML = "Region is Required";
	document.getElementById('region_ErrMsg').style.display='';
}else{
	Result_Region = true;
	document.getElementById('region_ErrMsg').style.display='none';
}

// for mandatory field
if(stripBlanks(state)==''){
	Result_State = false;
	document.getElementById('state_ErrMsg').innerHTML = "State is Required";
	document.getElementById('state_ErrMsg').style.display='';
}else{
	Result_State = true;
	document.getElementById('state_ErrMsg').style.display='none';
}

if( stripBlanks(isd)=='ISD'){
	document.getElementById('isd').value="";
}
if(stripBlanks(std)=='STD'){
	document.getElementById('std').value="";
}
if(stripBlanks(landline)=='LANDLINE'){
	document.getElementById('landline').value="";
}

// return true mandatory filled and validation is true
if(Result_FirstName && Result_LastName && Result_EmailID
	&& Result_Password && Result_rePassword && Result_address && Result_city
	&& Result_pin && Result_Country && is_passwordEqual && Result_MobileNo ){
	return true;
}else{
	return false;
}
}