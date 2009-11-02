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


/**
 * class Source<br>	
 * Holds informations about AES source (or modified AES, i.e meta transformation) code, and
 * users that made it.
 */
@Entity
@Table(name="source")
public class Source {
	@Inject
	private Session session;

    /**
     * List of Sources getter.
     * @return List<Source>
     */
    public List<Source> getSource()
    {
        return session.createCriteria(Source.class).list();
    }
    
    /**
     * Class constructor.
     */
    public Source(){
    	super();
    }
	
    /**
     * Class constructor.
     * @param sourceCode
     */
    public Source(String sourceCode){
    	this();
    	this.SourceCode = sourceCode;
    }
    
    
    
	/**
	 * long id<br>
	 * Row identifier.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NonVisual
	private long id;
 
	/**
	 * String SourceCode<br>
	 * Holds part of AES (or modified AES, i.e meta transformation) source code.
	 */
	@Validate("required")
	private String SourceCode;
	
	/**
	 * long UserID<br>
	 * Used to show rows that belong to logged User.
	 */
	@NonVisual
	private long UserId; 

	

	

	/**
	 * Id getter.
	 * @return long Id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Id setter.
	 * @param id
	 */
	public void setId(long id) {
		this.id = id;
	}
	
	/**
	 * SourceCode getter.
	 * @return SourceCode
	 */
	@Validate("required")
	public String getSourceCode() {
		return SourceCode;
	}
	/**
	 * sourceCode setter.
	 * @param sourceCode
	 */
	public void setSourceCode(String sourceCode) {
		SourceCode = sourceCode;
	}
	/**
	 * @return the userId
	 */
	public Long getUserId() {
		return UserId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(long userId) {
		UserId = userId;
	}

}
