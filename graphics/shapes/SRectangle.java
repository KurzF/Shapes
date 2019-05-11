package graphics.shapes;

import graphics.shapes.attributes.Attributes;
import graphics.shapes.attributes.ColorAttributes;
import graphics.shapes.attributes.ResizeAttributes;
import graphics.shapes.attributes.RotationAttributes;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;

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
		ResizeAttributes ra = (ResizeAttributes)this.getAttributes(ResizeAttributes.ID);
		if(ra != null) {
			ra.refresh();
		}
	}

	@Override
	public void translate(int x, int y) {
		this.rect.translate(x, y);
		ResizeAttributes ra = (ResizeAttributes)this.getAttributes(ResizeAttributes.ID);
		if(ra != null) {
			ra.refresh();
		}
	}

	@Override
	public Rectangle getBounds() {
		return (Rectangle)this.rect.clone();
	}
	
	@Override
	public Point getCenter() {
		Point p = this.getLoc();
		p.translate(this.rect.width/2, this.rect.height/2);
		return p;
	}
	
	@Override
	public void grow(int dx, int dy) {
		this.getRect().width += dx;
		this.getRect().height += dy;
		ResizeAttributes ra = (ResizeAttributes)this.getAttributes(ResizeAttributes.ID);
		if(ra != null) {
			ra.refresh();
		}
	}
	public void accept(ShapeVisitor vs) {
		vs.visitRectangle(this);
	}
}
