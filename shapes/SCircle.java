package graphics.shapes;

import graphics.shapes.attributes.Attributes;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.Map;

public class SCircle extends Shape {
	
	private int radius;
	private Point loc; // top left corner of bound
	
	public SCircle(Point p, int radius) {
		this.setLoc(p);
		this.setRadius(radius);
	}
	
	public SCircle(int rad,Point p,Map<String,Attributes> map){
		super(map);
		this.radius=rad;
		this.loc=(Point) p.clone();
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
	}

	@Override
	public Rectangle getBound() {
		return new Rectangle(this.loc.x, this.loc.y, this.radius*2, this.radius*2);
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
	
	public Shape clone(){
		return new SCircle(this.radius,this.loc,attributes);
	}
}