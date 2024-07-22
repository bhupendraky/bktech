package com.bktech.infra.ctx;

public class AuditContext {

	private String auditor;

	public AuditContext() {
		super();
	}

	public AuditContext(String auditor) {
		super();
		this.auditor = auditor;
	}

	public String getAuditor() {
		return auditor;
	}

	public void setAuditor(String auditor) {
		this.auditor = auditor;
	}

	@Override
	public String toString() {
		return "ContextData [auditor=" + auditor + "]";
	}
}
