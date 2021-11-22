package com.skydoves.waterdays.ui.model;

interface ErrorHandler {
	void handleError(com.skydoves.waterdays.ui.model.ErrorHandler.ErrorType errorType, String cause);

	enum ErrorType {
		BUFFER_CREATION_ERROR
	}
}