/**
 * Boris Damjanovic, 230/08, FON, Belgrade - crypto2 - 2009
 */
package edu.crypto2.pages;


import java.util.List;

import org.apache.log4j.Logger;
import org.apache.tapestry5.Block;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Session;
/******************************************************/
import edu.crypto2.entities.Source;
import edu.crypto2.services.SourceDao;

import edu.crypto2.transformations.SubBytes;
import edu.crypto2.rest.XmlParser;
import edu.crypto2.components.LinesOut;
import edu.crypto2.data.*;
/***********************************************************************
 * 
 */
public class SourcePG {
	private XmlParser xml_p;
    @Inject
    private Session session;
    @Inject
    private SourceDao sourceDao;
	@Parameter
	private Source source;

	public SourceDao getSourceDao() {
		return sourceDao;
	}

	public void setSourceDao(SourceDao sourceDao) {
		this.sourceDao = sourceDao;
	}


	
	public List<Source> getSourceList(){

		return sourceDao.findAllSources();
	}
	

    
	public SourcePG() throws Exception 
	{
		
		final Logger logger = Logger.getLogger(SourcePG.class);
        logger.debug("SourcePG--------------");
		logger.debug("--------------------------------------------------------------");
		logger.debug("--------------------------------------------------------------");
		logger.debug("SourcePG--------------");
		logger.debug("--------------------------------------------------------------");
		logger.debug("--------------------------------------------------------------");
		logger.debug("--------------------------------------------------------------");
		logger.debug("--------------------------------------------------------------");

		
	}
	
	
    @InjectPage
    private SourcePG sourcePG;

//    Object onActionFromView(Long id){
//
//		return LinesOutDetails;   //TestValueRowDetails; odustao 17.09
//	}


    //public List<Source> getSource()
    //{
    //    return session.createCriteria(Source.class).list();
   // }

	public Source getSource() {
		return source;
	}

	public void setSource(Source source) {
		this.source = source;
	}

	
	
	/***************************************************************/

}
