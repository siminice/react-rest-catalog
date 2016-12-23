/*
 * Copyright 2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.greglturnquist.payroll;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 * @author Greg Turnquist
 */
// tag::code[]
@Component
public class DatabaseLoader implements CommandLineRunner {

	private final EmployeeRepository repository;
	private final String dash = "-";

	@Autowired
	public DatabaseLoader(EmployeeRepository repository) {
		this.repository = repository;
	}

	@Override
	public void run(String... strings) throws Exception {
		this.repository.deleteAll();
		loadFile("players.dat");
	}

	private void loadFile(String filename) {
		try {
			final FileInputStream fstream = new FileInputStream(filename);
			final InputStreamReader dis = new InputStreamReader(fstream, "ISO-8859-2");
			final BufferedReader br = new BufferedReader(dis);
			String s = br.readLine();
			while ((s = br.readLine()) != null) {
				String[] tk = s.split(",");
				if (tk.length > 6) {
					// abayed,Abayateye,Edward,1975-10-06,GHA,Accra,GHA
					System.out.println("Loaded: " + String.join(", ", tk[0], tk[1], tk[2], tk[3], tk[4], tk[5], tk[6]));
					Employee p = new Employee(tk[0], tk[1], esc(tk[2]), esc(tk[3]),
							esc(tk[4]), esc(tk[5]), esc(tk[6]));
					this.repository.save(p);
				}
				else {
					System.out.println("Invalid entry: " + s);
				}
			}
			br.close();
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	/*
	  prevent undesired effects of trimming
	 */
	private String esc(String t) {
		if (t==null || t.length()==0 || t.equals(" ")) {
			return "-";
		}
		return t;
	}

}
// end::code[]