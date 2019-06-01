package graphics.shapes;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.Map;
import java.util.TreeMap;

import graphics.shapes.attributes.Attributes;
import graphics.shapes.attributes.ResizeAttributes;

/**
 * A drawable circle
 */
public class SCircle extends Shape {
	
	private int radius;
	private Point loc; // top left corner of bound
	
	public SCircle(Point p, int radius, Map<String, Attributes> attributes, boolean withHandle) {
		super(attributes);
		this.setLoc(p);
		this.setRadius(radius);
		if(withHandle) {
			this.setResizeHandles();
		}
	}
	
	public SCircle(Point p, int radius) {
		this(p, radius, new TreeMap<String, Attributes>(), true);
	}
	
	@Override
	public Point getLoc() {
		return new Point(this.loc);
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
		this.refresh();
	}
	
	@Override
	public void setWidth(int width) {
		System.out.println("circle");
		this.radius = width/2;
		this.refresh();
	}

	@Override
	public void setHeight(int height) {
		this.radius = height/2;
		this.refresh();
	}

	@Override
	public Shape clone() {
		return new SCircle((Point)loc.clone(), this.radius, this.attributes, this.getResizeHandles()!=null);
	}
}
