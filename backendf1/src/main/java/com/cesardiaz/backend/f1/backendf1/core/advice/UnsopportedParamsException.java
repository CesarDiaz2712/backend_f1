package com.cesardiaz.backend.f1.backendf1.core.advice;

public class UnsopportedParamsException extends RuntimeException{

	private static final long serialVersionUID = 4482764241994136778L;
	
	public UnsopportedParamsException(Object resourceId) {
		super(resourceId.toString());
	}
}
