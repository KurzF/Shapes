package graphics.shapes.attributes;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.font.FontRenderContext;

public class FontAttributes extends Attributes {

	private Font font;
	private Color font_color;
	
	public FontAttributes() {
		this(new Font("Ariel", Font.PLAIN, 12), Color.BLACK);
	}
	public FontAttributes(Font font, Color color) {
		this.font = font;
		this.font_color = color;
	}
	
	@Override
	public String getID() {
		return Attributes.FontID;
	}
	
	public Font font() {
		return this.font;
	}
	public Color fontColor() {
		return this.font_color;
	}
	
	public Rectangle getBounds(String s) {
		return this.font.getStringBounds(s, new FontRenderContext(null,RenderingHints.VALUE_TEXT_ANTIALIAS_DEFAULT, RenderingHints.VALUE_FRACTIONALMETRICS_DEFAULT)).getBounds();
	}
}