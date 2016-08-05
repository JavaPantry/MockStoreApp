package org.avp.quota.kpi.configuration.common;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;

import com.google.common.collect.ImmutableList;

/**
 * 
 * Configuration repository
 *
 */
@SuppressWarnings("serial")
public final class ApplicationConfiguration implements Serializable {

	private Collection<String> authExcludePatterns;
	
	private String attachmentPath;
	private Collection<String> attachmentContentTypes;
	private Collection<String> attachmentExtensions;
	private long attachmentMaxSize;
	
	private String  emailFromAddress;
	private String  emailSubject;
	private String  emailClientServiceLocation;
	
	private String  ldapHost;

	private String logoutUrl; // to properly destroy SSO tokens
	
	private boolean overrideExpiryDate;

	private boolean reloadCodeTables;
	
	private boolean serviceStatusEmail;
	
	private BigDecimal standardDealerGp;
	
	private String testEmail;
	
	
	public ApplicationConfiguration() {}

	/**
	 * @return the aUTH_EXCLUDE_PATTERNS
	 */
	public Collection<String> getAuthExcludePatterns() {
		return ImmutableList.copyOf(authExcludePatterns);
	}

	/**
	 * @param patterns the aUTH_EXCLUDE_PATTERNS to set
	 */
	public void setAuthExcludePatterns(Collection<String> patterns) {
		authExcludePatterns = patterns;
	}

	/**
	 * @return the aTTACHMENTS_PATH
	 */
	public String getAttachmentPath() {
		return attachmentPath;
	}

	/**
	 * @param path the aTTACHMENTS_PATH to set
	 */
	public void setAttachmentPath(String path) {
		attachmentPath = path;
	}

	/**
	 * @return the aTTACHMENTS_CONTENT_TYPES
	 */
	public Collection<String> getAttachmentContentTypes() {
		return ImmutableList.copyOf(attachmentContentTypes);
	}

	/**
	 * @param types the aTTACHMENTS_CONTENT_TYPES to set
	 */
	public void setAttachmentContentTypes(Collection<String> types) {
		attachmentContentTypes = types;
	}

	/**
	 * @return the aTTACHMENTS_EXTENSIONS
	 */
	public Collection<String> getAttachmentExtensions() {
		return ImmutableList.copyOf(attachmentExtensions);
	}

	/**
	 * @param extensions the aTTACHMENTS_EXTENSIONS to set
	 */
	public void setAttachmentExtensions(Collection<String> extensions) {
		attachmentExtensions = extensions;
	}

	/**
	 * @return the aTTACHMENTS_MAXSIZ
	 */
	public long getAttachmentMaxSize() {
		return attachmentMaxSize;
	}

	/**
	 * @param maxSize the aTTACHMENTS_MAXSIZ to set
	 */
	public void setAttachmentMaxSize(long maxSize) {
		attachmentMaxSize = maxSize;
	}

	/**
	 * @return the eMAIL_FROM_ADDRESS
	 */
	public String getEmailFromAddress() {
		return emailFromAddress;
	}

	/**
	 * @param address the eMAIL_FROM_ADDRESS to set
	 */
	public void setEmailFromAddress(String address) {
		emailFromAddress = address;
	}

	/**
	 * @return the eMAIL_SUBJECT
	 */
	public String getEmailSubject() {
		return emailSubject;
	}

	/**
	 * @param subject the eMAIL_SUBJECT to set
	 */
	public void setEmailSubject(String subject) {
		emailSubject = subject;
	}

	/**
	 * @return the eMAIL_CLIENT_SERVICE_LOCATION
	 */
	public String getEmailClientServiceLocation() {
		return emailClientServiceLocation;
	}

	/**
	 * @param location the eMAIL_CLIENT_SERVICE_LOCATION to set
	 */
	public void setEmailClientServiceLocation(String location) {
		emailClientServiceLocation = location;
	}

	/**
	 * @return the lDAP_HOST
	 */
	public String getLdapHost() {
		return ldapHost;
	}

	/**
	 * @param host the lDAP_HOST to set
	 */
	public void setLdapHost(String host) {
		ldapHost = host;
	}

	/**
	 * @return the logoutUrl
	 */
	public String getLogoutUrl() {
		return logoutUrl;
	}

	/**
	 * @param logoutUrl the logoutUrl to set
	 */
	public void setLogoutUrl(String logoutUrl) {
		this.logoutUrl = logoutUrl;
	}

	/**
	 * @return the overrideExpiryDate
	 */
	public boolean isOverrideExpiryDate() {
		return overrideExpiryDate;
	}

	/**
	 * @param overrideExpiryDate the overrideExpiryDate to set
	 */
	public void setOverrideExpiryDate(boolean overrideExpiryDate) {
		this.overrideExpiryDate = overrideExpiryDate;
	}

	/**
	 * @return the reloadCodeTables
	 */
	public boolean isReloadCodeTables() {
		return reloadCodeTables;
	}

	/**
	 * @param reloadCodeTables the reloadCodeTables to set
	 */
	public void setReloadCodeTables(boolean reloadCodeTables) {
		this.reloadCodeTables = reloadCodeTables;
	}

	/**
	 * @return the sERVICE_STATUS_EMAIL
	 */
	public boolean isServiceStatusEmail() {
		return serviceStatusEmail;
	}

	/**
	 * @param email the sERVICE_STATUS_EMAIL to set
	 */
	public void setServiceStatusEmail(boolean email) {
		serviceStatusEmail = email;
	}

	/**
	 * @return the standardDealerGp
	 */
	public BigDecimal getStandardDealerGp() {
		return standardDealerGp;
	}

	/**
	 * @param standardDealerGp the standardDealerGp to set
	 */
	public void setStandardDealerGp(BigDecimal standardDealerGp) {
		this.standardDealerGp = standardDealerGp;
	}

	public String getTestEmail() {
		return testEmail;
	}

	public void setTestEmail(String testEmail) {
		this.testEmail = testEmail;
	}

}
