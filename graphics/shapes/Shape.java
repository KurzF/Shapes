package graphics.shapes;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import graphics.shapes.attributes.Attributes;
import graphics.shapes.attributes.SelectionAttributes;
import graphics.shapes.handles.Handle;
import graphics.shapes.handles.ResizeHandles;

import graphics.shapes.attributes.ColorAttributes;

/**
 * A drawable shape
 */
public abstract class Shape {
	
	protected Map<String,Attributes> attributes;
	private ResizeHandles resize_handles;
	
	public Shape(Map<String,Attributes> map) {
		Map<String,Attributes> copy=new HashMap<String,Attributes>();
		for(String s:map.keySet()){
			copy.put(s,((Attributes)map.get(s)).clone());
			System.out.println(copy.get(s));
		}
		this.attributes=copy;
		this.resize_handles = null;
	}
	
	public Shape() {
		this(new TreeMap<String,Attributes>());
	}
	
	public Shape(Shape s) {
		this.attributes = s.attributes;
	}
	
	public void addAttributes(Attributes a) {
		this.attributes.put(a.getID(), a);
	}
	
	public Attributes getAttributes(String a) {
		return this.attributes.get(a);
	}
	
	public ResizeHandles getResizeHandles() {
		return this.resize_handles;
	}
	
	public void setResizeHandles(ResizeHandles rh) {
		this.resize_handles = rh;
	}
	
	public void setResizeHandles() {
		this.resize_handles = new ResizeHandles(this);
	}
	public void refresh() {
		if(this.resize_handles!=null) {
			this.resize_handles.refresh();
		}
	}
	
	public abstract Point getLoc();
	public abstract void setLoc(Point p);
	public abstract void translate(int x, int y);
	public abstract Rectangle getBounds();
	public abstract Point getCenter();
	public abstract void setWidth(int width);
	public abstract void setHeight(int height);
	public int getWidth() {
		return this.getBounds().width;
	}
	public int getHeight() {
		return this.getBounds().height;
	}
	public abstract void accept(ShapeVisitor sv);
	
	public abstract Shape clone();
	
	public void setColor(Color color) {
		ColorAttributes ca = ((ColorAttributes) this.getAttributes(Attributes.ColorID));
		if(ca != null) {
			ca.setFilledColor(color);
		}
	}
	
	public boolean isSelected() {// Shouldn't be here
		SelectionAttributes sa = (SelectionAttributes)this.getAttributes(Attributes.SelectionID);
		if(sa != null) {
			return sa.isSelected();
		}
		return false;
	}
	
	
	
}
