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

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;

/**
 * @author Radu Siminiceanu
 */
// tag::code[]
@Data
@Entity
public class Employee {

	//abayed,Abayateye,Edward,1975-10-06,GHA,Accra,GHA
	private @Id @GeneratedValue Long id;
	private String mnem;
	private String lastName;
	private String firstName;
	private String dob;
	private String nationality;
	private String pob;
	private String cob;

	private @Version @JsonIgnore Long version;

	private Employee() {}

	public Employee(String mnem, String lastName, String firstName,
									String dob, String nat, String pob, String cob) {
		this.mnem = mnem;
		this.lastName = lastName;
		this.firstName = firstName;
		this.dob = dob;
		this.nationality = nat;
		this.pob = pob;
		this.cob = cob;
	}
}
// end::code[]