package org.avp.quota.kpi.model.dto;

public class BaseImportDto {
	protected boolean hasErrors;
	protected String messages;

	public boolean isHasErrors() {
		return hasErrors;
	}
	public void setHasErrors(boolean hasErrors) {
		this.hasErrors = hasErrors;
	}
	public String getMessages() {
		return messages;
	}
	public void setMessages(String messages) {
		this.messages = messages;
	}

}
