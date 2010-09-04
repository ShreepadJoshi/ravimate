
var INVALID_LOGIN_MESSAGE = "Invalid Username or password!";
var EMAILID_EXIST_MESSAGE = "User with same EmailID exist!";
var EMAILID_NOT_EXIST_MESSAGE = "EmailID does not exist!";
var LOGIN_EXCEPTION_MESSAGE = "Some Problem in Login, Please Contact System Admin!";
var SENDMAIL_EXCEPTION_MESSAGE = "Some Problem in sending mail,Please Contact System Admin!";
var UNSUCCESSFULL_EXCEPTION_MSG = "User has been blocked, due to 5 unsuccessfull Attempts";

function abc(){
window.showModalDialog("dialogpage.html",window,"dialogHeight:100px;dialogWidth: 283px; dialogTop: 286px; edge: Raised; center: Yes; resizable: Yes; status: No;");
}

function ModalPopupsCustom1() {
    ModalPopups.Custom("idCustom1",
        "Enter address information",
        "<div style='padding: 25px;'>" + 
        "<table>" + 
        "<tr><td>Name</td><td><input type=text id='inputCustom1Name' style='width:250px;'></td></tr>" + 
        "<tr><td>Address</td><td><input type=text id='inputCustom1Address' style='width:250px;'></td></tr>" + 
        "<tr><td>ZipCode</td><td><input type=text id='inputCustom1ZipCode' style='width:100px;'></td></tr>" + 
        "<tr><td>City</td><td><input type=text id='inputCustom1City' style='width:250px;'></td></tr>" + 
        "<tr><td>Phone</td><td><input type=text id='inputCustom1Phone' style='width:250px;'></td></tr>" + 
        "<tr><td>E-Mail</td><td><input type=text id='inputCustom1EMail' style='width:250px;'></td></tr>" + 
        "</table>" + 
        "</div>", 
        {
            width: 500,
            buttons: "ok,cancel",
            okButtonText: "Save",
            cancelButtonText: "Cancel",
            onOk: "ModalPopupsCustom1Save()",
            onCancel: "ModalPopupsCustom1Cancel()"
        }
    );
            
    ModalPopups.GetCustomControl("inputCustom1Name").focus(); 
}

function ModalPopupsCustom1Save() {
    var custom1Name = ModalPopups.GetCustomControl("inputCustom1Name"); 
    if(custom1Name.value == "") {
        alert("Please submit a name to this form");
        custom1Name.focus();
    }
    else {
        alert("Your name is: " + custom1Name.value);
        ModalPopups.Close("idCustom1");
    }
}

function ModalPopupsCustom1Cancel() {
    //alert('You pressed Cancel');
    ModalPopups.Cancel("idCustom1");
}

function newUserSignup(){
	//alert('In newUserSignup()');	
	ModalPopups.Cancel("popup_loginID");
	newUserSignup_login();	
}

function forgotPassword(){
	//alert('forgotPassword()');	
	ModalPopups.Cancel("popup_loginID");
	showForgotPassword();
}

function showForgotPassword() {
	//alert('display forgot Password');
	setPopupDefaults();
    ModalPopups.Custom("newUserpopup_loginID",
        "forgot Password",
        "<div style='padding: 10px;' >" +
        "<table algin='left'>" +
        "<tr><td colspan='2'><label style='display: none;' id='abc_errMsg'>Invalid UserName </label></td></tr>"+         
        "<tr><td align='right'>Username:</td><td><input type='text' onkeypress=\"handleEnterKey(event,'signup')\" maxlength='50' size='10' id='signup_username' style='width:150px;'></td></tr>" + 
        "</table>" +	 
        "</div>",
        {
            width: 370,
            buttons: "ok,cancel",
            okButtonText: "Send Mail",
            cancelButtonText: "Cancel",
            onOk: "send_Mail_okbutton_EventHandler()",
            onCancel: "newUserSignUp_cancelbutton_EventHanderl()"
        }
    );            
    ModalPopups.GetCustomControl("username").focus(); 
}


function setPopupDefaults(){
	//alert('In setPopupDefaults()');
	ModalPopups.SetDefaults( 
		{
		 backgroundColor: "#FFFFFF", 
		 //titleBackColor: "#C1D2E7",
		 titleBackColor: "#22a7d3",
		 //titleBackColor: "#E4E4E4",
		 titleFontColor: "#FFFFFF",
		 popupBackColor: "#FFFFFF",
		 //popupBackColor: "#C7D6E9",
		 popupFontColor: "black",
		 //footerBackColor: "#C1D2E7",
		 footerBackColor: "#FFFFFF",
		 footerFontColor: "#15428B",
		 fontFamily: "Verdana,Arial",
		 fontSize: "9pt"   
		});
}

function setPopupDefaultsForSchoolPopup(){
	//alert('In setPopupDefaults()');
	ModalPopups.SetDefaults( 
		{
		 backgroundColor: "#FFFFFF", 
		 //titleBackColor: "#C1D2E7",
		 titleBackColor: "#22a7d3",
		 //titleBackColor: "#E4E4E4",
		 titleFontColor: "#FFFFFF",
		 popupBackColor: "#F0F0FF",
		 //popupBackColor: "#C7D6E9",
		 popupFontColor: "black",
		 //footerBackColor: "#C1D2E7",
		 footerBackColor: "#FFFFFF",
		 footerFontColor: "#15428B",
		 fontFamily: "Verdana,Arial",
		 fontSize: "9pt"  
		 
		});
}

function newUserSignup_login() {
	//alert('display loginDialog In login()');
	setPopupDefaults();
    ModalPopups.Custom("newUserpopup_loginID",
        "New user Sign In",
        "<div style='padding: 10px;' >" +
        "<table algin='left'>" +
        "<tr><td colspan='2'><label style='display: none;' id='abc_errMsg'>Invalid UserName or Password</label></td></tr>"+         
        "<tr><td align='right'>Username:</td><td><input type='text' onkeypress=\"handleEnterKey(event,'signup')\" maxlength='50' size='10' id='signup_username' style='width:150px;'></td></tr>" + 
        "<tr><td align='right'>Password:</td><td><input type='password' onkeypress=\"handleEnterKey(event,'signup')\" maxlength='50' size='10' id='signup_password1'   style='width:150px;'></td></tr>" +
        "<tr><td align='right'>Re-type Password:</td><td><input type='password' onkeypress=\"handleEnterKey(event,'signup')\" id='signup_password2' style='width:150px;'></td></tr>" +
        "</table>" +	 
        "</div>",
        {
            width: 370,
            buttons: "ok,cancel",
            okButtonText: "Register",
            cancelButtonText: "Cancel",
            onOk: "newUserSignUp_okbutton_EventHandler()",
            onCancel: "newUserSignUp_cancelbutton_EventHanderl()"
        }
    );            
  //Smita
    ModalPopups.GetCustomControl("signup_username").focus(); 
}

function login() {
	 //alert('display loginDialog In login()');
	setPopupDefaults();
    ModalPopups.Custom("popup_loginID",
        "User Login",
        "<div style='padding: 25px;'>" +
        "<table algin='left'>" +
        "<tr><td colspan='2'><label style='display: none;' id='abc_errMsg'>Invalid UserName or Password</label></td></tr>"+         
        "<tr><td >Username:</td><td><input type='text' maxlength='50' size='10' id='login_username' onkeypress=\"handleEnterKey(event,'login')\"  style='width:150px;' class='text'></td></tr>" + 
        "<tr><td >Password:</td><td><input type='password' maxlength='50' size='10' id='login_password' onkeypress=\"handleEnterKey(event,'login')\" style='width:150px;' class='text'></td></tr>" +
        "<tr></tr>"+
        "<tr></tr>"+
        "<tr></tr>"+
        "<tr></tr>"+
        "<tr><td><a href='#' class='url' onclick='newUserSignup()'>NewUser Signup</a></td>" +
        
        "<td><a href='#' class='url' onclick='forgotPassword()' style='padding-left:55px;'>Forgot Password</a></td></tr>" +
        "</table>" +	 
        "</div>", 
        {
            width: 370,
            buttons: "ok,cancel",
            okButtonText: "Login",
            cancelButtonText: "Cancel",
            onOk: "okbutton_EventHandler()",
            onCancel: "cancelbutton_EventHanderl()"
        }
    );            
    ModalPopups.GetCustomControl("login_username").focus(); 
}

function display_schoolDetails(count) {
	 var schoolName= "" ;
	 var board= "" ;
	 var district= "" ;
	 var state= "" ;
	 var postalAddr= "" ;
	 var pinCode= "" ;
	 var yearOfFoundation= "" ;
	 var status= "" ;
	 var mediumOdIntroduction= "" ;
	 
	 var table = document.getElementById("row");
	    var rows = table.getElementsByTagName("tr");
	   
	    for (i = 0; i < rows.length; i++) {
	       if(count == i){
	    	   var column =  rows[i].getElementsByTagName("td");
	    	       schoolName =column[0].innerHTML; 
	    	       	postalAddr = column[1].innerHTML;
	    	         city = column[2].innerHTML;
	    	         pinCode = column[3].innerHTML;
	    	         webSite = column[4].innerHTML;
	    		     board = column[5].innerHTML;
	    		     category = column[6].innerHTML;
	    		     type = column[7].innerHTML;
	    		     contactPerson = column[8].innerHTML;
	    		     contactNumber = column[9].innerHTML;
	    		    
	       } 
	    }
	setPopupDefaultsForSchoolPopup();
	ModalPopups.Custom("popup_SchoolDetails",
	"School Details",
   "<div style='padding: 25px;'>" +
   "<table width='100' border='0' align='center' cellpadding='0' cellspacing='10'>" +
   "<tr><td class = 'input_label'>School Name :</td><td class = 'input_field' maxlength='40' size='40' readOnly='true' value="+schoolName+"></td></tr> "+
	"<tr><td class = 'input_label'>Address: </td><td colspan='2' ><label><input name='Address1' type='text'   class = 'input_field' maxlength='40' size='40' readOnly='true' value="+postalAddr+"></label></td></tr> "+
	"<tr><td> </td><td colspan='2'><label><input name='Address2' type='text'  class = 'input_field' maxlength='40' size='40' readOnly='true'></label></td></tr>" +
	"<tr><td  class = 'input_label'>City: </td><td colspan='0' ><label><input name='City' type='text' class = 'input_field' maxlength='20'  size='20' readOnly='true' value="+postalAddr+"></td><td  width='80' class = 'input_label' >PIN / Zip: </td><td colspan='0' ><label><input name='Pin' type='text' class = 'input_field' maxlength='8' size='8' readOnly='true' value="+pinCode+" /></td></tr> " +
	"<tr><td  class = 'input_label'>Web Site: </td><td colspan='1'><label><input name='WebSite' type='text' class = 'input_field' maxlength='40' size='40' readOnly='true'  value="+webSite+"></td></tr>" +
	"<tr><td  class = 'input_label'>Board: </td><td colspan='1'><label><input name='Board' type='text' class = 'input_field'  maxlength='20' size='20' readOnly='true' value="+board+"></td><td rowspan='5' colspan='3' align='left' valign='middle'><iframe frameborder='0' scrolling='no' width='240' height='169' marginheight='10' marginwidth='10' src='http://maps.google.co.in/maps?hl=en&amp;q=adding+google+map+to+website&amp;ie=UTF8&amp;showlabs=1&amp;ll=18.516825,73.842092&amp;spn=0.043989,0.063605&amp;output=embed'></iframe></td></tr>"+
	"<tr><td  class = 'input_label'>Category</td><td colspan='0'><label><input name='PvtGovt' type='text' class = 'input_field' maxlength='15'  size='15' readOnly='true' value="+category+"></td></tr> " +
	"<tr><td  class = 'input_label' >Type: </td><td colspan='0' ><label><input name='InstituteType' type='text' class = 'input_field' maxlength='20'  size='20' readOnly='true'  value="+type+"></td></tr> "+
	"<tr><td  class = 'input_label'>Contact Person: </td><td><label><input name='ContactPerson' type='text' class = 'input_field' maxlength='25' size='25'  readOnly='true' value="+contactPerson+"></td></tr>" +
	"<tr><td  class = 'input_label' >Contact No: </td><td><label><input name='ContactNo' type='text' class = 'input_field' maxlength='12'  size='12' readOnly='true' value="+contactNumber+"></td></tr> "+
	"<tr><td colspan='2' align='right' valign='bottom'><a href='#' onClick='cancel()'>Back to school list</a></td></tr> "+
   "</div>" ,
   {
		buttons: "",
       width: 650,
       height:50
   }            
	);
}


function cancel() {
	ModalPopups.Cancel("popup_SchoolDetails");
}


function okbutton_EventHandler() {
    var username= ModalPopups.GetCustomControl("login_username");
    var password = ModalPopups.GetCustomControl("login_password");
    var errorMsg = ModalPopups.GetCustomControl("abc_errMsg");
     
    if(username.value == "") {
        alert("Please Enter Username");
        username.focus();
    }  else if(password.value == ""){
        alert("Please Enter Password");
        password.focus();  
        return false;
   }  else {    	
    	loginAjaxRequest(DOMAIN_NAME+"login.do?username="+username.value+"&password="+password.value+"&loginType=login");
		//loginAjaxRequest("login.do"); //works
		//loginAjaxRequest("LoginServlet?username="+username.value+"&password="+password.value);    
		//loginAjaxRequest("login.do"); 	
    }
    return true;
}

function newUserSignUp_cancelbutton_EventHanderl() {
    ModalPopups.Cancel("newUserpopup_loginID");
}


function newUserSignUp_okbutton_EventHandler() {

    var username = ModalPopups.GetCustomControl("signup_username");
    var password1 = ModalPopups.GetCustomControl("signup_password1");
    var password2 = ModalPopups.GetCustomControl("signup_password2");
    //alert('username----:'+username.value+' pass1----:'+password1.value+' pass2------:'+password2.value); 
    
    	if(username.value == "") {
	        alert("Please Enter Username");
	        ModalPopups.GetCustomControl("signup_username").focus();
	    } else if(emailcheck(username.value) == false) {
	    		ModalPopups.GetCustomControl("signup_username").focus();
	    		return false;
	    } else if(password1.value == "") {
	        alert("Please Enter Password");
	        ModalPopups.GetCustomControl("signup_password1").focus();   
	        return false;
	    } else if(passwardCheck(password1.value,username.value)== false) {
	    	ModalPopups.GetCustomControl("signup_password1").focus();
	    } else if(password2.value == "") {
	        alert("Enter the second Password");
	        ModalPopups.GetCustomControl("signup_password2").focus();
	        return false;
	    } else if(password1.value != password2.value) {
	    		alert("Re - Type Password correctly");
	            password2.focus();
	            return false;
	    } else {
			//alert("sending ajax req");
			//loginAjaxRequest("login.do?username="+username.value+"&password="+password.value);
			loginAjaxRequest(DOMAIN_NAME+"login.do?username="+username.value+"&password1="+password1.value+"&password2="+password2.value+"&loginType=signup");    
			//loginAjaxRequest("login.do"); 
		}
   return true;
}

function passwardCheck(password,username) {
	if (password != "") {
		if (password.length < 6) {
			alert("Error: Password must contain at least six characters!");
			ModalPopups.GetCustomControl("signup_password1").focus();
			return false;
		}
		if (password == username) {
			alert("Error: Password must be different from Username!");
			ModalPopups.GetCustomControl("signup_password1").focus();
			return false;
		}
		re = /[0-9]/;
		if (!re.test(password)) {
			alert("Error: password must contain at least one number (0-9)!");
			ModalPopups.GetCustomControl("signup_password1").focus();
			return false;
		}
		re = /[a-z]/;
		if (!re.test(password)) {
			alert("Error: password must contain at least one lowercase letter (a-z)!");
			ModalPopups.GetCustomControl("signup_password1").focus();
			return false;
		}
		re = /[A-Z]/;
		if (!re.test(password)) {
			alert("Error: password must contain at least one uppercase letter (A-Z)!");
			ModalPopups.GetCustomControl("signup_password1").focus();
			return false;
		}
	}
	return true;
}  


function send_Mail_okbutton_EventHandler() {
	//alert('In okbutton_EventHandler()');
    var username = ModalPopups.GetCustomControl("signup_username");    
    //alert('username:'+username.value+' pass1:'+password1.value+' pass2:'+password2.value); 
    if(username.value == "") {
        alert("Please Enter Username (eMail id)");
        username.focus();
    }
    else{    	
    	 //alert("sending ajax req");		
    	//sendMailAjaxRequest(DOMAIN_NAME+"sendMailofforgotPassword.do?username="+username.value); // not working
    	sendMailAjaxRequest("sendMailofforgotPassword.do?username="+username.value);
		//alert("after sending ajax req");
    }
}

function cancelbutton_EventHanderl() {
    ModalPopups.Cancel("popup_loginID");
}

function processLoginResponse(p_respText){
	
	
	
	if(p_respText != null && p_respText == "INVALID_LOGIN"){
	
		ModalPopups.Alert("jsAlert1",
	        "Login Status",
	        "<div style='padding:25px;'>"+INVALID_LOGIN_MESSAGE+"</div>", 
	        {
	            okButtonText: "Close"
	        }
    	);
	}else if(p_respText != null && p_respText == "LOGIN_EXCEPTION"){
		ModalPopups.Alert("jsAlert1",
	        "Login Status",
	        "<div style='padding:25px;'>"+LOGIN_EXCEPTION_MESSAGE+"</div>", 
	        {
	            okButtonText: "Close"
	        }
    	);
	}else if(p_respText != null && p_respText == "SAME_EMAILID_EXIST"){
		ModalPopups.Alert("jsAlert1",
	        "Login Status",
	        "<div style='padding:25px;'>"+EMAILID_EXIST_MESSAGE+"</div>", 
	        {
	            okButtonText: "Close"
	        }
    	);
	}else if(p_respText != null && p_respText == "UNSUCCESSFULL_EXCEPTION_MSG"){
		ModalPopups.Alert("jsAlert1",
		        "Login Status",
		        "<div style='padding:25px;'>"+UNSUCCESSFULL_EXCEPTION_MSG+"</div>", 
		        {
		            okButtonText: "Close"
		        }
	    	);
	}else if(p_respText != null && p_respText == "EMAILID_NOT_EXIST_MSG"){
		ModalPopups.Alert("jsAlert1",
		        "Send Mail Status",
		        "<div style='padding:25px;'>"+EMAILID_NOT_EXIST_MESSAGE+"</div>", 
		        {
		            okButtonText: "Close"
		        }
	    	);
	}
	else if(p_respText != null && p_respText == "SENDMAIL_EXCEPTION_MSG"){
		ModalPopups.Alert("jsAlert1",
		        "Login Status",
		        "<div style='padding:25px;'>"+SENDMAIL_EXCEPTION_MESSAGE+"</div>", 
		        {
		            okButtonText: "Close"
		        }
	    	);
	}	
	else{
		window.location=DOMAIN_NAME+"homepage.jsp";		
		//window.location.reload(true);	// causes the document to be reloaded from web server
		//window.location.replace("index.jsp"); //doc reloaded frm server, no entry is made in page history 		 	
	}	
}
//By Smita 31-July
function emailcheck(username) {
	
	var at="@";
	var dot=".";
	var lat=username.indexOf(at);
	var lstr=username.length;
	if (username.indexOf(at)==-1){
		alert("Username must be E-mail ID");
		return false;
	}
	if (username.indexOf(at)==-1 || username.indexOf(at)==0 || username.indexOf(at)==lstr){
	alert("Invalid E-mail ID");
	return false;
	}
	
	if (username.indexOf(dot)==-1 || username.indexOf(dot)==0 || username.indexOf(dot)==lstr){
	alert("Invalid E-mail ID");
	return false;
	}
	
	if (username.indexOf(at,(lat+1))!=-1){
	alert("Invalid E-mail ID");
	return false;
	}

	if (username.substring(lat-1,lat)==dot || username.substring(lat+1,lat+2)==dot){
	alert("Invalid E-mail ID");
	return false;
	}

	if (username.indexOf(dot,(lat+2))==-1){
	alert("Invalid E-mail ID");
	return false;
	}

	if (username.indexOf(" ")!=-1){
	alert("Invalid E-mail ID");
	return false;
	}
	return true ;
	}

	

function loginAjaxRequest(url) {
		var resText="";		
		
		if (window.ActiveXObject) { 
			xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
		else if (window.XMLHttpRequest) {
			xmlHttp = new XMLHttpRequest();
		}
		xmlHttp.open("POST",url);
		xmlHttp.onreadystatechange = function(){
			if (xmlHttp.readyState == 4) {
				if (xmlHttp.status == 200) {
					resText = xmlHttp.responseText;
					//alert("result Ajax call: "+ resText);
					processLoginResponse(resText);					 
				}
			}	
		};
		xmlHttp.send(null);
}

function sendMailAjaxRequest(url) {
	var resText="";		
	//alert('In send mail Ajax Request'+ url);
	
	if (window.ActiveXObject) { 
		xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	else if (window.XMLHttpRequest) {
		xmlHttp = new XMLHttpRequest();
	}
	xmlHttp.open("POST",url);
	xmlHttp.onreadystatechange = function(){
		if (xmlHttp.readyState == 4) {
			//alert('Ajax Req Success '+ xmlHttp.status);
			if (xmlHttp.status == 200) {
				resText = xmlHttp.responseText;
				//alert("result Ajax call: "+ resText);
				processLoginResponse(resText);					 
			}
		}	
	};
	xmlHttp.send(null);
}


/**
 * e is event object passed from function invocation
 * If Enter key is pressed depending on 'key' routes the submission
 * either to login or Signup
 */
function handleEnterKey(e,key){ 
	var characterCode //literal character code will be stored in this variable
	
	if(e && e.which){ //if which property of event object is supported (NN4)
		e = e
		characterCode = e.which //character code is contained in NN4's which property
	}
	else{
		e = e
		characterCode = e.keyCode //character code is contained in IE's keyCode property
	}
	
	if(characterCode == 13){ //if generated character code is equal to ascii 13 (if enter key)
		//submit form
		if(key == "login"){
			okbutton_EventHandler();
		}else if(key == "signup"){
			newUserSignUp_okbutton_EventHandler();
		}
	}
}





















