package graphics.shapes.handles;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

import graphics.shapes.SRectangle;
import graphics.shapes.Shape;
import graphics.shapes.attributes.Attributes;
import graphics.shapes.attributes.ColorAttributes;
import graphics.shapes.attributes.ResizeAttributes;
import graphics.shapes.attributes.RotationAttributes;

/**
 * An resize Handler place at the bottom right corner of master
 * @author kurzf
 *
 */
public class RHBottomRight extends Handle {

	public RHBottomRight(Shape master) {
		super(master);
		this.setShape(new SRectangle(new Point(0,0), ResizeHandles.HANDLE_SIZE, ResizeHandles.HANDLE_SIZE, false));
		this.position();
		
	}

	@Override
	public void modifier(Point loc) {
		//this.getMaster().translate(0, 0);
		//this.getMaster().grow(dx, dy);
		
		RotationAttributes rot = (RotationAttributes) this.getMaster().getAttributes(Attributes.RotationID);
		Rectangle rect = this.master.getBounds();
		Point last_pos = rect.getLocation();
		last_pos.translate(rect.width, rect.height);
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
							  (int)((dx*Math.sin(alpha)+dy*(Math.cos(alpha)-1))/2));
		
		this.master.setWidth((int)(loc.getX())-rect.x);
		this.master.setHeight((int)(loc.getY())-rect.y);
		System.out.println("modifier ");
	}
	
	@Override
	public void position() {
		Point loc = this.master.getLoc();
		loc.translate(this.master.getBounds().width, this.master.getBounds().height);
		this.shape.setLoc(loc);
	}
	
	public void setLoc(Point p) {
		System.out.println("modifier call1");
		Point2D loc = (Point2D) p.clone();
		RotationAttributes rot = (RotationAttributes)this.master.getAttributes(Attributes.RotationID);
		if(rot != null && rot.getAngle() != 0) {
			AffineTransform at = new AffineTransform();
			Point center = this.master.getCenter();
			at.rotate(Math.toRadians(-rot.getAngle()), center.x, center.y);
			loc = at.transform(loc, null);
		}
		this.modifier(new Point((int)loc.getX(), (int)loc.getY()));
		System.out.println("modifier call");
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
	public int getId() {
		return ResizeHandles.BR;
	}

	@Override
	public Shape clone() {
		// TODO Auto-generated method stub
		return null;
	}
}
