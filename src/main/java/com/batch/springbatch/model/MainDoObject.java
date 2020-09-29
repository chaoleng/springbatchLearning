package com.batch.springbatch.model;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class MainDoObject {
	private List<Person> persons;

	public List<Person> getPersons() {
		return persons;
	}

	public void setPersons(List<Person> persons) {
		this.persons = persons;
	}
	

}
