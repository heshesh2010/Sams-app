package com.apps.sams;

import java.util.List;

import autoButton.StudentHelper;

public class Pair {

	public List<StudentHelper> StudentNamesList;
	public List<StudentHelper> StudentIdList;
	public List<StudentHelper> StudentCodeList;
	public List<ClassesHelper> ClassesList;
	public List<ClassesHelper> ClassesListIDS;
	public List<StudentHelper> StudentStatusList; 
	public String error = "h";
	public String Course_Name;
	public String Class_Name;
	public String Class_id;
	public String date;
	
	public void setCourseName(String Course_Name) {
		this.Course_Name = Course_Name;
	}
	
	public void setdate(String date) {
		this.date = date;
	}
	
	
	public void setClassId(String Class_id) {
		this.Class_id = Class_id;
	}
	
	public void setClassName(String Class_Name) {
		this.Class_Name = Class_Name;
	}
	
	public String getCourseName(){
		
		return this.Course_Name;
		
	}
	
	
	public String getClassName(){
		
		return this.Class_Name;
		
	}
	
	public String getClassId(){
		
		return this.Class_id;
		
	}
public String getdate(){
		
		return this.date;
		
	}

	public void setclasses(List<ClassesHelper> ClassesList) {
		this.ClassesList = ClassesList;
	}
	
	public void setclassesIDS(List<ClassesHelper> ClassesListIDS) {
		this.ClassesListIDS = ClassesListIDS;
	}
	
	public void setids(List<StudentHelper> StudentIdList1) {
		this.StudentIdList = StudentIdList1;
	}

	public void setcode(List<StudentHelper> StudentcodeList1) {
		this.StudentCodeList = StudentcodeList1;
	}
	
	public void setstatus(List<StudentHelper> StudentStatusList) {
		this.StudentStatusList = StudentStatusList;
	}
	
	public void seterror(String error){
		
		this.error = error;
		
	}
	
	public String geterror (){
		
		return error;
		
	}
	
	
	public void setnames(List<StudentHelper> StudentNamesList1) {
		this.StudentNamesList = StudentNamesList1;
	}

	public List<ClassesHelper> getclasses() {
		return this.ClassesList;
	}
	public List<ClassesHelper> getclassesIDS() {
		return this.ClassesListIDS;
	}
	
	public List<StudentHelper> getids() {
		return this.StudentIdList;
	}

	public List<StudentHelper> getcode() {
		return this.StudentCodeList;
	}
	
	public List<StudentHelper> getstatus() {
		return this.StudentStatusList;
	}
	public List<StudentHelper> getnames() {
		return this.StudentNamesList;
	}
	
	
	
}