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
		return new Point(this.loc);
	}

	@Override
	public void setLoc(Point p) {
		this.loc = p;
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
	public Rectangle getBounds() {
		return new Rectangle(this.loc.x, this.loc.y, this.radius*2, this.radius*2);
	}
	
	@Override
	public Point getCenter() {
		Point p = this.getLoc();
		p.translate(this.radius, this.radius);
		return p;
	}
	
	@Override
	public void accept(ShapeVisitor sv) {
		sv.visitCircle(this);
	}

	public int getRadius() {
		return this.radius;
	}
	
	public void setRadius(int r) {
		this.radius = r;
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
