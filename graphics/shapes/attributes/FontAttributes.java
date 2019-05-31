package graphics.shapes.attributes;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.font.FontRenderContext;

/**
 * Describe the font and the color of a text
 */

public class FontAttributes extends Attributes {

	private Font font;
	private Color font_color;
	
	public FontAttributes() {
		this(new Font("Ariel", Font.PLAIN, 12), Color.BLACK);
	}
	
	/**
	 * 
	 * @param font Font of text
	 * @param color Color of the text
	 */
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
	
	/**
	 * 
	 * @param s The string to bound
	 * @return Rectangle with the dimensions of the text but located at (0,0)
	 */
	public Rectangle getBounds(String s) {
		return this.font.getStringBounds(s, new FontRenderContext(null,RenderingHints.VALUE_TEXT_ANTIALIAS_DEFAULT, RenderingHints.VALUE_FRACTIONALMETRICS_DEFAULT)).getBounds();
	}
	
	public Attributes clone(){
		return new FontAttributes(this.font,this.font_color);
	}
	
}
