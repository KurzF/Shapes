package graphics.shapes.attributes;

import java.awt.Color;

public class ColorAttributes extends Attributes {

	private boolean fill;
	private boolean stroke;
	private Color fill_color;
	private Color stroke_color;
	
	public ColorAttributes(boolean fill, Color fill_color, boolean stroke, Color strok_color) {
		this.fill = fill;
		this.stroke = stroke;
		this.fill_color = fill_color;
		this.stroke_color = strok_color;
	}
	
	public ColorAttributes() {
		this(true, Color.BLACK, true, Color.BLACK);
	}
	
	@Override
	public String getID() {
		return "color";
	}

	public boolean filled() {
		return fill;
	}
	
	public boolean stroked() {
		return stroke;
	}
	
	public Color filledColor() {
		return fill_color;
	}
	
	public Color strokedColor() {
		return stroke_color;
	}
	
}
