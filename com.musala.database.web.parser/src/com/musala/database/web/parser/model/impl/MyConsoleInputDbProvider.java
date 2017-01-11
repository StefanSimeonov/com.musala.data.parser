package com.musala.database.web.parser.model.impl;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.musala.database.web.parser.model.impl.QueryType;
import com.musala.database.web.parser.model.IDbInputProvider;

/**
 * This class takes responsibility to provide the user's input
 */
public class MyConsoleInputDbProvider implements IDbInputProvider {
	private Scanner in = new Scanner(System.in);

	@Override
	public void visualizeMainMenu() {
		System.out.println("Hello to database-java parser ");

	}

	@Override
	public String getConnection() {
		System.out.print("Please enter server name:");
		return in.nextLine();
	}
	@Override
	public QueryType getQueryType() {
		System.out.println(
				" Your can choose from 3 type of queries: \n 1. Get all records from a given table \n 2. Get records from a table by given Id. \n 3.Get All records from a given table by name. \n 4. Close");
		System.out.print("Enter your choice:");
		int choice ;
		try{
	choice = in.nextInt();
		}catch(InputMismatchException e){
			in.nextLine();
			throw new InputMismatchException("Incorrect query");
		}
		 in.nextLine();

		switch (choice) {
		case 1:
			return QueryType.first;
		case 2:
			return QueryType.second;
		case 3:
			return QueryType.third;
		case 4:
			return QueryType.fourth;
		}
		return null;
	}

	@Override
	public String getQueryTableName() {
		System.out.print("Enter table name:");
		return in.nextLine();
	}

	@Override
	public String getDatabase() {
		System.out.print(" Please enter database name:");
		return in.nextLine();
	}

	@Override
	public String getUserName() {
		System.out.print("Please enter username:");
		return in.nextLine();

	}

	@Override
	public String getPassword() {
		System.out.print("Please enter password:");
		return in.nextLine();
	}

	@Override
	public String[] getRecordPropertiesName() {
		System.out.print("Please enter the record's propeties you want, splited by space:");
		return in.nextLine().split(" ");
	}

	@Override
	public String getNeedableId() {
		System.out.print("Enter the id you want:");
		String Id = in.nextLine();
		return Id;
	}

	@Override
	public String getNeedableName() {
		System.out.print("Enter the name you want");
		return in.nextLine();
	}

	@Override
	public void getGreeting() {
		System.out.println("Thank you. Have a nice day.");

	}

	@Override
	public void close() {
		in.close();
	}

}
