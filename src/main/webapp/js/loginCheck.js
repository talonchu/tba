function loginCheck(){
	var username = document.getElementById("username").value;
	var password = document.getElementById("password").value;

	if(username == "" || username == null){
		document.getElementById("errorMsg").innerHTML="Please input username";
		return false;
	}else if(password == "" ||password == null){
		document.getElementById("errorMsg").innerHTML="Please input password";
		return false;
	}else{
		return true;
	}
	
}