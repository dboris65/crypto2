package edu.crypto2.services;

import java.util.List;

import edu.crypto2.entities.TestValues;

public interface TestValuesDao {
	List<TestValues> findAllTestValues();
	
	TestValues find(long id);
	
	TestValues getCurrent();
	
	public void update (TestValues testValues);
	
	void save(TestValues testValues);

	void delete(TestValues testValues);
	
	public void reload();

	TestValues findTestValuesBySubBytesValue(String name);
}
