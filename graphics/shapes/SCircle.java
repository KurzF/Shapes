package graphics.shapes;

import java.awt.Point;
import java.awt.Rectangle;

import graphics.shapes.attributes.ResizeAttributes;

public class SCircle extends Shape {
	
	private int radius;
	private Point loc; // top left corner of bound
	
	public SCircle(Point p, int radius) {
		this.setLoc(p);
		this.setRadius(radius);
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
		return new Rectangle(this.loc.x, this.loc.y, this.radius, this.radius);
	}

	@Override
	public void accept(ShapeVisitor sv) {
		sv.visitCircle(this);
	}

	public int getRadius() {
		return this.radius;
	}
	
	public void setRadius(int d) {
		this.radius = d;
	}

	@Override
	public void grow(int dx, int dy) {
		this.radius += Math.abs(dx) < Math.abs(dy) ? dx : dy;
		ResizeAttributes ra = (ResizeAttributes)this.getAttributes(ResizeAttributes.ID);
		if(ra != null) {
			ra.refresh();
		}
	}
}
