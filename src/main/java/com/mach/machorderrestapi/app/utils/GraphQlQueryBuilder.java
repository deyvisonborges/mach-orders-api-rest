//package com.mach.machorderrestapi.app.utils;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class GraphQlQueryBuilder {
//	private String operationName;
//	private String returnType;
//	private String paramKey;
//	private String paramValue;
//	private List<String> params = new ArrayList<>();
//
//	public GraphQlQueryBuilder param(String key, String value) {
//		this.paramKey = key;
//		this.paramValue = value;
//		return this;
//	}
//
//	public GraphQlQueryBuilder params(String params) {
//		this.params.add(params);
//		return this;
//	}
//
//	public GraphQlQueryBuilder returnType(Class<?> c) {
//		this.returnType = c.getSimpleName();
//		return this;
//	}
//
//	public String build() {
//		StringBuilder queryBuilder = new StringBuilder("query {\n");
//		queryBuilder.append("  ")
//			.append(operationName).append("(input: { ")
//			.append(paramValue).append(": \"")
//			.append(paramKey).append("\" }) {\n");
//
//		queryBuilder.append("    ");
//		for (var field : returnType.getDeclaredFields()) {
//			queryBuilder.append(field.getName()).append("\n    ");
//		}
//
//		queryBuilder.append("  }\n");
//		queryBuilder.append("}");
//
//		return queryBuilder.toString();
//	}
//}
