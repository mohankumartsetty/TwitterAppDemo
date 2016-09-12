package com.tjx.resources;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.tjx.dao.TweetDAO;
import com.tjx.dto.Tweet;

import java.util.Date;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@Path("/tweets")
public class TweetResource {

	@Path("/{authorId}")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public List getUserTweets(@PathParam("authorId") String authorId)
	{
		TweetDAO tweetDAO = new TweetDAO();
		try {
			System.out.println(tweetDAO.getAllTweets(authorId));
			return tweetDAO.getAllTweets(authorId);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new ArrayList<Tweet>();
		
	}
	
	@Path("/submit_tweet")
	@POST
	public Response submitTweet( 
			@FormParam("authorId") String authorId,
			@FormParam("tweetMessage") String tweetMessage)
	{
		//get current date time with Date()
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		String created =  dateFormat.format(cal.getTime());		
		   
	    long tweetId = System.currentTimeMillis();
	    
	    Tweet tweetObj = new Tweet();
	    tweetObj.setAuthorId(authorId);
	    tweetObj.setCreated(created);
	    tweetObj.setTweetMessage(tweetMessage);
	    tweetObj.setTweetId(String.valueOf(tweetId));
	    
	    TweetDAO tweetDAO = new TweetDAO();
	    try {
			tweetDAO.createTweet(tweetObj);
			return Response.status(200)
					.entity("Tweet submitted sucessfully ").build();

	    } catch (IOException e) {
			e.printStackTrace();
		}
	    
		return Response.status(400)
				.entity("Unable to submit tweet").build();
	}
}
