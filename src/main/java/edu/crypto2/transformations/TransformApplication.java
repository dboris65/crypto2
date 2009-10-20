/**
 * Boris Damjanovic, 230/08, FON, Belgrade - crypto2 - 2009
 */
package edu.crypto2.transformations;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import edu.crypto2.transformations.TransformationService;

/***********************************************************************
 * 
 */
public class TransformApplication {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		TransformationService transformation = (TransformationService)context.getBean("TransformationService");

		transformation.GetTransformationName();
	}

}
