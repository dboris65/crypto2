/**
 * Boris Damjanovic, 230/08, FON, Belgrade - crypto2 - 2009
 */
package edu.crypto2.entities;

import java.util.List;

import org.apache.tapestry5.beaneditor.NonVisual;
import org.apache.tapestry5.beaneditor.Validate;
import org.apache.tapestry5.ioc.annotations.Inject;

import org.hibernate.Session;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/***********************************************************************
 * 
 */
@Entity
@Table(name="source")
public class Source {
	@Inject
	private Session session;

    public List<Source> getSource()
    {
        return session.createCriteria(Source.class).list();
    }
    
    public Source(){
    	super();
    }
	
    public Source(String sourceCode){
    	this();
    	this.SourceCode = sourceCode;
    }
    
    
    
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NonVisual
	private Long id;

	@Validate("required")
	private String SourceCode;
	

	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@Validate("required")
	public String getSourceCode() {
		return SourceCode;
	}
	public void setSourceCode(String sourceCode) {
		SourceCode = sourceCode;
	}
    

}
