package graphics.shapes;

import java.awt.Point;
import java.awt.Rectangle;

import graphics.shapes.attributes.ResizeAttributes;

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
			ra.setLoc(p);
		}
	}

	@Override
	public void translate(int x, int y) {
		this.rect.translate(x, y);
		ResizeAttributes ra = (ResizeAttributes)this.getAttributes(ResizeAttributes.ID);
		if(ra != null) {
			ra.translate(x, y);
		}
	}

	@Override
	public Rectangle getBound() {
		return this.rect;
	}
	
	public void accept(ShapeVisitor vs) {
		vs.visitRectangle(this);
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
}
