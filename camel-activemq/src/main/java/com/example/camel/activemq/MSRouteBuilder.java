package com.example.camel.activemq;

import org.apache.camel.model.RouteDefinition;

public class MSRouteBuilder extends org.apache.camel.builder.RouteBuilder {

	private String fromDirectory;
	private String toQueue;

	private RouteDefinition routeDefinition;

	public MSRouteBuilder(String fromDirectory, String toQueue) {
		this.fromDirectory = fromDirectory;
		this.toQueue = toQueue;
	}

	@Override
	public void configure() throws Exception {
		routeDefinition = from("file:" + fromDirectory + "?noop=true")
		.to(toQueue);
	}

	public RouteDefinition getRouteDefinition() {
		return this.routeDefinition;
	}

	public String getFromDirectory() {
		return fromDirectory;
	}

	public void setFromDirectory(String fromDirectory) {
		this.fromDirectory = fromDirectory;
	}

	public String getToQueue() {
		return toQueue;
	}

	public void setToQueue(String toQueue) {
		this.toQueue = toQueue;
	}
}
