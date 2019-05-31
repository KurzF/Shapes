package graphics.shapes.attributes;

import java.awt.Color;

/**
 * Describe color
 */
public class ColorAttributes extends Attributes {

	private boolean fill;
	private boolean stroke;
	private Color fill_color;
	private Color stroke_color;
	
	/**
	 * 
	 * @param fill Fill the shape
	 * @param stroke Draw the shape border
	 * @param fill_color Fill color
	 * @param strok_color Strok color
	 */
	public ColorAttributes(boolean fill, boolean stroke, Color fill_color, Color strok_color) {
		this.fill = fill;
		this.stroke = stroke;
		this.fill_color = fill_color;
		this.stroke_color = strok_color;
	}
	

	public ColorAttributes() {
		this(true, true, Color.BLACK, Color.BLACK);
	}
	
	@Override
	public String getID() {
		return Attributes.ColorID;
	}

	public boolean filled() {
		return this.fill;
	}
	
	public boolean stroked() {
		return this.stroke;
	}
	
	public Color filledColor() {
		return fill_color;
	}
	
	public Color strokedColor() {
		return stroke_color;
	}


	@Override
	public Attributes clone(){
		return new ColorAttributes(this.fill,this.stroke,this.fill_color,this.stroke_color);
	}
	
}
