package graphics.shapes.handles;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

import graphics.shapes.SRectangle;
import graphics.shapes.Shape;
import graphics.shapes.attributes.Attributes;
import graphics.shapes.attributes.ResizeAttributes;
import graphics.shapes.attributes.RotationAttributes;

/**
 * An resize Handler place at the top right corner of master
 * @author kurzf
 *
 */
public class RHTopRight extends Handle {

	public RHTopRight(Shape master) {
		super(master);
		Point p = this.getMaster().getLoc();
		this.setShape(new SRectangle(p, ResizeHandles.HANDLE_SIZE, ResizeHandles.HANDLE_SIZE, false));
		this.position();
	}
	
	@Override
	public void modifier(Point loc) {
		System.out.println(loc);		
		RotationAttributes rot = (RotationAttributes) this.getMaster().getAttributes(Attributes.RotationID);
		Rectangle rect = this.master.getBounds();
		Point last_pos = rect.getLocation();
		last_pos.translate(rect.width, 0);
		int dx = (int)(loc.getX())-last_pos.x;
		int dy = (int)(loc.getY())-last_pos.y;
		double alpha;
		if(rot != null)
		{
			alpha = Math.toRadians(rot.getAngle());
		} else {
			alpha = 0;
		}
		this.master.translate((int)((dx*(Math.cos(alpha)-1)-dy*Math.sin(alpha))/2),
							  (int)((dx*Math.sin(alpha)+dy*(Math.cos(alpha)+1))/2));
		
		this.master.setWidth(rect.width + dx);
		this.master.setHeight(rect.height - dy);	

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
		if(rot != null) {
			AffineTransform at = new AffineTransform();
			Point center = this.master.getCenter();
			at.rotate(Math.toRadians(-rot.getAngle()), center.x, center.y);
			at.transform(loc, loc);
		}
		this.modifier((Point)loc);
	}

	@Override
	public void setWidth(int width) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setHeight(int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Shape clone() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getId() {
		return ResizeHandles.TR;
	}
}
