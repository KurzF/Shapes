package graphics.shapes.handlers;

import java.awt.Point;
import java.awt.Rectangle;

import graphics.shapes.SRectangle;
import graphics.shapes.Shape;
import graphics.shapes.attributes.ColorAttributes;
import graphics.shapes.attributes.ResizeAttributes;
import graphics.shapes.ui.ShapeController;

public class RHTopLeft extends Handler {

	public RHTopLeft(Shape master) {
		super(master);
		Point p = this.getMaster().getLoc();
		this.setShape(new SRectangle(p,ResizeAttributes.size, ResizeAttributes.size));
		this.setLoc(p);
	}
	
	@Override
	public void modifier(int dx, int dy) {
		this.getMaster().translate(dx, dy);
		this.getMaster().grow(-dx, -dy);
	}

	@Override
	public void setLoc(Point p) {
		Point loc = (Point)p.clone();
		loc.translate(-ResizeAttributes.size, -ResizeAttributes.size);
		this.getShape().setLoc(loc);
		
	}
}
