package graphics.shapes;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.Map;
import java.util.TreeMap;

import graphics.shapes.attributes.Attributes;

public abstract class Shape {
	private Map<String,Attributes> attributes;
	
	public Shape() {
		this.attributes = new TreeMap();
	}
	
	public void addAttribute(Attributes a) {
		this.attributes.put(a.getID(), a);
	}
	
	public Attributes getAttributes(String a) {
		return this.attributes.get(a);
	}
	
	public abstract Point getLoc();
	public abstract void setLoc(Point p);
	public abstract void translate(int x, int y);
	public abstract Rectangle getBound();
	public abstract void accept(ShapeVisitor sv);
	
}