package graphics.shapes.attributes;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;

public class FontAttributes extends Attributes {

	private Font font;
	private Color font_color;
	
	public FontAttributes(Font font, Color color) {
		this.font = font;
		this.font_color = color;
	}
	
	@Override
	public String getID() {
		return "font";
	}
	
	public Color fontColor() {
		return this.font_color;
	}
	
	public Rectangle getBounds(String s) {
		return null;
	}
}
