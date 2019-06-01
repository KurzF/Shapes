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
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

/**
 * A drawable rectangle
 */
public class SRectangle extends Shape {
	private Rectangle rect;
	
	public SRectangle(Point p,int width, int height, Map<String,Attributes> attributes, boolean withHandles) {
		super(attributes);
		this.rect = new Rectangle(p.x, p.y, width, height);
		if(withHandles) {
			this.setResizeHandles();
		}

	}
	
	public SRectangle(Rectangle rect, Map<String,Attributes> attributes, boolean withHandles) {
		super(attributes);
		this.rect = (Rectangle)rect.clone();
		if(withHandles) {
			this.setResizeHandles();
		}
	}
	
	public SRectangle(Point p,int width, int height, boolean withHandles) {
		this(p, width, height, new TreeMap<String, Attributes>(), withHandles);
	}
	
	public SRectangle(Point p,int width, int height) {
		this(p, width, height, new TreeMap<String, Attributes>(), true);
	}
	
	public SRectangle(Rectangle rect, Map<String,Attributes> attributes) {
		this(rect,attributes,true);
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
		this.refresh();
	}

	@Override
	public void translate(int x, int y) {
		this.rect.translate(x, y);
		this.refresh();
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
	
	public void accept(ShapeVisitor vs) {
		vs.visitRectangle(this);
	}

	@Override
	public void setWidth(int width) {
		this.rect.width = width;
		this.refresh();
	}

	@Override
	public void setHeight(int height) {
		this.rect.height = height;
		this.refresh();
	}

	@Override
	public Shape clone() {
		return new SRectangle(this.rect,this.attributes, this.getResizeHandles()!=null);
	}


}
