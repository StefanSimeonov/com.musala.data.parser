package com.musala.database.web.spa.parser.model.impl;

/**
 * Currently there are 4 types of query
 *
 */
public enum QueryType {
	first(1), second(2), third(3), fourth(4);
	private Integer value;

	private QueryType(Integer value) {
		this.value = value;
	}

	public Integer getValue() {
		return this.value;
	}
};
