package graphics.shapes;

import java.awt.Point;
import java.awt.Rectangle;

import graphics.shapes.attributes.ResizeAttributes;

public class SCircle extends Shape {
	
	private int diameter;
	private Point loc; // top left corner of bound
	
	public SCircle(Point p, int diameter) {
		this.setLoc(p);
		this.setDiameter(diameter);
	}
	
	@Override
	public Point getLoc() {
		return this.loc;
	}

	@Override
	public void setLoc(Point p) {
		this.loc = p;
		ResizeAttributes ra = (ResizeAttributes)this.getAttributes(ResizeAttributes.ID);
		if(ra != null) {
			ra.setLoc(p);
		}
	}

	@Override
	public void translate(int x, int y) {
		this.loc.translate(x, y);
		ResizeAttributes ra = (ResizeAttributes)this.getAttributes(ResizeAttributes.ID);
		if(ra != null) {
			ra.translate(x, y);
		}
	}

	@Override
	public Rectangle getBound() {
		return new Rectangle(this.loc.x, this.loc.y, this.diameter, this.diameter);
	}

	@Override
	public void accept(ShapeVisitor sv) {
		sv.visitCircle(this);
	}

	public int getDiameter() {
		return this.diameter;
	}
	
	public void setDiameter(int d) {
		this.diameter = d;
	}

	@Override
	public void grow(int dx, int dy) {
		this.diameter += Math.abs(dx) < Math.abs(dy) ? dx : dy;
		ResizeAttributes ra = (ResizeAttributes)this.getAttributes(ResizeAttributes.ID);
		if(ra != null) {
			ra.refresh();
		}
	}
}
