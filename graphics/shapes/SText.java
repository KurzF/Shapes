package graphics.shapes;

import java.awt.Point;
import java.awt.Rectangle;

import graphics.shapes.attributes.FontAttributes;

public class SText extends Shape {
	
	private String text;
	private Point loc;

	public SText(Point p, String text) {
		this.setLoc(p);
		this.setText(text);
	}
	public String getText() {
		return this.text;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	@Override
	public Point getLoc() {
		return this.loc;
	}

	@Override
	public void setLoc(Point p) {
		this.loc = p;
	}

	@Override
	public void translate(int x, int y) {
		this.loc.translate(x, y);
	}

	@Override
	public Rectangle getBound() {
		FontAttributes fa = (FontAttributes)this.getAttributes(FontAttributes.ID);
		if(fa == null) { fa = new FontAttributes(); }
		Rectangle r = fa.getBounds(this.text);
		r.translate(this.loc.x, this.loc.y);//TODO remove and correct -3 
		return r;
	}

	@Override
	public void accept(ShapeVisitor sv) {
		sv.visitText(this);
	}
	@Override
	public void grow(int dx, int dy) {
		// TODO Auto-generated method stub
		
	}

}
