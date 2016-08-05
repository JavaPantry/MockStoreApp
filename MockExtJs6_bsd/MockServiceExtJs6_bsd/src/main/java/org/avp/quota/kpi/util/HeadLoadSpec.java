package org.avp.quota.kpi.util;

public class HeadLoadSpec {
	
	// Default values are set to most conservative load - as little as possible
	private boolean shortResponse		= true;
	private boolean loadAttachments     = false;
	private boolean loadDetails			= false;
	private boolean loadFollowUps		= false;
	private boolean loadStatusHistory	= false;
	private long searchRowId = 0;
	private long logonRowId = 0;
	
	public HeadLoadSpec() {}

	public boolean shortResponse() {
		return shortResponse;
	}

	public void setShortResponse(boolean shortResponse) {
		this.shortResponse = shortResponse;
	}

	/**
	 * @return the loadAttachments
	 */
	public boolean loadAttachments() {
		return loadAttachments;
	}

	/**
	 * @param loadAttachments the loadAttachments to set
	 */
	public void setLoadAttachments(boolean loadAttachments) {
		this.loadAttachments = loadAttachments;
	}

	public boolean loadDetails() {
		return loadDetails;
	}

	public void setLoadDetails(boolean loadDetails) {
		this.loadDetails = loadDetails;
	}

	public boolean loadFollowUps() {
		return loadFollowUps;
	}

	public void setLoadFollowUps(boolean loadFollowUps) {
		this.loadFollowUps = loadFollowUps;
	}

	public boolean loadStatusHistory() {
		return loadStatusHistory;
	}

	public void setLoadStatusHistory(boolean loadStatusHistory) {
		this.loadStatusHistory = loadStatusHistory;
	}

	/**
	 * @return the searchRowId
	 */
	public long getSearchRowId() {
		return searchRowId;
	}

	/**
	 * @param searchRowId the searchRowId to set
	 */
	public void setSearchRowId(long searchRowId) {
		this.searchRowId = searchRowId;
	}

	/**
	 * @return the logonRowId
	 */
	public long getLogonRowId() {
		return logonRowId;
	}

	/**
	 * @param logonRowId the logonRowId to set
	 */
	public void setLogonRowId(long logonRowId) {
		this.logonRowId = logonRowId;
	}

}
