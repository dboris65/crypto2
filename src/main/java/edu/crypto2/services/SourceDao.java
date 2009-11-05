package edu.crypto2.services;

import java.util.List;

import edu.crypto2.entities.Source;


public interface SourceDao {
	List<Source> findAllSources();
	 
	Source find(long id);
	
	Source getCurrent();

	public void update(Source source);
	
	void save(Source _source);

	void delete(Source _source);
	
	public void reload(long userId, int kind);
}
