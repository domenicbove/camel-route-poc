package com.example.camel.activemq;

import org.apache.camel.model.RouteDefinition;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MSRouteBuilderTest {
	
	@Test
	public void basicStringRoute() throws Exception{
		MSRouteBuilder routeBuilder = new MSRouteBuilder("hello", "world");
		routeBuilder.configure();
		RouteDefinition routeDef = routeBuilder.getRouteDefinition();
		
		assertEquals(routeDef.getInputs().get(0).getLabel(), "file:hello?noop=true");
		assertEquals(routeDef.getOutputs().get(0).getLabel(), "world");
	}
	
	@Test
	public void toJms() throws Exception {
		
		MSJmsEndpoint jms = new MSJmsEndpoint("test-queue");
		
		MSRouteBuilder routeBuilder = new MSRouteBuilder(jms, "jms:queue:order");
		routeBuilder.configure();
		RouteDefinition routeDef = routeBuilder.getRouteDefinition();
		
		assertEquals(routeDef.getInputs().get(0).getLabel(), "jms:queue:test-queue");
		assertEquals(routeDef.getOutputs().get(0).getLabel(), "jms:queue:order");
	}

}
