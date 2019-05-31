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
 * An resize Handler place at the bottom left corner of master
 * @author kurzf
 *
 */
public class RHBottomLeft extends Handle {

	public RHBottomLeft(Shape master) {
		super(master);
		Point p = this.getMaster().getLoc();
		this.setShape(new SRectangle(p, ResizeHandles.HANDLE_SIZE, ResizeHandles.HANDLE_SIZE, false));
		this.position();
		
	}
	
	@Override
	public void modifier(Point loc) {

		RotationAttributes rot = (RotationAttributes) this.getMaster().getAttributes(Attributes.RotationID);
		Rectangle rect = this.master.getBounds();
		Point last_pos = rect.getLocation();
		last_pos.translate(0, rect.height);
		int dx = (int)(loc.getX())-last_pos.x;
		int dy = (int)(loc.getY())-last_pos.y;

		if(rot != null)
		{
			double alpha = Math.toRadians(rot.getAngle());
			this.master.translate((int)((dx*(Math.cos(alpha)+1)-dy*Math.sin(alpha))/2),
								  (int)((dx*Math.sin(alpha)+dy*(Math.cos(alpha)-1))/2));
		}
		this.master.setWidth(rect.width - dx);
		this.master.setHeight(rect.height + dy);	
		
		/*Point anchor = this.getMaster().getLoc();
		anchor.translate(this.getMaster().getBounds().width, 0);
		Rectangle rect = this.master.getBounds();
		Point last_pos = rect.getLocation();
		last_pos.translate(0, rect.height);
		int dx = (int)(loc.getX())-last_pos.x;
		int dy = (int)(loc.getY())-last_pos.y;
		RotationAttributes rot = (RotationAttributes) this.getMaster().getAttributes(Attributes.RotationID);
		if(rot != null && rot.getAngle() != 0) {
			AffineTransform at = new AffineTransform();
			Point center = this.getMaster().getCenter();
			at.rotate(Math.toRadians(rot.getAngle()), center.x, center.y);
			Point2D anchor_position = at.transform(anchor, null);
			this.master.setWidth(rect.width-dx);
			this.master.setHeight(rect.height+dy);
			anchor = this.master.getLoc();
			anchor.translate(this.getMaster().getBounds().width, 0);
			center = this.getMaster().getCenter();
			at.setToIdentity();
			at.rotate(Math.toRadians(rot.getAngle()), center.x, center.y);
			Point2D new_anchor_position = at.transform(anchor, null);
			this.getMaster().translate((int)(anchor_position.getX()-new_anchor_position.getX()), (int)(anchor_position.getY()-new_anchor_position.getY()));
			
		} else {
			this.master.translate(dx, 0);
			this.master.setWidth(rect.width-dx);
			this.master.setHeight(rect.height+dy);

		}*/
	}
	
	public void position() {
		Point loc = this.master.getLoc();
		loc.translate(-this.shape.getBounds().width, this.master.getBounds().height);
		this.shape.setLoc(loc);
	}

	public void setLoc(Point p) {
		Point2D loc =  (Point2D) p.clone();
		RotationAttributes rot = (RotationAttributes)this.master.getAttributes(Attributes.RotationID);
		if(rot != null && rot.getAngle() != 0) {
			AffineTransform at = new AffineTransform();
			Point center = this.master.getCenter();
			at.rotate(Math.toRadians(-rot.getAngle()), center.x, center.y);
			loc = at.transform(loc, null);
		}
		this.modifier(new Point((int)loc.getX(), (int)loc.getY()));
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
		return ResizeHandles.BL;
	}

	@Override
	public Shape clone() {
		// TODO Auto-generated method stub
		return null;
	}
}
