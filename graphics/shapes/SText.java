package graphics.shapes;

import java.awt.Point;

import java.awt.Rectangle;
import java.util.Map;
import java.util.TreeMap;

import graphics.shapes.attributes.Attributes;
import graphics.shapes.attributes.FontAttributes;

/**
 * A drawable text
 */
public class SText extends Shape {
	
	private String text;
	private Point loc;

	public SText(Point p, String text, Map<String, Attributes> attributes, boolean withHandles) {
		super(attributes);
		this.setLoc(p);
		this.setText(text);
		if(withHandles) {
			this.setResizeHandles();
		}
	}
	
	public SText(Point p, String text, Map<String, Attributes> attributes) {
		this(p,text, attributes, true);
	}
	
	public SText(Point p, String text) {
		this(p,text, new TreeMap<String, Attributes>(), true);
	}
	
	public String getText() {
		return this.text;
	}
	
	public void setText(String text) {
		this.text = text;
		this.refresh();
	}
	
	@Override
	public Point getLoc() {
		Point location = (Point)this.loc.clone();
		Rectangle r = this.getBounds();
		location.translate(0, +r.height);
		return location;
	}

	@Override
	public void setLoc(Point p) {
		this.loc = p;
		this.refresh();
	}

	@Override
	public void translate(int x, int y) {
		this.loc.translate(x, y);
		this.refresh();
	}

	@Override
	public Rectangle getBounds() {
		FontAttributes fa = (FontAttributes)this.getAttributes(Attributes.FontID);
		if(fa == null) { fa = new FontAttributes(); }
		Rectangle r = fa.getBounds(this.text);
		r.setLocation(this.loc);
		r.translate(0, +r.height);
		return r;
	}
	
	@Override
	public Point getCenter() {
		Rectangle rect = this.getBounds();
		Point p = this.getLoc();
		p.translate(rect.width/2, rect.height/2);
		return p;
	}
	
	public int getHeight() {
		FontAttributes fa = (FontAttributes)this.getAttributes(Attributes.FontID);
		if(fa == null) { fa = new FontAttributes(); }
		Rectangle r = fa.getBounds(this.text);
		return r.height;
	}
	
	@Override
	public void accept(ShapeVisitor sv) {
		sv.visitText(this);
	}
	
	@Override
	public void setWidth(int width) {
		// This will remain empty
		
	}
	@Override
	public void setHeight(int height) {
		FontAttributes fa = (FontAttributes)this.getAttributes(Attributes.FontID);
		if(fa == null) { fa = new FontAttributes(); }
		fa.setFontSize(height);
		this.addAttributes(fa);
		this.refresh();
	}
	@Override
	public Shape clone() {
		return new SText((Point)this.loc.clone(), this.text, this.attributes, this.getResizeHandles()!=null);
	}

}
