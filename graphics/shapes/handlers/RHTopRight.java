package graphics.shapes.handlers;

import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

import graphics.shapes.SRectangle;
import graphics.shapes.Shape;
import graphics.shapes.attributes.Attributes;
import graphics.shapes.attributes.ResizeAttributes;
import graphics.shapes.attributes.RotationAttributes;

public class RHTopRight extends Handler {

	public RHTopRight(Shape master) {
		super(master);
		Point p = this.getMaster().getLoc();
		this.setShape(new SRectangle(p, ResizeAttributes.size, ResizeAttributes.size));
		this.setLoc(p);
	}
	
	@Override
	public void modifier(int dx, int dy) {
		this.getMaster().translate(0, dy);
		this.getMaster().grow(dx, -dy);
	}

	@Override
	public void position() {
		Point loc = this.master.getLoc();
		loc.translate(this.master.getBounds().width, -this.shape.getBounds().height);
		this.shape.setLoc(loc);
	}
	

	public void setLoc(Point p) {
		Point prev = this.shape.getLoc();
		Point2D loc = (Point) p.clone();
		RotationAttributes rot = (RotationAttributes)this.master.getAttributes(Attributes.RotationID);
		if(rot != null && rot.getAngle() != 0) {
			AffineTransform at = new AffineTransform();
			Point center = this.master.getCenter();
			at.rotate(Math.toRadians(-rot.getAngle()), center.x, center.y);
			at.transform(loc, loc);
		}
		this.getShape().setLoc((Point)loc);
		this.modifier((int)loc.getX()-prev.x, (int)loc.getY()-prev.y);
	}
}
