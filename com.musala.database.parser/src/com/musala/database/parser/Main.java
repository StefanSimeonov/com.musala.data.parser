package com.musala.database.parser;

import com.musala.database.parser.model.IDbEngine;
import com.musala.database.parser.model.impl.MySqlDbEngine;

public class Main {

	/** The initialization method for the program
	 * @param args -unused arguments
	 */
	public static void main(String[] args) {

		IDbEngine engine = new MySqlDbEngine();
		engine.initialize();
		engine.startQuering();
	}

}
