var xmlHttp;

/** GLOBAL CONSTANTS START **/
	
	var MANAGEQPG_SUBJECTDD = "1";
	var MANAGEQPG_TOPICDD = "2";
	var MANAGEQPG_SUBTOPICDD = "3";
	
	var CONTENTUPLOADPG_ADD_REC_SUBJECTDD = "4";
	var CONTENTUPLOADPG_ADD_REC_TOPICDD="5";
	var CONTENTUPLOADPG_ADD_REC_SUBTOPICDD = "6";
	
	var MANAGEQPG_SUBJECTDD_NEW = "7";	
	var MANAGEQPG_TOPICDD_NEW="8";
	var MANAGEQPG_SUBTOPICDD_NEW = "9";
	var QUESTIONLIST_ADD_REC_TOPICDD = "10";
	var QUESTIONLIST_ADD_REC_SUBJECTDD = "11";
	var MANAGEQUESTION_ADD_REC_SUBJECTDD = "12";
	var MANAGEQUESTION_ADD_REC_TOPICDD = "13";
	var MANAGEQUESTION_ADD_REC_CLASSDD = "14";
	
	var END = "7"; 
	
	/** On change Events **/
	var MANAGEQPG_SUBJECTDD_ONCHANGE_EVENT = "";
	var MANAGEQPG_TOPICDD_ONCHANGE_EVENT = "onchange=\"populateDropdown('subTopic','subjectId='+document.getElementById('subject').value+',topicValue='+this.value,'subtopiclist','ManageQsubTopic_divID',''+MANAGEQPG_SUBTOPICDD)\"";
	var MANAGEQPG_SUBTOPICDD_ONCHANGE_EVENT = ""
	
	var CONTENTUPLOADPG_ADD_REC_SUBJECTDD_ONCHANGE_EVENT ="onchange=\"populateDropdown('uptTopicId','classId='+document.getElementById('uptClassId').value+'','subjectId='+document.getElementById('uptsubjectId').value+'','0','topiclist','topicDropdown',''+CONTENTUPLOADPG_ADD_REC_TOPICDD)\"";
	
	var QUESTIONLIST_ADD_REC_SUBJECTDD_ONCHANGE_EVENT ="onchange=\"populateDropdown('sch_topic','classId='+document.getElementById('sch_classType').value+'','subjectId='+document.getElementById('sch_subject').value+'','0','topiclist','topicDropdown',''+QUESTIONLIST_ADD_REC_TOPICDD)\"";
	var CONTENTUPLOADPG_ADD_REC_TOPICDD_ONCHANGE_EVENT ="onchange=\"populateDropdown('uptsubTopicId','classId='+document.getElementById('uptClassId').value+'','subjectId='+document.getElementById('uptsubjectId').value+'','topicId='+document.getElementById('uptTopicId').value+'','subtopiclist','subTopicDropdown',''+CONTENTUPLOADPG_ADD_REC_SUBTOPICDD)\"";
	
	var CONTENTUPLOADPG_ADD_REC_SUBTOPICDD_ONCHANGE_EVENT = "";
	
	//var MANAGEQPG_SUBJECTDD_ONCHANGE_EVENT_NEW ="onchange=\"wrapperFunDropDown('uptTopicId','subjectId='+this.value,'topiclist','topicDropdown',''+MANAGEQPG_TOPICDD_NEW,'subjectId',this.value)\"";
	//var MANAGEQPG_TOPICDD_ONCHANGE_EVENT_NEW ="onchange=\"wrapperFunDropDown('uptsubTopicId','subjectId='+document.getElementById('uptsubjectId').value+',topicValue='+this.value,'subtopiclist','subTopicDropdown',''+MANAGEQPG_SUBTOPICDD_NEW,'topicId',this.value)\"";
	var MANAGEQPG_SUBTOPICDD_ONCHANGE_EVENT_NEW = "onchange=\"document.getElementById('subTopicId').value=this.value;\" ";
	
	var MANAGEQUESTION_SUBJECTDD_ONCHANGE_EVENT = "onchange=\"populateDropdown('topic','classId='+document.getElementById('cert').value+'','subjectId='+document.getElementById('subject').value+'','0','topiclist','topicDropdown',''+MANAGEQUESTION_ADD_REC_TOPICDD)\"";
	var MANAGEQUESTION_CLASSDD_ONCHANGE_EVENT = "";
	var MANAGEQUESTION_TOPICDD_ONCHANGE_EVENT ="onchange=\"populateDropdown('subTopic','classId='+document.getElementById('cert').value+'','subjectId='+document.getElementById('subject').value+'','topicId='+document.getElementById('topic').value+'','subtopiclist','subTopicDropdown',''+MANAGEQUESTION_TOPICDD_ONCHANGE_EVENT)\"";
/** GLOBAL CONSTANTS END **/


	function ajaxRequest(url) {
		//alert('In ajaxRequest() url: '+ url);
		if (window.ActiveXObject) {
			xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
		else if (window.XMLHttpRequest) {
			xmlHttp = new XMLHttpRequest();
		}
		xmlHttp.open("GET", url);
		xmlHttp.onreadystatechange = callback;
		xmlHttp.send(null);
	}

	function callback() {
		//alert('In Callback()');
		if (xmlHttp.readyState == 4) {
			//alert('Ajax Req Success '+ xmlHttp.status);
			if (xmlHttp.status == 200) {
				var respHtml = xmlHttp.responseText;				 
				document.getElementById('MainbodyContent').innerHTML=respHtml;
			}
		}
	}
	

	/**
 	* Does ajax request for requested url.
 	* On success updates InnerHtml of elementID element
 	*/
	function doajaxRequest(url,elementID) {		
		//alert('In doajaxRequest() url: '+ url+' elementID:'+elementID);	
		
		if (window.ActiveXObject) {
			xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
		else if (window.XMLHttpRequest) {
			xmlHttp = new XMLHttpRequest();
		}
		xmlHttp.open("Post", url);
		xmlHttp.onreadystatechange = function() {
					//alert('In doajaxRequest() Callback');
					if (xmlHttp.readyState == 4) {
						//alert(' In doajaxRequest()Ajax Req Success '+ xmlHttp.status);
						if (xmlHttp.status == 200) {
							var respHtml = xmlHttp.responseText;				 
							document.getElementById(elementID).innerHTML=respHtml;
						}
					}
		};
		xmlHttp.send(null);
	}
		
	function populateTopics(subjectId,elementID){
		//alert('In populateTopicDropDownListBox() subjectId: '+ subjectId+' elementID:'+elementID);
		
		//make sure selected value is not empty
		if(!subjectId == ""){
			doajaxRequest(DOMAIN_NAME+"populateDropdownByAjax.do?subjectId="+subjectId+"&pickDropDownvalueFor=topicList",elementID);	
		}	
	}
	function populateSubTopics(subjectId,topicValue,elementID){
		//alert('In populateSubTopics() subjectId: '+ subjectId+' topicValue:'+topicValue+ ' elementID:'+elementID);
		
		//make sure selected value is not empty
		if(!subjectId == "" && !topicValue=="" ){
			doajaxRequest(DOMAIN_NAME+"populateDropdownByAjax.do?subjectId="+subjectId+"&topicValue="+topicValue+"&pickDropDownvalueFor=subtopiclist",elementID);	
		}	
	}

	/**
	 * Functions populates the drop down value
	 */
	function populateDropdown(dropDownName,classId,subjectId,topicId,listType,divID,onChangeEvent){	
		var equal = "=";
		var classAt = classId.indexOf(equal);
		var subjectAt = subjectId.indexOf(equal);
		var topicAt = topicId.indexOf(equal);
		var splitVlaues =  new Array();
		
		if((classId.substring(classAt+1) == "")) {
			splitVlaues[0] = "";
		} else if((classId.substring(classAt+1) != "")) {
			splitVlaues[0] = classId;
		}
		
		if((subjectId.substring(subjectAt+1) == "")) {
			splitVlaues[1] = "";
		} else if((subjectId.substring(subjectAt+1) != "") &&  (subjectId.substring(subjectAt+1) > 0)) {
			splitVlaues[1] = subjectId;
		}
		if((topicId.substring(topicAt+1) != ""  &&  topicId.substring(topicAt+1) > 0)) {
			splitVlaues[2] = topicId;
		} else if(topicId.substring(topicAt+1) == "") {
			splitVlaues[2] = "";
		}
		
	
		var values = "";
		for(var i=0; i<splitVlaues.length; i++ ){
			if(values == "") {
				values = splitVlaues[i];
			} else {
				values = values + "&" + splitVlaues[i];
			} 
		}
		
		var optionList = "<option value=\"Mat\">Maths</option>"+
						  "<option value=\"Eng\">English</option>"+
						  "<option value=\"Science\">Science</option>";
		
		var respAjaxHtml = "";
		
		
		var url = DOMAIN_NAME+"populateDropdownByAjax.do?"+values+"&pickDropDownvalueFor="+listType;
		//make sure selected value is not empty then do Ajax call
		if(splitVlaues != null && splitVlaues .length > 0){			
			if (window.ActiveXObject) {
				xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
			}else if (window.XMLHttpRequest) {
				xmlHttp = new XMLHttpRequest();
			}
			xmlHttp.open("Post", url);
			xmlHttp.onreadystatechange = function() {
				//alert('In doajaxRequest() Callback');
				if (xmlHttp.readyState == 4) {
					//alert(' In doajaxRequest()Ajax Req Success '+ xmlHttp.status);
					if (xmlHttp.status == 200) {
						respAjaxHtml = xmlHttp.responseText;
						var selectTag = prepareSelectTag(respAjaxHtml,dropDownName,onChangeEvent);
						//alert("selectTag: "+ selectTag);
						//alert('Previous InnerDIVContent: '+document.getElementById(divID).innerHTML);
						document.getElementById(divID).innerHTML = selectTag;
						//alert('New InnerDIVContent: '+document.getElementById(divID).innerHTML); 
					}
				}
			};//end ajax call back function
			xmlHttp.send(null);
		}//end nameValuePair If condition
	}
	
	
	function prepareSelectTag(optionList,dropDownName,onChangeEvent){	
		var startTag = "<select name='"+ dropDownName +"' id='"+ dropDownName +"' ";
		var endTag = "</select>";
		var selectTag = "";
		var styleClassAttribute = " class='input_field' "; 
		
		//alert("In prepareSelectTag() ");
		if(onChangeEvent == MANAGEQPG_SUBJECTDD)			
			startTag += MANAGEQPG_SUBJECTDD_ONCHANGE_EVENT;
		else if(onChangeEvent == MANAGEQPG_TOPICDD)
			startTag += MANAGEQPG_TOPICDD_ONCHANGE_EVENT;
		else if(onChangeEvent == MANAGEQPG_SUBTOPICDD)
			startTag += MANAGEQPG_SUBTOPICDD_ONCHANGE_EVENT;
		
		else if(onChangeEvent == CONTENTUPLOADPG_ADD_REC_SUBJECTDD)
			startTag += CONTENTUPLOADPG_ADD_REC_SUBJECTDD_ONCHANGE_EVENT;
		else if(onChangeEvent == QUESTIONLIST_ADD_REC_SUBJECTDD)
			startTag += QUESTIONLIST_ADD_REC_SUBJECTDD_ONCHANGE_EVENT;
		else if(onChangeEvent == CONTENTUPLOADPG_ADD_REC_TOPICDD)
			startTag += CONTENTUPLOADPG_ADD_REC_TOPICDD_ONCHANGE_EVENT;
		else if(onChangeEvent == CONTENTUPLOADPG_ADD_REC_SUBTOPICDD)
			startTag += CONTENTUPLOADPG_ADD_REC_SUBTOPICDD_ONCHANGE_EVENT;
		else if(onChangeEvent == MANAGEQPG_SUBTOPICDD_NEW )
			startTag += MANAGEQPG_SUBTOPICDD_ONCHANGE_EVENT_NEW;
		else if(onChangeEvent == MANAGEQUESTION_ADD_REC_SUBJECTDD) 
			startTag += MANAGEQUESTION_SUBJECTDD_ONCHANGE_EVENT;
		else if (onChangeEvent == MANAGEQUESTION_ADD_REC_TOPICDD) 
			startTag += MANAGEQUESTION_TOPICDD_ONCHANGE_EVENT;
		else if (onChangeEvent == MANAGEQUESTION_ADD_REC_CLASSDD) 
				startTag += MANAGEQUESTION_CLASSDD_ONCHANGE_EVENT;	
		else if(onChangeEvent == END)
			startTag += "";
					
		startTag += styleClassAttribute+ " />";
		selectTag = startTag + optionList + endTag;		
		return selectTag;
	}

	function callback() {
		//alert('In Callback()');
		if (xmlHttp.readyState == 4) {
			//alert('Ajax Req Success '+ xmlHttp.status);
			if (xmlHttp.status == 200) {
				var respHtml = xmlHttp.responseText;				 
				document.getElementById('MainbodyContent').innerHTML=respHtml;
			}
		}
	}
	
	

function loadBodyAjaxCall(url,heading){
	//alert('In loadBodyAjaxCall() url: '+url+' heading: '+heading);
	if(heading != null){	
		document.getElementById("bodyContentHeader").innerHTML = heading;  //set body header
	}
	ajaxRequest(url);
	
}
// called when any service overview is clicked
function loadStudyMaterial(url,heading){
	//alert('In loadStudyMaterial() method');	
	if (window.ActiveXObject) {
			xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
		else if (window.XMLHttpRequest) {
			xmlHttp = new XMLHttpRequest();
		}
		xmlHttp.open("GET", url);
		xmlHttp.onreadystatechange = function(){
			if (xmlHttp.readyState == 4) {
			//alert('Ajax Req Success '+ xmlHttp.status);
				if (xmlHttp.status == 200) {
					document.getElementById('MainbodyContent').innerHTML = xmlHttp.responseText;
					document.getElementById("bodyContentHeader").innerHTML = heading;  //set body header
					loadLeftNavigationPanel("services.jsp");
				}
			}	
		};
		xmlHttp.send(null); 
}

// Function loads left navigation panel
function loadLeftNavigationPanel(url){
	//alert('In loadLeftNavigationPanel()');
	if (window.ActiveXObject) {
			xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
		else if (window.XMLHttpRequest) {
			xmlHttp = new XMLHttpRequest();
		}
		xmlHttp.open("GET", url);
		xmlHttp.onreadystatechange = function(){
			if (xmlHttp.readyState == 4) {
			//alert('Ajax Req Success '+ xmlHttp.status);
				if (xmlHttp.status == 200) {
					document.getElementById('leftNavigationPanelID').innerHTML = xmlHttp.responseText;					
				}
			}	
		};
		xmlHttp.send(null);
}

/**
 * Togles check/uncheck property of checkbox on form   
 * Takes selectAll Checkbox Id, hiddentextbox having list
 * of ids to be toggled.
 */ 
function checkAll(chkAllBoxID,idsTextboxID,hiddenTextBox_actionBtnIDs){
	//alert("In checkAll method");
	var idList = document.getElementById(idsTextboxID).value;
	var splitIdList = idList.split(",");
	var value;	
	
	if(document.getElementById(chkAllBoxID).checked){
		value ="checked";
		//alert('checked');
	}else{
		value="";
		//alert('Not checked');
	}
	for(i=0; i<splitIdList.length; i++ ){
		document.getElementById(splitIdList[i]).checked=value;
	}
	
	if(hiddenTextBox_actionBtnIDs != null && hiddenTextBox_actionBtnIDs != "" ){
		//alert("calling");
		toggle_ActionBtn(idsTextboxID,hiddenTextBox_actionBtnIDs);
		//alert("calling done");
	}
}



/**
 * Method enables or disables Action buttons on page
 * based on selected checkBox on grid
 */ 
function toggle_ActionBtn(hiddenTextBox_chkBoxIDs,hiddenTextBox_actionBtnIDs){
	//alert("In toggle_ActionBtn() method");	
	//alert("hiddenTextBox_actionBtnIDs: "+ hiddenTextBox_actionBtnIDs);
	//alert("hiddenTextBox_chkBoxIDs: "+ hiddenTextBox_chkBoxIDs);
	var checkBoxTextBox = document.getElementById(hiddenTextBox_chkBoxIDs).value;
	var actionBtnTextBox = document.getElementById(hiddenTextBox_actionBtnIDs).value;	
	
	var checkBoxIdList = checkBoxTextBox.split(",");
	var actionBtnIdList = actionBtnTextBox.split(",");
	//alert("DONE1");
	
	var toogleFlag = false;	
	//check If any checkbox is checked
	for(i=0; i<checkBoxIdList.length; i++ ){
		var checkBox = checkBoxIdList[i];
		//alert("checkbox: "+ checkBox);
		if(document.getElementById(checkBox).checked){
			toogleFlag = true;
			break;
		}
	}
	//alert("DONE2");
	//alert("ToggleFlag: "+ toogleFlag);
	//Toggle Action Button disable property
	for(i=0; i<actionBtnIdList.length; i++ ){
		var actionBtn = actionBtnIdList[i];
		if(toogleFlag)
			document.getElementById(actionBtn).disabled = "";
		else
			document.getElementById(actionBtn).disabled = "disabled";
		//alert("actionBtn: "+actionBtn);
	}
	//alert("DONE");
}

function enableImgInputForID(elmentID){
	//alert('In enableImgInputForID() elmentID:'+elmentID);	
	document.getElementById(elmentID).style.display = "";	
	//alert('done');	
}

function disableImgInputForID(elmentID){
	//alert('In disableImgInputForID() elmentID:'+elmentID);	
	document.getElementById(elmentID).style.display = "none";
	document.getElementById(elmentID).value = "";	
	//alert('done');	
}

function dispCntUploadUpdatePnl(){
	//document.getElementById('').style.display='';
}

function disableButton(actionPnlID){
	//alert("In disableButton() ref:"+ref);
	//ref.disabled="disabled";
	document.getElementById(actionPnlID).InnerHtml="Please Wait ....";
}

function setAnswerDD_ManageQ_CreatePg(ansDropDownElementID){

	var option1_Text = document.getElementById("option1").value;
	var option1_Img = document.getElementById("ansOneImage").value;
	
	var option2_Text = document.getElementById("option2").value;
	var option2_Img = document.getElementById("ansTwoImage").value;
	
	var option3_Text = document.getElementById("option3").value;
	var option3_Img = document.getElementById("ansThreeImage").value;
	
	var option4_Text = document.getElementById("option4").value;
	var option4_Img = document.getElementById("ansFourImage").value;
	
	var option5_Text = document.getElementById("option5").value;
	var option5_Img = document.getElementById("ansFiveImage").value;
	
	var ansDD_Element = document.getElementById(ansDropDownElementID);
	
	var ansList = new Array();
	var index = 0;
	
	if( (option1_Text != null && option1_Text != "") || (option1_Img != null && option1_Img != "")  )
		ansList[index++] = "Option1";
	if( (option2_Text != null && option2_Text != "") || (option2_Img != null && option2_Img != "")  )
		ansList[index++] = "Option2";
	if( (option3_Text != null && option3_Text != "") || (option3_Img != null && option3_Img != "")  )
		ansList[index++] = "Option3";
	if( (option4_Text != null && option4_Text != "") || (option4_Img != null && option4_Img != "")  )
		ansList[index++] = "Option4";
	if( (option5_Text != null && option5_Text != "") || (option5_Img != null && option5_Img != "")  )
		ansList[index++] = "Option5";
		
	//remove previous options from drop down 
	for(i=ansDD_Element.length-1; i>=0; i--)
    {
    	ansDD_Element.options[i] = null;
    }	
	//alert("ansList: "+ ansList.length);
	//alert("ansDD_Element.length:"+ansDD_Element.length);
	
	ansDD_Element.options[0] = new Option("Please Specify", ""); //set default option
	
	//populate the ans drop down
	for(i=0,j=1; i<ansList.length; i++,j++)
    {
      var newOpt = new Option(ansList[i], ansList[i]);
      ansDD_Element.options[j] = newOpt;
    }
    //ansDD_Element.options[0].selected = "Please Specify"; // set set default option as selected
    ansDD_Element.selectedIndex=0;
}

function displayFileUpload_Cntrl(element)
{
	if(element=="1"){
		document.getElementById("questionImage").style.display = "";
		document.getElementById("ansOneImage").style.display = "";
		document.getElementById("ansTwoImage").style.display = "";
		document.getElementById("ansThreeImage").style.display = "";
		document.getElementById("ansFourImage").style.display = "";
		document.getElementById("ansFiveImage").style.display = "";
		document.getElementById("ansExplanation").style.display = "";
		//document.getElementById("isgraphicFlag").value="0";
		//document.getElementById("isPictureQ").value="Y";
	}else if(element=="0"){
		document.getElementById("questionImage").style.display = "none";
		document.getElementById("ansOneImage").style.display = "none";
		document.getElementById("ansTwoImage").style.display = "none";
		document.getElementById("ansThreeImage").style.display = "none";
		document.getElementById("ansFourImage").style.display = "none";
		document.getElementById("ansFiveImage").style.display = "none";
		document.getElementById("ansExplanation").style.display = "none";
		
		document.getElementById("questionImage").value = "";
		document.getElementById("ansOneImage").value = "";
		document.getElementById("ansTwoImage").value = "";
		document.getElementById("ansThreeImage").value = "";
		document.getElementById("ansFourImage").value = "";
		document.getElementById("ansFiveImage").value = "";
		document.getElementById("ansExplanation").value = "";
		
		//document.getElementById("isgraphicFlag").value="1";
		//document.getElementById("isPictureQ").value="N";
	}
}
/**
 * Sets the subjectDrop down hidden variable -  
 */
function wrapperFunDropDown(dropDownName,nameValuePair,listType,divID,onChangeEvent,hiddenElementID,hiddenElementValue){
	//alert('IN wrapperFunDropDown hiddenElementID: '+hiddenElementID+" hiddenElementValue: "+hiddenElementValue);

	document.getElementById(hiddenElementID).value = hiddenElementValue;
	populateDropdown(dropDownName,nameValuePair,listType,divID,onChangeEvent);	 
}

function displayTestQuestion(questionID,questionNum){
	//alert("In displayTestQuestion() questionID:"+questionID+" questionNum:"+questionNum);
	formElement = document.getElementById("testMainPageFormId");
	document.getElementById("questionID").value=questionID;
	document.getElementById("questionPaperNum").value=questionNum;
	formElement.submit();
}





















