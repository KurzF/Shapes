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
import graphics.shapes.ui.ShapeController;

/**
 * An resize Handler place at the top left corner of master
 * @author kurzf
 *
 */
public class RHTopLeft extends Handle {

	public RHTopLeft(Shape master) {
		super(master);
		Point p = this.getMaster().getLoc();
		this.setShape(new SRectangle(p,ResizeHandles.HANDLE_SIZE, ResizeHandles.HANDLE_SIZE, false));
		this.position();
	}

	@Override
	public void modifier(Point loc) {
		//this.getMaster().grow(-(loc.x - this.getMaster().getBounds().x), -(loc.y - this.getMaster().getBounds().y));
		//this.getMaster().setLoc(loc);
		RotationAttributes rot = (RotationAttributes) this.getMaster().getAttributes(Attributes.RotationID);
		Rectangle rect = this.master.getBounds();
		Point last_pos = rect.getLocation();
		int dx = (int)(loc.getX())-last_pos.x;
		int dy = (int)(loc.getY())-last_pos.y;
		double alpha;
		if(rot != null)
		{
			alpha = Math.toRadians(rot.getAngle());
		} else {
			alpha = 0;
		}
		this.master.translate((int)((dx*(Math.cos(alpha)+1)-dy*Math.sin(alpha))/2),
							  (int)((dx*Math.sin(alpha)+dy*(Math.cos(alpha)+1))/2));
		
		this.master.setWidth(rect.width - dx);
		this.master.setHeight(rect.height - dy);	
	}
	
	@Override
	public void position() {
		Point loc = this.master.getLoc();
		loc.translate(-this.shape.getBounds().width, -this.shape.getBounds().height);
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
		this.modifier((Point)loc);
	}

	public void setWidth(int width) {
		// TODO Auto-generated method stub
		
	}

	public void setHeight(int height) {
		// TODO Auto-generated method stub
		
	}

	public Shape clone() {
		// TODO Auto-generated method stub
		return null;
	}

	public int getId() {
		return ResizeHandles.TL;
	}



}
