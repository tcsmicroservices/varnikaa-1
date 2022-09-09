package com.ashish.rabbitmq.model;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id", scope = Employee.class)
public class Employee {

	private String to;
	private String from;
	private String content;
	private String correlationId;

	public String getCorrelationId() {
		return correlationId;
	}

	public void setCorrelationId(String correlationId) {
		this.correlationId = correlationId;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@java.lang.Override
	public java.lang.String toString() {
		return "Employee{" +
				"to='" + to + '\'' +
				", from='" + from + '\'' +
				", content='" + content + '\'' +
				", correlationId='" + correlationId + '\'' +
				'}';
	}
}
