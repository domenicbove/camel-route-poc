package com.example.camel.activemq;

import org.apache.camel.model.RouteDefinition;

public class MSRouteBuilder extends org.apache.camel.builder.RouteBuilder {

	private String fromUri;
	private String toUri;

	private RouteDefinition routeDefinition;

	public MSRouteBuilder(String fromUri, String toUri) {
		this(fromUri, toUri, false);
	}
	
	public MSRouteBuilder(String fromUri, String toUri, Boolean noop) {
		if(noop) {
			this.fromUri = fromUri + "?noop=true";
		} else {
			this.fromUri = fromUri;
		}
		this.toUri = toUri;
	}

	@Override
	public void configure() throws Exception {
		routeDefinition = from(fromUri).to(toUri);
	}

	public RouteDefinition getRouteDefinition() {
		return this.routeDefinition;
	}

	public String getFromDirectory() {
		return fromUri;
	}

	public void setFromDirectory(String fromDirectory) {
		this.fromUri = fromDirectory;
	}

	public String getToQueue() {
		return toUri;
	}

	public void setToQueue(String toQueue) {
		this.toUri = toQueue;
	}
}
