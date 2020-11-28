package com.cgfy.oauth.base.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * 该类是controller的执行上下文对象
 */
public class CustomRequestContext implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2431269078366541809L;

	private static ThreadLocal<CustomRequestContext> controllerContext = new ThreadLocal<CustomRequestContext>();

	private Map<Object, Object> context;

	private static final String REQUEST = "javax.servlet.http.HttpServletRequest";

	private static final String RESPONSE = "javax.servlet.http.HttpServletResponse";

	public CustomRequestContext(Map<Object, Object> context) {
		this.context = context;
	}

	public static void setContext(CustomRequestContext context) {
		controllerContext.set(context);
	}

	public static CustomRequestContext getContext() {
		CustomRequestContext context = (CustomRequestContext) controllerContext.get();
		if (null == context) {
			context = new CustomRequestContext(new HashMap<Object, Object>());
			setContext(context);
		}
		return context;
	}

	public Map<Object, Object> getContextMap() {
		return this.context;
	}

	public void setContextMap(Map<Object, Object> contextMap) {
		getContext().context = contextMap;
	}

	public Object get(Object key) {
		return context.get(key);
	}

	public void put(Object key, Object value) {
		context.put(key, value);
	}

	public void setRequest(HttpServletRequest request) {
		put(REQUEST, request);
	}

	public HttpServletRequest getRequest() {
		return (HttpServletRequest) get(REQUEST);
	}

	public HttpServletResponse getResponse() {
		return (HttpServletResponse) get(RESPONSE);
	}

	public void setResponse(HttpServletResponse response) {
		put(RESPONSE, response);
	}

	
}
