package graphics.shapes.handlers;

import java.awt.Point;

import graphics.shapes.SRectangle;
import graphics.shapes.Shape;
import graphics.shapes.attributes.ColorAttributes;
import graphics.shapes.attributes.ResizeAttributes;

public class RHBottomRight extends Handler {

	public RHBottomRight(Shape master) {
		super(master);
		Point p = this.getMaster().getLoc();
		this.setShape(new SRectangle(p, ResizeAttributes.size, ResizeAttributes.size));
		this.setLoc(p);
		
	}
	
	@Override
	public void modifier(int dx, int dy) {
		this.getMaster().grow(dx, dy);
	}

	@Override
	public void setLoc(Point p) {
		Point loc = (Point)p.clone();
		loc.translate(this.getMaster().getBound().width, this.getMaster().getBound().height);
		this.getShape().setLoc(loc);
	}

}
