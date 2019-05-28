package graphics.shapes.attributes;

import graphics.shapes.SRectangle;
import graphics.shapes.Shape;

import java.awt.Color;
import java.util.Map;
import java.util.TreeMap;

public class ColorAttributes extends Attributes {

	private boolean fill;
	private boolean stroke;
	private Color fill_color;
	private Color stroke_color;
	
	public ColorAttributes(boolean fill, boolean stroke, Color fill_color, Color stroke_color) {
		this.fill = fill;
		this.stroke = stroke;
		this.fill_color = fill_color;
		this.stroke_color = stroke_color;
	}
	
	public ColorAttributes() {
		this(true, true, Color.BLACK, Color.BLACK);
	}
	

	@Override
	public String getID() {
		return Attributes.ColorID;
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
	
	public Attributes clone(){
		return new ColorAttributes(this.fill,this.stroke,this.fill_color,this.stroke_color);
	}
}