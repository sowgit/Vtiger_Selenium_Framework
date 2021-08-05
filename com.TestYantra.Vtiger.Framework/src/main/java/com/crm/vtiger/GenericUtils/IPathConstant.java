package com.crm.vtiger.GenericUtils;
/**
 * This interface contains file path which is required in framework
 * @author Adarsh
 *
 */
public interface IPathConstant {
	String PROPERTY_FILEPATH="./src/main/resources/CommonData.properties";
	String EXCELPATH="./src/test/resources/testdata.xlsx";
	String JSONFILEPATH="";
	String htmlPath="./extentReport"+JavaUtility.getCurrentSystemDate()+".html";
}
