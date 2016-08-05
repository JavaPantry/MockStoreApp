package org.avp.quota.kpi.util;



import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.google.common.base.Strings;

public class GeneralUtil {
/**
 * utility check to unify filtering page content 
 * example: GeneralUtil.isRecordInPageLimit(page, limit, recordIdx)
 * 	
 * @param page - 1-based page index
 * @param limit - number records on page
 * @param recordIdx - record index in row set
 * @return true if recordIdx in page range
 * 
 */
	public static boolean isRecordInPageLimit(int page, int limit, int recordIdx) {
		return (recordIdx >= (page - 1) * limit) && (recordIdx < page * limit);
	}

	public static int find(Object[] array, Object obj){
		if (array == null || obj == null ) return -1;
		for (int i = 0; i < array.length; i++) {
			if(array[i].equals(obj))
				return i;
		}
		return -1;
	}
	public static int find(List list, Object obj) {
		if (list == null || obj == null ) return -1;
		for (int i = 0; i < list.size(); i++) {
			if(list.get(i).equals(obj))
				return i;
		}
		return -1;
	}
	
	
	/**
	 * use 
	 * com.google.common.base.Strings
	 * !Strings.isNullOrEmpty(jsonString)
	 * 
	 * null safe check empty string
	 * GeneralUtil.isEmpty(str)
	 * @param (str == null || str.trim().length() == 0)
	 * @return
	 */
	@Deprecated
	public static boolean isEmpty(String str){
		return (str == null || str.trim().length() == 0);
	}
	/**
	 * GeneralUtil.isEmpty(
	 * @param list
	 * @return
	 */
	public static boolean isEmpty(@SuppressWarnings("rawtypes") List list){return (list == null || list.size() == 0);}
	
	public static boolean isEmpty(Object[] array){ return (array == null || array.length == 0);}

	public static boolean isEmpty(Long id) {return (id == null || id == 0L);}
	public static boolean isEmpty(Integer id) {return (id == null || id == 0L);}
	public static boolean isEmpty(BigDecimal msrp) {return (msrp == null);}
	/**
	 * comparison null with Long(0L) will return true 
	public static boolean isNullSafeEqual(Long l1,Long l2){
		if(l1==null && l2==null) return true;
		if(l1==null && l2 != null && l2.longValue()==0L) return true;
		if(l2==null && l1 != null && l1.longValue()==0L) return true;
		if(l2.longValue()==l1.longValue()) return true;
		return false;
	}
	 */
	public static boolean isNullSafeEqual(Long l1,Long l2){
		if(l1==null) l1 =  Long.valueOf(0L);
		if(l2==null) l2 =  Long.valueOf(0L);
		return l1.equals(l2);
	}

	public static boolean isNullSafeEqual(Integer l1,Integer l2){
		if(l1==null) l1 =  Integer.valueOf(0);
		if(l2==null) l2 =  Integer.valueOf(0);
		return l1.equals(l2);
	}

	public static boolean isNullSafeEqual(String s1,String s2){
		if(s1==null) s1 = "";
		if(s2==null) s2 = "";
		return s1.equals(s2);
	}
	
	public static String renderJsonArrayOfStrings(Object[] arrayOfStrings){
		if(isEmpty(arrayOfStrings))
			return null;
		StringBuffer b = new StringBuffer();
		b.append("[");
		for (int i = 0; i < arrayOfStrings.length; i++) {
			if(i>0) b.append(",");
			b.append("'").append(arrayOfStrings[i].toString()).append("'");
		}
		b.append("]");
		return b.toString();
	}
	
	public static <E> String printListToString(String prefix,List<E> list, String suffix){
		StringBuilder sb = new StringBuilder();
		if(isEmpty(list))
			return prefix+suffix;
		sb.append("(");
		int count=0;
		for (E id:list) {
			sb.append(id);
			if((++count) < list.size()) sb.append(",");
		}
		sb.append(")");
		return sb.toString();
	}
	
	/**
	 * add element to java array
	 * 
	 * instead:
	 * 		List<FilterParameter> filters = null;
	 * 		if( !GeneralUtil.isEmpty(filterParameters) ){
	 * 			filters = Arrays.asList(filterParameters);
	 * 		}else{
	 * 			filters = new ArrayList<FilterParameter>();
	 * 		}
	 * 		FilterParameter filter = new FilterParameter();
	 * 		filter.setField("dealerGroupId");
	 * 		filter.setValue(dealerGroupId);
	 * 		filters.add(filter);
	 * 		filterParameters = new FilterParameter[filters.size()];// filters.toArray(); 
	 * 		for (int i = 0; i < filterParameters.length; i++) {
	 * 			filterParameters[i] = filters.get(i);
	 * 		}
	 * 
	 * 		@Test
	 * 		public void testGenericsArray(){
	 * 			FilterParameter[] srcArray = null; //srcArray = new FilterParameter[1];
	 * 			FilterParameter filterParameter = new FilterParameter();
	 * 			filterParameter.setField("a");
	 * 			filterParameter.setValue("avalue");
	 * 			srcArray = GeneralUtil.addToArray(FilterParameter.class, srcArray, filterParameter);
	 * 			assertEquals(1, srcArray.length);
	 * 			//srcArray[0] = filterParameter;
	 * 			FilterParameter filterParameter2 = new FilterParameter();
	 * 			filterParameter2.setField("b");
	 * 			filterParameter2.setValue("bvalue");
	 * 			srcArray = GeneralUtil.addToArray(FilterParameter.class, srcArray, filterParameter2);
	 * 			assertEquals(2, srcArray.length);
	 * 		}
	 * 
	 * 
	 *  http://stackoverflow.com/questions/4013683/creating-generic-arrays-in-java 
	 */
	public static <T> T[] addToArray(Class<T> clazz, T[] srcArray, T el) {
		List<T> list = new ArrayList<T>();
		if(srcArray != null){
			for (int i = 0; i < srcArray.length; i++) {
				list.add(srcArray[i]);
			}
		}
		list.add(el);
		srcArray = (T[]) Array.newInstance(clazz, list.size()); 
		for (int i = 0; i < srcArray.length; i++) {
			srcArray[i] = list.get(i);
		}
		return srcArray;
	}
/**
 * generate random long within min,max range
 * @param min
 * @param max
 * @return
 */
	public static long randomLongInRange(long min, long max) {
		return (min + (long)(Math.random() * ((max - min) + 1)));
	}

	/**
	 * return 1-based week of the month i.e. 5 for September 29,2014
	 * @param calendar
	 * @return
	 */
	public static int getWeekOfMonthGregorianCalendar(Calendar calendar) {
   		int weekOfMonth = calendar.get(Calendar.WEEK_OF_MONTH);
   		return weekOfMonth;
   	}

}
