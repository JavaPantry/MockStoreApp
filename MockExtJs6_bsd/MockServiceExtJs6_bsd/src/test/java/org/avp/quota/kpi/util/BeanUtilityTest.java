package org.avp.quota.kpi.util;

import static org.junit.Assert.*;

import org.avp.quota.kpi.util.BeanUtility;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BeanUtilityTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
	// at Test
	public void testNullSafeMergeTo() {
		A src = new A(1L,11,22,"A original","A original2");
		A dst = new A(2L,33,44,"B original","B original2");
		BeanUtility.nullSafeMergeTo(src, dst, new String[]{"+doNotCopyProperty"});
		assertNotEquals(src.getId(), dst.getId());
		assertNotEquals(src.getDoNotCopyProperty(), dst.getDoNotCopyProperty());
		assertEquals(src.getDoNotCopyProperty2(), dst.getDoNotCopyProperty2());
		assertEquals(src.getProperty1(), dst.getProperty1());
		assertEquals(src.getProperty2(), dst.getProperty2());

		dst = new A(2L,33,44,"B original","B original2");
		BeanUtility.nullSafeMergeTo(src, dst, new String[]{"+doNotCopyProperty","doNotCopyProperty2"});
		assertNotEquals(src.getId(), dst.getId());
		assertNotEquals(src.getDoNotCopyProperty(), dst.getDoNotCopyProperty());
		assertNotEquals(src.getDoNotCopyProperty2(), dst.getDoNotCopyProperty2());
		assertEquals(src.getProperty1(), dst.getProperty1());
		assertEquals(src.getProperty2(), dst.getProperty2());

	}

}
