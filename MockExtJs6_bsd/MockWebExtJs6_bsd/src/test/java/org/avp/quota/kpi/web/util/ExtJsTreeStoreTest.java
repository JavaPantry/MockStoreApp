package org.avp.quota.kpi.web.util;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.avp.quota.kpi.model.dao.SalesRepresentativeDao;
import org.avp.quota.kpi.model.dto.CodeDto;
import org.avp.quota.kpi.util.DtoFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sporcic.extjs.ExtData;

public class ExtJsTreeStoreTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
        ExtData response = new ExtData();
        TreeNode root = new TreeNode("Companies");
        
        TreeNode company1 =  new TreeNode("Company 1");
        root.getChildren().add(company1);
        TreeNode company2 =  new TreeNode("Company 2");
        	TreeNode billTo2_1 =  new TreeNode("billTo 2.1");
        		TreeNode shipTo2_1_1 =  new TreeNode("shipTo 2.1.1");
        		TreeNode shipTo2_1_2 =  new TreeNode("shipTo 2.1.2");
        		TreeNode shipTo2_1_3 =  new TreeNode("shipTo 2.1.3");
        		billTo2_1.getChildren().add(shipTo2_1_1);
        		billTo2_1.getChildren().add(shipTo2_1_2);
        		billTo2_1.getChildren().add(shipTo2_1_3);
        	TreeNode billTo2_2 =  new TreeNode("billTo 2.2");
        		TreeNode shipTo2_2_1 =  new TreeNode("shipTo 2.2.1");
        		TreeNode shipTo2_2_2 =  new TreeNode("shipTo 2.2.2");
        		billTo2_2.getChildren().add(shipTo2_2_1);
        		billTo2_2.getChildren().add(shipTo2_2_2);
        	TreeNode billTo2_3 =  new TreeNode("billTo 2.3");
        	TreeNode billTo2_4 =  new TreeNode("billTo 2.4");
        	company2.getChildren().add(billTo2_1);
        	company2.getChildren().add(billTo2_2);
        	company2.getChildren().add(billTo2_3);
        	company2.getChildren().add(billTo2_4);
        root.getChildren().add(company2);	
        TreeNode company3 =  new TreeNode("Company 3");
	    	TreeNode billTo3_1 =  new TreeNode("billTo 3.1");
	    	TreeNode billTo3_2 =  new TreeNode("billTo 3.2");
	    	TreeNode billTo3_3 =  new TreeNode("billTo 3.3");
	    		TreeNode shipTo3_3_1 =  new TreeNode("shipTo 3.3.1");
	    		TreeNode shipTo3_3_2 =  new TreeNode("shipTo 3.3.2");
	    		billTo3_3.getChildren().add(shipTo3_3_1);
	    		billTo3_3.getChildren().add(shipTo3_3_2);
	    	TreeNode billTo3_4 =  new TreeNode("billTo 3.4");
	    	company3.getChildren().add(billTo3_1);
	    	company3.getChildren().add(billTo3_2);
	    	company3.getChildren().add(billTo3_3);
	    	company3.getChildren().add(billTo3_4);
        root.getChildren().add(company3);	

        TreeNode company4 =  new TreeNode("Company 4");
        root.getChildren().add(company4);
        
        response.add(root);//
        response.setSuccess(true);
        
        
		//return response;
	}

}
