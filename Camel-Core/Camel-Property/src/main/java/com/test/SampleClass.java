package com.test;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.PropertyInject;

public class SampleClass implements Processor{

//	@PropertyInject("prop1")
//	public String value;
	
	
	public void process(Exchange exchange) throws Exception {
		String fileName = (String) exchange.getIn().getHeader("CamelFileNameOnly");
	exchange.setProperty("myPropertyNew",fileName);
		
		
		
		
	}

}
