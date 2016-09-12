/**
 * 
 */

function validateLoginForm(contextPath)
{

	if(document.loginform.username.value == ""
	       || document.loginform.password.value == "" )
    {
		alert("Please enter username and password!!");
		return false; 
    }
	else if( /[^a-zA-Z0-9\-\/]/.test( document.loginform.username.value ) ) {
		alert('No special characters allowed. Enter only alphanumeric values');
		return false;
	}

	document.loginform.action=contextPath+"/webapi/users/login";
	document.loginform.method="POST";
	document.loginform.submit();
}

function validateCreateUserForm(contextPath)
{
	if(document.createUserForm.username.value == ""
	       || document.createUserForm.password.value == "" )
	 {
			alert("Please enter username and password!!");
			return false; 
	 }
	else if( /[^a-zA-Z0-9\-\/]/.test( document.loginform.username.value ) ) {
		alert('No special characters allowed. Enter only alphanumeric values');
		return false;
	}	

	document.createUserForm.action=contextPath+"/webapi/users/create";
	document.createUserForm.method="POST";
	document.createUserForm.submit();

}


function validateTweetForm(contextPath)
{
	if(document.tweetsform.tweetMessage.value == "")
	 {
			alert("Please enter tweet!!");
			return false; 
	 }

	document.tweetsform.action=contextPath+"/webapi/tweets/submit_tweet";
	document.tweetsform.method="POST";
	document.tweetsform.submit();

}