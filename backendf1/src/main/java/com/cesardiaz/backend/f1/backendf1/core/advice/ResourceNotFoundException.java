package com.cesardiaz.backend.f1.backendf1.core.advice;

public class ResourceNotFoundException  extends RuntimeException{

	private static final long serialVersionUID = 4482764241994136778L;
	
	public ResourceNotFoundException(Object resourceId) {
		super(resourceId.toString());
	}

}
