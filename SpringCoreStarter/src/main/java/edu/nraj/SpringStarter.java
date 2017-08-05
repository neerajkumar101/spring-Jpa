package edu.nraj;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import edu.nraj.beans.BeanA;

public class SpringStarter {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
		
		BeanA beanA = context.getBean("beanA", BeanA.class);
		
		beanA.getName();
	}

}
