package graphics.shapes.attributes;

import java.awt.Color;

public class ColorAttributes extends Attributes {
	public static final String ID = "color";
	
	private boolean fill;
	private boolean stroke;
	private Color fill_color;
	private Color stroke_color;
	
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
		return ColorAttributes.ID;
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
