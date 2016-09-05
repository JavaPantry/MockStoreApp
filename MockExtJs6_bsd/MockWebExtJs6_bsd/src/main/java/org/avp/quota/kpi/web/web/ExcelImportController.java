package org.avp.quota.kpi.web.web;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.avp.quota.kpi.model.dto.BaseImportDto;
import org.sporcic.extjs.ExtData;
import org.sporcic.extjs.ExtResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;


/*
 * Problem reading response on POST file-upload in IE
 * http://stackoverflow.com/questions/8151138/ie-tries-to-download-json-response-while-submitting-jquery-multipart-form-data-c
 */
@Controller("excelImportController")
public class ExcelImportController extends AbstractQuotaKPIController {
	
	private static Logger logger = Logger.getLogger(ExcelImportController.class);

	private final static ExtResponse SUCCESS = new ExtResponse();

	static{
		SUCCESS.setSuccess(true);
		SUCCESS.setMessage("Ok");
	}
	//,produces = "application/json, text/x-json" "text/json" 
	// last attempt - ,produces = "application/json"
	@RequestMapping(value={"/companyUpload"}, method = RequestMethod.POST,produces = "application/json")
	@ResponseBody
	public ExtResponse companyUpload(@RequestParam("fileUpload") MultipartFile fileUpload) throws Exception{
		ExtData response = new ExtData();
		
		InputStream fis = fileUpload.getInputStream();
	    try {
	        while (fis.read()>-1){}
	    } finally {
	        fis.close();
	    }
		
		/*Sheet sheet = readFile(fileUpload);
		ParseResult<MdsCompany> result = (new CompanyParser()).parse(sheet);
        logger.debug("parsed data:" + result);
        
        List<ValidatedEntity<MdsCompany>> validatedEntities = result.getValidatedEntities();
        List<ImportCompanyDto> importCompanyDtos = new ArrayList<ImportCompanyDto>();
        int idx=0;
        for (ValidatedEntity<MdsCompany> validatedEntity : validatedEntities) {
        	ImportCompanyDto dto = new ImportCompanyDto(validatedEntity.getEntity().getCode(), validatedEntity.getEntity().getName(), (idx++)%2==0?true:false);//validatedEntity.hasErrors()
        	if(dto.isHasErrors()){
        		//dto.setMessages("<div>Error 1</div><div>Error 2</div><div>Error 3</div><div>Error 4</div><div>Error 5</div><div>Error 6</div>");
        		dto.setMessages("Error 1,Error 2,Error 3,Error 4,Error 5,Error 6");
        	}
        	importCompanyDtos.add(dto);
		}*/
		List<ImportCompanyDto> importCompanyDtos = new ArrayList<ImportCompanyDto>();
		for(int i=0;i<10;i++){
			//ImportCompanyDto dto = new ImportCompanyDto("code"+i,"name"+i,((i%2==0)?true:false));
			ImportCompanyDto dto = new ImportCompanyDto("code"+i,"name"+i,false);
			if(dto.isHasErrors()){
        		//dto.setMessages("<div>Error 1</div><div>Error 2</div><div>Error 3</div><div>Error 4</div><div>Error 5</div><div>Error 6</div>");
        	}
			importCompanyDtos.add(dto);
		}
        response.add(importCompanyDtos);
        response.setSuccess(true);
		return response;
	}

	static class ImportCompanyDto extends BaseImportDto {
		private String code;
		private String name;
		
		
		public ImportCompanyDto(String code, String name, boolean hasErrors) {
			super();
			this.code = code;
			this.name = name;
			this.hasErrors = hasErrors;
		}
		
		
		public String getCode() {return code;}
		public void setCode(String code) {this.code = code;}
		public String getName() {return name;}
		public void setName(String name) {this.name = name;}
	}
	
	
/*	private Sheet readFile(MultipartFile fileUpload) throws IOException, InvalidFormatException {
		InputStream fis = fileUpload.getInputStream();
	    try {
	        Workbook workbook = WorkbookFactory.create(fis);
	        return null == workbook ? null : workbook.getSheetAt(0);
	    } finally {
	        fis.close();
	    }
	}
*/
	
}
