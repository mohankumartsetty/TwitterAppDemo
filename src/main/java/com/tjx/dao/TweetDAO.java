package com.tjx.dao;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.avro.file.DataFileWriter;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.specific.SpecificDatumWriter;
import org.apache.avro.file.DataFileReader;
import org.apache.avro.io.DatumReader;
import org.apache.avro.specific.SpecificDatumReader;

import com.tjx.dto.*;

public class TweetDAO {

	//@To Do : Read from property file
	public static String TweetDataFileLocation = "g:\\mohan\\";

	/**
	 * Create Tweet
	 */
	public void createTweet(Tweet TweetObj) throws IOException
	{
	 DatumWriter<Tweet> TweetDatumWriter = new SpecificDatumWriter<Tweet>(Tweet.class);
	 DataFileWriter<Tweet> TweetFileWriter = new DataFileWriter<Tweet>(TweetDatumWriter);

	String userTweetsDataFileName = TweetObj.getAuthorId() + "_TweetDetails.avro";
	File userTweetsDataFile = new File(TweetDataFileLocation + userTweetsDataFileName);
	
	 if(!userTweetsDataFile.exists() && !userTweetsDataFile.isDirectory()) { 
		 TweetFileWriter.create(TweetObj.getSchema(), userTweetsDataFile);
		 TweetFileWriter.append(TweetObj);
	 }
	 else {
		 TweetFileWriter.appendTo(userTweetsDataFile);
		 TweetFileWriter.append(TweetObj);
	 }

	 TweetFileWriter.close();		

	}

	/**
	 * get all Tweets
	 */
	public List<Tweet> getAllTweets(String authorId) throws IOException
	{
		List<Tweet> TweetRecords = new ArrayList<Tweet>();
		
		String userTweetsDataFileName = authorId + "_TweetDetails.avro";
		File userTweetsDataFile = new File(TweetDataFileLocation + userTweetsDataFileName);
		
		if(userTweetsDataFile.exists() && !userTweetsDataFile.isDirectory()) { 
			//DeSerializing the objects
			DatumReader<Tweet> TweetDatumReader = new SpecificDatumReader<Tweet>(Tweet.class);
			//Instantiating DataFileReader
			DataFileReader<Tweet> dataFileReader = new DataFileReader<Tweet>(userTweetsDataFile, TweetDatumReader);
			Tweet usesObj=null;
				while(dataFileReader.hasNext()){
					usesObj=dataFileReader.next(usesObj);
					TweetRecords.add(usesObj);
				}
		}
		return TweetRecords;
	}
	
}
