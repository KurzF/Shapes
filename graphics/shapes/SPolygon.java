package graphics.shapes;

import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import graphics.shapes.attributes.Attributes;

public class SPolygon extends Shape{
	private Polygon pl;
	
	public SPolygon(Polygon pl, Map<String,Attributes> attributes, boolean withHandles) {
		super(attributes);
		
		this.pl = new Polygon();
		for(int i=0; i<pl.npoints; i++) {
			this.pl.addPoint(pl.xpoints[i], pl.ypoints[i]);
		}
		if(withHandles) {
			this.setResizeHandles();
		}
	}
	
	public SPolygon() {	
		this.pl = new Polygon();
		this.setResizeHandles();
	}
	
	public SPolygon(Polygon pl) {
		this(pl, new TreeMap<String,Attributes>(), true);
	}
	
	public void add(Point p) {
		this.pl.addPoint(p.x, p.y);
		this.refresh();
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
		this.refresh();
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
		return loc;
	}

	@Override
	public void setWidth(int width) {
		Rectangle rect = this.getBounds();
		Polygon poly = new Polygon();
		for(int i=0; i<this.pl.npoints; i++) {
			poly.addPoint((int) ((int) rect.x+(this.pl.xpoints[i]-rect.x)/((double)rect.width)*width), this.pl.ypoints[i]);
		}
		this.pl = poly;
		this.refresh();
	}

	@Override
	public void setHeight(int height) {
		Rectangle rect = this.getBounds();
		Polygon poly = new Polygon();
		for(int i=0; i<this.pl.npoints; i++) {
			poly.addPoint(this.pl.xpoints[i], (int) ((int) rect.y+(this.pl.ypoints[i]-rect.y)*1.0/rect.height*height));
		}
		this.pl = poly;
		this.refresh();
	}

	@Override
	public Shape clone() {
		return new SPolygon(this.pl, this.attributes, this.getResizeHandles()!=null);
	}

}