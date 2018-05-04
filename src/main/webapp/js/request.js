function calcu(){
	if($("[class='selected connected-list ui-sortable'] li")!=null)
		var num = $("[class='selected connected-list ui-sortable'] li").length-1;
	else num =0;
	alert(num);
	$("[id='length']").text();
	var avg = 90;
	var sum = num * avg;
	document.getElementById("sumBudget").innerHTML=sum;
}
//use request page
function cleanTextarea() {
	var un = 
		document.getElementById("purpose");
	un.value="";
	var at=document.getElementById("activity");
	at.value="";
};
//for purpose and activity and select

function cleanPurposeMsg(){
	document.getElementById('purposeMsg').innerHTML="";
}
function cleanActivityMsg(){
	document.getElementById('activityMsg').innerHTML="";
}

//use for submit button
function isOk(count){
	var purpose = document.getElementById("purpose");
	var purposeLength=purpose.value.length;
	var purposeSpaceLength=0;
	var purposeValue=new String(purpose.value);
	for(var i=0;i<purposeLength;i++){
		if(purposeValue.charAt(i)==" "){
			purposeSpaceLength++;
		}
	}
	var activity = document.getElementById("activity");
	var activityLength=activity.value.length;
	var activitySpaceLength=0;
	var activityValue=new String(activity.value);
	for(var i=0;i<activityLength;i++){
		if(activityValue.charAt(i)==" "){
			activitySpaceLength++;
		}
	}
	
	var time = document.getElementById("text_date").value;
	var myDate = new Date();  
	var nowdate = myDate.getDate()+0;
	var nowyear = myDate.getFullYear(); 
    var nowmonth = myDate.getMonth()+1;
    var nowdate1=new String();
    if(nowmonth<10)  {  
       nowmonth = "0"+nowmonth;  
    }
    if(nowdate<10){  
        nowdate1="0"+nowdate; 
     }else{
    	 nowdate1=nowdate;
     }
    var nowtime = nowyear+"-"+nowmonth+"-"+nowdate1;
    if(time==""){
    	document.getElementById('dateMsg').innerHTML="Please choose date";
		return false;
	}else if(time<nowtime){
		document.getElementById('dateMsg').innerHTML="Can not choose the day before today";
		return false;
	}
    
	if(purposeLength==0){
		document.getElementById('purposeMsg').innerHTML="Please input";
		return false;
	}else if (purposeLength> count){   
		document.getElementById('purposeMsg').innerHTML="500&nbsp;characters";
         return false;
     }
	if(purposeLength==purposeSpaceLength){
		document.getElementById('purposeMsg').innerHTML="Empty";
		return false;
	}
	
	if(activityLength==0){
		document.getElementById('activityMsg').innerHTML="Please input";
		return false;
	}else if (activityLength> count){   
		document.getElementById('activityMsg').innerHTML="500&nbsp;characters!";
         return false;
     }
	if(activityLength==activitySpaceLength){
		document.getElementById('activityMsg').innerHTML="Empty!";
		return false;
	}
	
	var count=0;
	$("select option:selected").each(function() {
		count++;
	});
	if(count==0){
		document.getElementById('selectMsg').innerHTML="Please add participants";
		return false;
	}	
    return true;
}