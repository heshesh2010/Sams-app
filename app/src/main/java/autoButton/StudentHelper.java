package autoButton;

import java.io.Serializable;

public class StudentHelper implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private  String name;
	  private boolean selected;

	  public StudentHelper(String name) {
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