package graphics.shapes.attributes;

public class SelectionAttributes extends Attributes {
	private boolean selected;
	
	public SelectionAttributes() {
		this(false);
	}
	
	public SelectionAttributes(boolean b) {
		this.selected = b;
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
		return new SelectionAttributes(false);
	}
}