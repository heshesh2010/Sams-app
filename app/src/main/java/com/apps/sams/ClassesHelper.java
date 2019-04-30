package com.apps.sams;

import java.io.Serializable;

public class ClassesHelper implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private  String name;
	  private boolean selected;

	  public ClassesHelper(String name) {
	    this.name = name;
	    selected = false;
	  }

	  public  String getName() {
	    return name;
	  }

	  public void setName(String name) {
	    this.name = name;
	  }

	  public boolean isSelected() {
	    return selected;
	  }

	  public void setSelected(boolean selected) {
	    this.selected = selected;
	  }


}