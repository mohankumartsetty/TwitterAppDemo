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

public class UserDAO {
	
	//@To Do : Read from property file
	//Deployment step : copy UserDetails.avro to destination folder from src/main/resources
	public static String userDataFileLocation = "g:\\mohan\\";
	public static String userDataFileName = "UserDetails.avro";
	public static File userDataFile = new File(userDataFileLocation + userDataFileName);
	/**
	 * Create new user
	 */
	public void createUser(User userObj) throws IOException
	{
	 DatumWriter<User> userDatumWriter = new SpecificDatumWriter<User>(User.class);
	 DataFileWriter<User> userFileWriter = new DataFileWriter<User>(userDatumWriter);

	 if(!userDataFile.exists() && !userDataFile.isDirectory()) { 
		 userFileWriter.create(userObj.getSchema(), userDataFile);
		 userFileWriter.append(userObj);
	 }
	 else {
		 userFileWriter.appendTo(userDataFile);
		 userFileWriter.append(userObj);
	 }

	 userFileWriter.close();		

	}

	/**
	 * get all users
	 */
	public List<User> getAllUsers() throws IOException
	{
		List<User> userRecords = new ArrayList<User>();

		//DeSerializing the objects
		DatumReader<User> userDatumReader = new SpecificDatumReader<User>(User.class);
		//Instantiating DataFileReader
		DataFileReader<User> dataFileReader = new DataFileReader<User>(userDataFile, userDatumReader);
		User usesObj=null;
			while(dataFileReader.hasNext()){
				usesObj=dataFileReader.next(usesObj);
				userRecords.add(usesObj);
			}
		
		return userRecords;
	}
	


}
