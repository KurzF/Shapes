package graphics.shapes;

import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SPolygon extends Shape{
	private Polygon pl;
	
	public SPolygon() {	
		this.pl = new Polygon();
	}
	
	public SPolygon(Polygon pl) {
		this.pl = pl;	
	}
	
	public void add(Point p) {
		this.pl.addPoint(p.x, p.y);
	}
		
	public Polygon getPolygon() {
		return this.pl;
	}
	
	public Point getLoc() {
		return this.pl.getBounds().getLocation();
	}
	
	public void setLoc(Point p) {
		Point loc = this.getLoc();
		Point delta = new Point(loc.x-p.x, loc.y-p.y);
		this.translate(delta.x, delta.y);
	}
	
	public void translate(int dx,int dy) {
		this.pl.translate(dx, dy);
	}
		
	public Rectangle getBounds() {
		return this.pl.getBounds();
	}
	
	public void accept(ShapeVisitor s) {
		s.visitPolygon(this);
	}
	
	@Override
	public Point getCenter() {
		Rectangle rect = this.getBounds();
		Point loc = rect.getLocation();
		loc.translate(rect.width/2, rect.height/2);
		return null;
	}
	@Override
	public void grow(int dx, int dy) {
		// TODO Auto-generated method stub
		
	}

}