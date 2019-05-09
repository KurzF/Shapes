package graphics.shapes;

import graphics.shapes.attributes.Attributes;
import graphics.shapes.attributes.ColorAttributes;
import graphics.shapes.attributes.RotationAttributes;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;

public class SRectangle extends Shape {
	private Rectangle rect;
	
	public SRectangle(Rectangle rect) {
		super();
		this.rect = rect;
	}
	
	public SRectangle(Point p,int width, int height) {
		this(new Rectangle(p.x, p.y, width, height));
	}
	
	public Rectangle getRect() {
		return this.rect;
	}
	
	@Override
	public Point getLoc() {
		return this.rect.getLocation();
	}

	@Override
	public void setLoc(Point p) {
		this.rect.setLocation(p);
	}

	@Override
	public void translate(int x, int y) {
		this.rect.translate(x, y);
	}

	@Override
	public Rectangle getBound() {
		return this.rect;
	}
	
	public void accept(ShapeVisitor vs) {
		vs.visitRectangle(this);
	}
}