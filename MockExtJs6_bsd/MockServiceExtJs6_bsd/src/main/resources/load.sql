delete FROM Users
delete FROM Authorities
delete FROM ids_employee_profile
delete FROM ProductLine
delete FROM ids_security
delete FROM ids_manager
delete from ids_toc
delete from ids_security_toc
delete from ids_mdse_stru_lv3
delete from ids_quota

INSERT INTO Users (enabled,password,userId) VALUES ('true','password','Alexei Ptitchkin')
INSERT INTO Authorities (role,userId) VALUES ('ADMIN',1)     
INSERT INTO Authorities (role,userId) VALUES ('USER',1)     

--INSERT INTO ids_employee_profile (employee_id,employee_nm,status,job_title,company,location,location_nm,dept_id,dept_nm,manager_id,manager_level,report_path,report_path_name)
--VALUES(
-- employee_id,employee_nm,status,job_title,company,location,location_nm,dept_id,dept_nm,manager_id,manager_level,report_path,report_path_name

INSERT INTO ids_employee_profile (employee_id,employee_nm,status,job_title,company,location,location_nm,dept_id,dept_nm,manager_id,manager_level,report_path,report_path_name)
VALUES('C05622','Taizaburo Ted Egawa','A','* President','CAN','TMIS','AVP company','TTZ910','General Administration','C02924','7',NULL,NULL)

INSERT INTO ids_employee_profile (employee_id,employee_nm,status,job_title,company,location,location_nm,dept_id,dept_nm,manager_id,manager_level,report_path,report_path_name)
VALUES('C08420','Masaharu Choki','A','* Director, Plan & Ctrl-CIG','CAN','TMIS','AVP company','TTA111','TR Camera Adm - Marketing','T02899','3','T02899,C05622','Justin Lam;Taizaburo Ted Egawa')

INSERT INTO ids_employee_profile (employee_id,employee_nm,status,job_title,company,location,location_nm,dept_id,dept_nm,manager_id,manager_level,report_path,report_path_name)
VALUES('C10591','Josh Saji','T','* Manager, Bdgts & Invntry ISG','CAN','TMIS','AVP company','TTD001','Copier Administration','U03202','15','U03202,T02720,C05622','Elaine Kwong;Satch Hattori;Taizaburo Ted Egawa')

INSERT INTO ids_employee_profile (employee_id,employee_nm,status,job_title,company,location,location_nm,dept_id,dept_nm,manager_id,manager_level,report_path,report_path_name)
VALUES('C10744','Hiroshi Miyazato','T','Director Sr, IT','CAN','TMIS','AVP company','TTZ620','IT','T03197','13','T03197','Kazuto Ogawa')

INSERT INTO ids_employee_profile (employee_id,employee_nm,status,job_title,company,location,location_nm,dept_id,dept_nm,manager_id,manager_level,report_path,report_path_name)
VALUES('C11266','Makoto Shibata','T','* Mgr, Budget & Product Ctr','CAN','TMIS','AVP company','TTA110','TR Camera Administration','C08420','15','C08420,T02899,C05622','Masaharu Choki;Justin Lam;Taizaburo Ted Egawa')

INSERT INTO ids_employee_profile (employee_id,employee_nm,status,job_title,company,location,location_nm,dept_id,dept_nm,manager_id,manager_level,report_path,report_path_name)
VALUES('C11410','Takayuki Naruo','T','*Mgr, Service & Bus Analysis','CAN','TMIS','AVP company','TTAS20','TR Camera Service Admin','T01592','16','T01592,T02899,C05622','Steve Mackay;Justin Lam;Taizaburo Ted Egawa')

INSERT INTO ids_employee_profile (employee_id,employee_nm,status,job_title,company,location,location_nm,dept_id,dept_nm,manager_id,manager_level,report_path,report_path_name)
VALUES('C11428','Kenichiro Kitamura','A','Mgr, ISG Inventory Planning','CAN','TMIS','AVP company','TTZ919','ISG Finance','U03202','15','U03202,T02720,C05622','Elaine Kwong;Satch Hattori;Taizaburo Ted Egawa')

INSERT INTO ids_employee_profile (employee_id,employee_nm,status,job_title,company,location,location_nm,dept_id,dept_nm,manager_id,manager_level,report_path,report_path_name)
VALUES('C11437','Michihiko Takino','A','* Mgr Sr, IT','CAN','TMIS','AVP company','TTZ620','IT','C12667','9','C12667,C05622','Kenichiro Hijikata;Taizaburo Ted Egawa')

INSERT INTO ids_employee_profile (employee_id,employee_nm,status,job_title,company,location,location_nm,dept_id,dept_nm,manager_id,manager_level,report_path,report_path_name)
VALUES('C11789','Takashi Ichinomiya','A','Manager, PPG Bsns Plng','CAN','TMIS','AVP company','TTA110','TR Camera Administration','U01354','15','U01354,T02899,C05622','Chung Kuen Liu;Justin Lam;Taizaburo Ted Egawa')

/*
 * prior to fil ids_manager we need to productLine 
 */

INSERT INTO ProductLine (code,description) VALUES ('A', 'CAMERA');
INSERT INTO ProductLine (code,description) VALUES ('B', 'CALCULATOR');
INSERT INTO ProductLine (code,description) VALUES ('C', 'MICRO');
INSERT INTO ProductLine (code,description) VALUES ('D', 'COPIER');
INSERT INTO ProductLine (code,description) VALUES ('E', 'BCD');
INSERT INTO ProductLine (code,description) VALUES ('F', 'SEMICOM');
INSERT INTO ProductLine (code,description) VALUES ('I', 'PC COPIER');
INSERT INTO ProductLine (code,description) VALUES ('J', 'PERIPHERALS');
INSERT INTO ProductLine (code,description) VALUES ('K', 'FAX');
INSERT INTO ProductLine (code,description) VALUES ('L', 'LBP');
INSERT INTO ProductLine (code,description) VALUES ('M', 'TYPE WRITER');
INSERT INTO ProductLine (code,description) VALUES ('N', 'MEDICAL');
INSERT INTO ProductLine (code,description) VALUES ('P', 'C-ETW');
INSERT INTO ProductLine (code,description) VALUES ('Q', 'LFP');
INSERT INTO ProductLine (code,description) VALUES ('T', 'PRINTER');
INSERT INTO ProductLine (code,description) VALUES ('U', 'FAX PHONE');
INSERT INTO ProductLine (code,description) VALUES ('V', 'VIDEO');
INSERT INTO ProductLine (code,description) VALUES ('X', 'VISUAL COMM SYSTEMS');
INSERT INTO ProductLine (code,description) VALUES ('Y', 'GRAPHICS');
INSERT INTO ProductLine (code,description) VALUES ('Z', 'ADMIN');


/*
 * 
 * Fill SalesRep and SalesRepManager tables
 * 
 */
INSERT INTO ids_security (alt_salesrep_cd,alt_salesrep_nm,ids_user_id, all_access) VALUES ('CIGIT','HENRY YANG (IT)','C05622','true');
INSERT INTO ids_manager (alt_salesrep_cd,manager_id,first_prod_ctrl_cd) VALUES ('CIGIT','C05622','A');
INSERT INTO ids_manager (alt_salesrep_cd,manager_id,first_prod_ctrl_cd) VALUES ('CIGIT','C11266','Q');

INSERT INTO ids_security (alt_salesrep_cd,alt_salesrep_nm,ids_user_id, all_access) VALUES ('CIG001','TBD(WEST MANAGER)',NULL,'false');
INSERT INTO ids_manager (alt_salesrep_cd,manager_id,first_prod_ctrl_cd) VALUES ('CIG001','C05622','I');
INSERT INTO ids_manager (alt_salesrep_cd,manager_id,first_prod_ctrl_cd) VALUES ('CIG001','C11266','A');
INSERT INTO ids_manager (alt_salesrep_cd,manager_id,first_prod_ctrl_cd) VALUES ('CIG001','C11410','A');

INSERT INTO ids_security (alt_salesrep_cd,alt_salesrep_nm,ids_user_id,  all_access) VALUES ('CIG002','JOANNE HENRY','C08420','false');
INSERT INTO ids_manager (alt_salesrep_cd,manager_id,first_prod_ctrl_cd) VALUES ('CIG002','C05622','V');

INSERT INTO ids_security (alt_salesrep_cd,alt_salesrep_nm,ids_user_id,  all_access) VALUES ('CIG004','JERRY HOSIER','C10591','false');
INSERT INTO ids_manager (alt_salesrep_cd,manager_id,first_prod_ctrl_cd) VALUES ('CIG004','C05622','Y');

INSERT INTO ids_security (alt_salesrep_cd,alt_salesrep_nm,ids_user_id,  all_access) VALUES ('CIG005','ROB FARDEN','C10744','false');
INSERT INTO ids_manager (alt_salesrep_cd,manager_id,first_prod_ctrl_cd) VALUES ('CIG005','C11266','Q');

INSERT INTO ids_security (alt_salesrep_cd,alt_salesrep_nm,ids_user_id,  all_access) VALUES ('CIG006','RUSSELL BROWN','C11266','false');
INSERT INTO ids_manager (alt_salesrep_cd,manager_id,first_prod_ctrl_cd) VALUES ('CIG006','C11410','B');

INSERT INTO ids_security (alt_salesrep_cd,alt_salesrep_nm,ids_user_id,  all_access) VALUES ('CIG008','ANTHONY MASTRANGELO','C11410','false');
INSERT INTO ids_manager (alt_salesrep_cd,manager_id,first_prod_ctrl_cd) VALUES ('CIG008','C05622','D');

/*
CREATE TABLE ids_toc (
	toc_cd VARCHAR(8) NOT NULL,
	toc_nm VARCHAR(50),
	coa_br_cd VARCHAR(3),
	coa_ch_cd VARCHAR(3),
	coa_prod_cd VARCHAR(8),
	org_cd VARCHAR(8),
	org_layer_num NUMERIC(2 , 0),
	CONSTRAINT PK__ids_toc__5E1FF51F PRIMARY KEY (toc_cd) WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)
)
INSERT INTO [ccidw].[dbo].[ids_toc(toc_cd,toc_nm,coa_br_cd,coa_ch_cd,coa_prod_cd,org_cd,org_layer_num)VALUES
(<toc_cd, varchar(8),>
           ,<toc_nm, varchar(50),>
           ,<coa_br_cd, varchar(3),>
           ,<coa_ch_cd, varchar(3),>
           ,<coa_prod_cd, varchar(8),>
           ,<org_cd, varchar(8),>
           ,<org_layer_num, numeric,>)

CREATE TABLE ids_security_toc (
	alt_salesrep_cd VARCHAR(10) NOT NULL,
	toc_cd VARCHAR(8) NOT NULL,
	CONSTRAINT PK__ids_security_toc__20E1DCB5 PRIMARY KEY (toc_cd) WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)
)
INSERT INTO [ccidw].[dbo].[ids_security_toc (alt_salesrep_cd,toc_cd) VALUES
           (<alt_salesrep_cd, varchar(10),>
           ,<toc_cd, varchar(8),>)
*/


INSERT INTO ids_toc(toc_cd,toc_nm,coa_br_cd,coa_ch_cd,coa_prod_cd,org_cd,org_layer_num)VALUES ('TA451','ANTHONY M-TAKNOLOGY PPG A','125','998','AO','CA30E',5)
INSERT INTO ids_security_toc (alt_salesrep_cd,toc_cd) VALUES ('CIG008','TA451')

INSERT INTO ids_toc(toc_cd,toc_nm,coa_br_cd,coa_ch_cd,coa_prod_cd,org_cd,org_layer_num)VALUES ('TB479','N/A','125','241','BO','CB10E',5)
INSERT INTO ids_security_toc (alt_salesrep_cd,toc_cd) VALUES ('CIG008','TB479')

INSERT INTO ids_toc(toc_cd,toc_nm,coa_br_cd,coa_ch_cd,coa_prod_cd,org_cd,org_layer_num)VALUES ('TB732','ANTHONY M-TAKNOLOGY ISPG B','125','998','BO','CB80E',5)
INSERT INTO ids_security_toc (alt_salesrep_cd,toc_cd) VALUES ('CIG008','TB732')
-- end of CIG008

INSERT INTO ids_toc(toc_cd,toc_nm,coa_br_cd,coa_ch_cd,coa_prod_cd,org_cd,org_layer_num)VALUES ('EA416','CL CAMERA REG-1 AB          TC','105','998','AO','CA20W',5)
INSERT INTO ids_security_toc (alt_salesrep_cd,toc_cd) VALUES ('CIG006','EA416')

INSERT INTO ids_toc(toc_cd,toc_nm,coa_br_cd,coa_ch_cd,coa_prod_cd,org_cd,org_layer_num)VALUES ('EQ416','CL LFP AB PPG VA','105','998','SA','CB20W',5)
INSERT INTO ids_security_toc (alt_salesrep_cd,toc_cd) VALUES ('CIG006','EQ416')

INSERT INTO ids_toc(toc_cd,toc_nm,coa_br_cd,coa_ch_cd,coa_prod_cd,org_cd,org_layer_num)VALUES ('ET416','CL PRINTR PRT AB PRT PPG TC','105','998','TB','CB25W',5)
INSERT INTO ids_security_toc (alt_salesrep_cd,toc_cd) VALUES ('CIG006','ET416')

INSERT INTO ids_toc(toc_cd,toc_nm,coa_br_cd,coa_ch_cd,coa_prod_cd,org_cd,org_layer_num)VALUES ('EI416','CL PC COPIER CL VA','105','998','II','CB80W',5)
INSERT INTO ids_security_toc (alt_salesrep_cd,toc_cd) VALUES ('CIG006','EI416')

INSERT INTO ids_toc(toc_cd,toc_nm,coa_br_cd,coa_ch_cd,coa_prod_cd,org_cd,org_layer_num)VALUES 	('EA418','CL CAMERA REG-1 N/A -VISIONS','105','998','AO','CA10W',5)
INSERT INTO ids_security_toc (alt_salesrep_cd,toc_cd) VALUES ('CIG006','EA418')
-- end of CIG006

INSERT INTO ids_toc(toc_cd,toc_nm,coa_br_cd,coa_ch_cd,coa_prod_cd,org_cd,org_layer_num)VALUES ('VA421','ROB F - LONDON DRUGS PPG A','100','998','AO','CA10W',5)
INSERT INTO ids_security_toc (alt_salesrep_cd,toc_cd) VALUES ('CIG005','VA421')

INSERT INTO ids_toc(toc_cd,toc_nm,coa_br_cd,coa_ch_cd,coa_prod_cd,org_cd,org_layer_num)VALUES ('VA424','VA CAMERA REG-1 V.V-I.OKA-V RF','100','998','AO','CA20W',5)
INSERT INTO ids_security_toc (alt_salesrep_cd,toc_cd) VALUES ('CIG005','VA424')

INSERT INTO ids_toc(toc_cd,toc_nm,coa_br_cd,coa_ch_cd,coa_prod_cd,org_cd,org_layer_num)VALUES ('VI424','VA COPIER VA COPIER BC RF','100','998','TB','CB25W',5)
INSERT INTO ids_security_toc (alt_salesrep_cd,toc_cd) VALUES ('CIG005','VI424')

INSERT INTO ids_toc(toc_cd,toc_nm,coa_br_cd,coa_ch_cd,coa_prod_cd,org_cd,org_layer_num)VALUES ('VQ424','VA LFP BC PPG RF','100','998','SA','CB20W',5)
INSERT INTO ids_security_toc (alt_salesrep_cd,toc_cd) VALUES ('CIG005','VQ424')

INSERT INTO ids_toc(toc_cd,toc_nm,coa_br_cd,coa_ch_cd,coa_prod_cd,org_cd,org_layer_num)VALUES ('VT424','VA PRINTR PRT BC PRT PPG RF','100','998','TB','CB25W',5)
INSERT INTO ids_security_toc (alt_salesrep_cd,toc_cd) VALUES ('CIG005','VT424')

INSERT INTO ids_toc(toc_cd,toc_nm,coa_br_cd,coa_ch_cd,coa_prod_cd,org_cd,org_layer_num)VALUES ('VV423','ROB F - LONDON DRUGS PPG V','100','998','VV','CA10W',5)
INSERT INTO ids_security_toc (alt_salesrep_cd,toc_cd) VALUES ('CIG005','VV423')

INSERT INTO ids_toc(toc_cd,toc_nm,coa_br_cd,coa_ch_cd,coa_prod_cd,org_cd,org_layer_num)VALUES ('VV424','VA VIDEO  REG   V.V-I.OKA-V RF','100','998','VV','CA20W',5)
INSERT INTO ids_security_toc (alt_salesrep_cd,toc_cd) VALUES ('CIG005','VV424')
-- end of CIG005

INSERT INTO ids_toc(toc_cd,toc_nm,coa_br_cd,coa_ch_cd,coa_prod_cd,org_cd,org_layer_num)VALUES ('TB404','JERRRY H-ISPG B-DAISYTEK','125','998','BO','CB30E',5)
INSERT INTO ids_security_toc (alt_salesrep_cd,toc_cd) VALUES ('CIG004','TB404')

INSERT INTO ids_toc(toc_cd,toc_nm,coa_br_cd,coa_ch_cd,coa_prod_cd,org_cd,org_layer_num)VALUES ('TI404','JERRY H ISPG I-DAISYTEK','125','998','IC','CB30E',5)
INSERT INTO ids_security_toc (alt_salesrep_cd,toc_cd) VALUES ('CIG004','TI404')

INSERT INTO ids_toc(toc_cd,toc_nm,coa_br_cd,coa_ch_cd,coa_prod_cd,org_cd,org_layer_num)VALUES ('TT404','JERRY H ISPG T-DAISYTEK','125','998','TB','CB30E',5)
INSERT INTO ids_security_toc (alt_salesrep_cd,toc_cd) VALUES ('CIG004','TT404')
-- end of CIG004

INSERT INTO ids_toc(toc_cd,toc_nm,coa_br_cd,coa_ch_cd,coa_prod_cd,org_cd,org_layer_num)VALUES ('TA420','JOANNE H PPG A','125','998','AO','CA20E',5)
INSERT INTO ids_security_toc (alt_salesrep_cd,toc_cd) VALUES ('CIG002','TA420')

INSERT INTO ids_toc(toc_cd,toc_nm,coa_br_cd,coa_ch_cd,coa_prod_cd,org_cd,org_layer_num)VALUES ('TI420','JOANNE H ISPG I','125','998','II','CB25E',5)
INSERT INTO ids_security_toc (alt_salesrep_cd,toc_cd) VALUES ('CIG002','TI420')
	
INSERT INTO ids_toc(toc_cd,toc_nm,coa_br_cd,coa_ch_cd,coa_prod_cd,org_cd,org_layer_num)VALUES ('TQ420','TR LFP ON PPG QD','125','998','SA','CB20E',5)
INSERT INTO ids_security_toc (alt_salesrep_cd,toc_cd) VALUES ('CIG002','TQ420')
           
INSERT INTO ids_toc(toc_cd,toc_nm,coa_br_cd,coa_ch_cd,coa_prod_cd,org_cd,org_layer_num)VALUES ('TV419','JOANNE H PPG V','125','998','VV','CA20E',5)
INSERT INTO ids_security_toc (alt_salesrep_cd,toc_cd) VALUES ('CIG002','TV419')
-- end of CIG002

INSERT INTO ids_mdse_stru_lv3 (zeroth_prod_ctrl_cd,zeroth_prod_ctrl_nm,first_prod_ctrl_cd,first_prod_ctrl_nm,scd_prod_ctrl_cd,scd_prod_ctrl_nm,third_prod_ctrl_cd,third_prod_ctrl_nm)
VALUES('1A','PHOTO','A','CAMERA','A9','CEC','A9J','CEC')

INSERT INTO ids_mdse_stru_lv3 (zeroth_prod_ctrl_cd,zeroth_prod_ctrl_nm,first_prod_ctrl_cd,first_prod_ctrl_nm,scd_prod_ctrl_cd,scd_prod_ctrl_nm,third_prod_ctrl_cd,third_prod_ctrl_nm)
VALUES('1A','PHOTO','A','CAMERA','A9','CEC','A9Z','* NO CATEGORY')

INSERT INTO ids_mdse_stru_lv3 (zeroth_prod_ctrl_cd,zeroth_prod_ctrl_nm,first_prod_ctrl_cd,first_prod_ctrl_nm,scd_prod_ctrl_cd,scd_prod_ctrl_nm,third_prod_ctrl_cd,third_prod_ctrl_nm)
VALUES('1A','PHOTO','A','CAMERA','AA','DSLR','AAA','DSLR ACCESSORIES')

INSERT INTO ids_mdse_stru_lv3 (zeroth_prod_ctrl_cd,zeroth_prod_ctrl_nm,first_prod_ctrl_cd,first_prod_ctrl_nm,scd_prod_ctrl_cd,scd_prod_ctrl_nm,third_prod_ctrl_cd,third_prod_ctrl_nm)
VALUES('1A','PHOTO','A','CAMERA','AA','DSLR','AAE','E-STORE')

INSERT INTO ids_mdse_stru_lv3 (zeroth_prod_ctrl_cd,zeroth_prod_ctrl_nm,first_prod_ctrl_cd,first_prod_ctrl_nm,scd_prod_ctrl_cd,scd_prod_ctrl_nm,third_prod_ctrl_cd,third_prod_ctrl_nm)
VALUES('1A','PHOTO','A','CAMERA','AA','DSLR','AAG','ANALOG')

INSERT INTO ids_mdse_stru_lv3 (zeroth_prod_ctrl_cd,zeroth_prod_ctrl_nm,first_prod_ctrl_cd,first_prod_ctrl_nm,scd_prod_ctrl_cd,scd_prod_ctrl_nm,third_prod_ctrl_cd,third_prod_ctrl_nm)
VALUES('1A','PHOTO','A','CAMERA','AA','DSLR','AAH','DSLR HARDWARE')

INSERT INTO ids_mdse_stru_lv3 (zeroth_prod_ctrl_cd,zeroth_prod_ctrl_nm,first_prod_ctrl_cd,first_prod_ctrl_nm,scd_prod_ctrl_cd,scd_prod_ctrl_nm,third_prod_ctrl_cd,third_prod_ctrl_nm)
VALUES('1A','PHOTO','A','CAMERA','AA','DSLR','AAR','DSLR REFURBISHED')

INSERT INTO ids_mdse_stru_lv3 (zeroth_prod_ctrl_cd,zeroth_prod_ctrl_nm,first_prod_ctrl_cd,first_prod_ctrl_nm,scd_prod_ctrl_cd,scd_prod_ctrl_nm,third_prod_ctrl_cd,third_prod_ctrl_nm)
VALUES('1A','PHOTO','A','CAMERA','AA','DSLR','AAZ','* NOT CATEGORIZED')

INSERT INTO ids_mdse_stru_lv3 (zeroth_prod_ctrl_cd,zeroth_prod_ctrl_nm,first_prod_ctrl_cd,first_prod_ctrl_nm,scd_prod_ctrl_cd,scd_prod_ctrl_nm,third_prod_ctrl_cd,third_prod_ctrl_nm)
VALUES('1A','PHOTO','A','CAMERA','AD','SMD','ADA','TAMRAC')

INSERT INTO ids_mdse_stru_lv3 (zeroth_prod_ctrl_cd,zeroth_prod_ctrl_nm,first_prod_ctrl_cd,first_prod_ctrl_nm,scd_prod_ctrl_cd,scd_prod_ctrl_nm,third_prod_ctrl_cd,third_prod_ctrl_nm)
VALUES('1A','PHOTO','A','CAMERA','AD','SMD','ADB','PHATSTRAPS')

INSERT INTO ids_mdse_stru_lv3 (zeroth_prod_ctrl_cd,zeroth_prod_ctrl_nm,first_prod_ctrl_cd,first_prod_ctrl_nm,scd_prod_ctrl_cd,scd_prod_ctrl_nm,third_prod_ctrl_cd,third_prod_ctrl_nm)
VALUES('1A','PHOTO','A','CAMERA','AD','SMD','ADC','UMBRA')

INSERT INTO ids_mdse_stru_lv3 (zeroth_prod_ctrl_cd,zeroth_prod_ctrl_nm,first_prod_ctrl_cd,first_prod_ctrl_nm,scd_prod_ctrl_cd,scd_prod_ctrl_nm,third_prod_ctrl_cd,third_prod_ctrl_nm)
VALUES('1A','PHOTO','A','CAMERA','AD','SMD','ADD','CAPTURING COUTURE')

-- QUOTA
--CIG001
INSERT INTO ids_quota (DISCRIMINATOR, alt_salesrep_cd,third_prod_ctrl_cd,AmtType,Year,A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12)
VALUES('Q','CIG001','CatCode',1,2014,0,0,0,0,0,0,0,0,0,0,0,0)
--CIG002
INSERT INTO ids_quota (DISCRIMINATOR, alt_salesrep_cd,third_prod_ctrl_cd,AmtType,Year,A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12)
VALUES('Q','CIG002','CatCode',1,2014,0,0,0,0,0,0,0,0,0,0,0,0)
--CIG004
INSERT INTO ids_quota (DISCRIMINATOR, alt_salesrep_cd,third_prod_ctrl_cd,AmtType,Year,A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12)
VALUES('Q','CIG004','CatCode',1,2014,0,0,0,0,0,0,0,0,0,0,0,0)
--CIG005
INSERT INTO ids_quota (DISCRIMINATOR, alt_salesrep_cd,third_prod_ctrl_cd,AmtType,Year,A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12)
VALUES('Q','CIG005','CatCode',1,2014,0,0,0,0,0,0,0,0,0,0,0,0)
--CIG006
INSERT INTO ids_quota (DISCRIMINATOR, alt_salesrep_cd,third_prod_ctrl_cd,AmtType,Year,A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12)
VALUES('Q','CIG006','CatCode',1,2014,0,0,0,0,0,0,0,0,0,0,0,0)
--CIG008
INSERT INTO ids_quota (DISCRIMINATOR, alt_salesrep_cd,third_prod_ctrl_cd,AmtType,Year,A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12)
VALUES('Q','CIG008','CatCode',1,2014,0,0,0,0,0,0,0,0,0,0,0,0)
--CIGIT
INSERT INTO ids_quota (DISCRIMINATOR,alt_salesrep_cd,third_prod_ctrl_cd,AmtType,Year,A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12)
VALUES('Q','CIGIT','CatCode',1,2014,0,0,0,0,0,0,0,0,0,0,0,0)
-- BUDGET
--CIG002
INSERT INTO ids_quota (DISCRIMINATOR, alt_salesrep_cd,third_prod_ctrl_cd,AmtType,Year,A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12)
VALUES('B','CIG002','CatCode',1,2014,1,0,2,0,3,0,4,0,5,0,6,0)
--CIG004
INSERT INTO ids_quota (DISCRIMINATOR, alt_salesrep_cd,third_prod_ctrl_cd,AmtType,Year,A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12)
VALUES('B','CIG004','CatCode',1,2014,1,0,2,0,3,0,4,0,5,0,6,0)
--CIG005
INSERT INTO ids_quota (DISCRIMINATOR, alt_salesrep_cd,third_prod_ctrl_cd,AmtType,Year,A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12)
VALUES('B','CIG005','CatCode',1,2014,1,0,2,0,3,0,4,0,5,0,6,0)
--CIG006
INSERT INTO ids_quota (DISCRIMINATOR, alt_salesrep_cd,third_prod_ctrl_cd,AmtType,Year,A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12)
VALUES('B','CIG006','CatCode',1,2014,1,0,2,0,3,0,4,0,5,0,6,0)
--CIG008
INSERT INTO ids_quota (DISCRIMINATOR, alt_salesrep_cd,third_prod_ctrl_cd,AmtType,Year,A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12)
VALUES('B','CIG008','CatCode',1,2014,1,0,2,0,3,0,4,0,5,0,6,0)
--CIGIT
INSERT INTO ids_quota (DISCRIMINATOR,alt_salesrep_cd,third_prod_ctrl_cd,AmtType,Year,A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12)
VALUES('B','CIGIT','CatCode',1,2014,1,0,2,0,3,0,4,0,5,0,6,0)


--******************************************************************************************************		
--*********************************************** verify ***********************************************
--******************************************************************************************************		
select * FROM Users
select * FROM Authorities
select * FROM ids_employee_profile
select * FROM ProductLine
select * FROM ids_security
select * FROM ids_manager
select * from ids_toc
select * from ids_security_toc
select * from ids_mdse_stru_lv3
select * from ids_quota
