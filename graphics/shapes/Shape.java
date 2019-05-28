package graphics.shapes;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.util.Map;
import java.util.TreeMap;

import graphics.shapes.attributes.Attributes;
import graphics.shapes.attributes.SelectionAttributes;

public abstract class Shape {
	protected Map<String,Attributes> attributes; //TODO : private
	
	public Shape() {
		this.attributes = new TreeMap<String,Attributes>();
	}
	
	public Shape(Map<String,Attributes> map) {
		Map<String,Attributes> copy=new TreeMap<String,Attributes>();
		
		for(String s:map.keySet()){
			copy.put(s,((Attributes)map.get(s)).clone());
			System.out.println(copy.get(s));
		}
		this.attributes=copy;
	}
	
	
	public void addAttributes(Attributes a) {
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
	public abstract Shape clone();
	
	public boolean isSelected() {// Shouldn't be here
		SelectionAttributes sa = (SelectionAttributes)this.getAttributes(Attributes.SelectionID);
		if(sa != null) {
			return sa.isSelected();
		}
		return false;
	}
	
}