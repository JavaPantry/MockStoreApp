package org.avp.quota.kpi.util;


public class A{
	Long id;
	int property1;
	int property2;
	String doNotCopyProperty;
	String doNotCopyProperty2;

	public A(Long id, int property1, int property2, String doNotCopyProperty, String doNotCopyProperty2) {
		super();
		this.id = id;
		this.property1 = property1;
		this.property2 = property2;
		this.doNotCopyProperty = doNotCopyProperty;
		this.doNotCopyProperty2 = doNotCopyProperty2;
	}

	public Long getId() {return id;}
	public void setId(Long id) {this.id = id;}
	public int getProperty1() {return property1;}
	public void setProperty1(int property1) {this.property1 = property1;}
	public int getProperty2() {return property2;}
	public void setProperty2(int property2) {this.property2 = property2;}
	//java.lang.NoSuchMethodException: Property 'doNotCopyProperty' has no getter method
	public String getDoNotCopyProperty() {return doNotCopyProperty;}
	public void setDoNotCopyProperty(String doNotCopyProperty) {this.doNotCopyProperty = doNotCopyProperty;}
	public String getDoNotCopyProperty2() {return doNotCopyProperty2;}
	public void setDoNotCopyProperty2(String doNotCopyProperty2) {this.doNotCopyProperty2 = doNotCopyProperty2;}
}
