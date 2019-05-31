package graphics.shapes.attributes;

/**
 * Allow to select the shape
 * 
 */
public class SelectionAttributes extends Attributes {
	private boolean selected;
	
	/**
	 * 
	 * @param selected Is shape selected
	 */
	public SelectionAttributes(boolean selected) {
		this.selected = selected;
	}
	
	public SelectionAttributes() {
		this(false);
	}

	public String getID() {
		return Attributes.SelectionID;
	}
	
	public boolean isSelected() {
		return this.selected;
	}
	
	public void select() {
		this.selected = true;
	}
	
	public void unselect() {
		this.selected = false;
	}
	
	public void toggleSelection() {
		this.selected = !this.selected;
	}
	
	public Attributes clone(){
		return new SelectionAttributes(this.selected);
	}
	
}
