
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>User Home Page Page</title>
<link rel="stylesheet" type="text/css" href="../css/CustomStyles2.css">
<script src="../javaScript/twitter.js"></script>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page language="java" import="java.util.List,com.tjx.dao.*,com.tjx.dto.*"%>
</head>
<body>
<% 
String username = (String)session.getAttribute("username");

if(username!=null && !username.trim().equals("") )
{
%>
<p class="message"><b><b>User Page of :</b> <%=username %></b></p>



<%   
TweetDAO tweetDAO = new TweetDAO();
List<Tweet> userTweets = tweetDAO.getAllTweets(username);

%>
  <table border="1">
	     <tr>
	         <td>User Name</td>
	     	  <td>Created On</td>
	     	  <td>Tweet Message</td>
	     	  <td>Tweet Id</td>
	     </tr>
	     
     <% for(Tweet currentTweet : userTweets)
		{
	 %>
          <tr>
              <td><%=currentTweet.getAuthorId() %></td>
          	  <td><%=currentTweet.getCreated() %></td>
          	  <td><%=currentTweet.getTweetMessage()  %></td>
          	  <td><%=currentTweet.getTweetId() %></td>
          </tr>
	<%	}
	 %>
  </table>
    

<div class="user-page">
<b>Submit new tweet :</b> 

<div class="form">

    <form  name="tweetsform" method="POST" action="">
      <input type="text" name="tweetMessage" placeholder="new tweet"/>
	  <input type="hidden" name="authorId" value="<%=session.getAttribute("username") %>"/>	
      <button type="submit" onClick="validateTweetForm('<%=request.getContextPath() %>')">Submit-Tweet </button>
    </form>
  </div>
  
</div>
<%
}
else
{
	response.sendRedirect(request.getContextPath() + "/index.jsp");
}
%>

</body>
</html>