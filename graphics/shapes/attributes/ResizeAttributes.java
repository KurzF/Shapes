package graphics.shapes.attributes;

import java.util.TreeMap;

import graphics.shapes.SRectangle;
import graphics.shapes.Shape;
import graphics.shapes.handlers.Handler;
import graphics.shapes.handlers.RHBottomLeft;
import graphics.shapes.handlers.RHBottomRight;
import graphics.shapes.handlers.RHTopLeft;
import graphics.shapes.handlers.RHTopRight;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;

public class ResizeAttributes extends Attributes {
	public static final String ID = "resize";
	public static int size = 5;
	private ArrayList<Handler> handlers;
	
	public ResizeAttributes(Shape master) {
		super();
		this.handlers = new ArrayList<Handler>();
		this.add(new RHTopLeft(master));
		this.add(new RHBottomRight(master));
		this.add(new RHTopRight(master));
		this.add(new RHBottomLeft(master));
	}
	
	public Iterator<Handler> iterator() {
		return this.handlers.iterator();
	}
	
	public void add(Handler h) {
		this.handlers.add(h);
	}
	
	public void translate(int dx, int dy) {
		for(Handler h : this.handlers) {
			h.translate(dx, dy);
		}
	}
		
	public void refresh() {
		Point loc = this.handlers.get(0).getMaster().getLoc();
		for (Handler h: this.handlers) {
			h.position();
		}
	}
	
	@Override
	public String getID() {
		return ResizeAttributes.ID;
	}

}
