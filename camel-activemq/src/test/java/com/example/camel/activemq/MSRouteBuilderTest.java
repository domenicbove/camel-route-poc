package com.example.camel.activemq;

import org.apache.camel.model.RouteDefinition;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MSRouteBuilderTest {
	
	@Test
	public void basicFileRoute() throws Exception{
		MSRouteBuilder routeBuilder = new MSRouteBuilder("file:src/data/a", "activemq:personal.records", true);
		routeBuilder.configure();
		RouteDefinition routeDef = routeBuilder.getRouteDefinition();
		
		assertEquals(routeDef.getInputs().get(0).getLabel(), "file:src/data/a?noop=true");
		assertEquals(routeDef.getOutputs().get(0).getLabel(), "activemq:personal.records");
	}
	
	@Test
	public void basicFileRouteNoop() throws Exception{
		MSRouteBuilder routeBuilder = new MSRouteBuilder("file:src/data/a", "activemq:personal.records");
		routeBuilder.configure();
		RouteDefinition routeDef = routeBuilder.getRouteDefinition();
		
		assertEquals(routeDef.getInputs().get(0).getLabel(), "file:src/data/a");
		assertEquals(routeDef.getOutputs().get(0).getLabel(), "activemq:personal.records");
	}

}
