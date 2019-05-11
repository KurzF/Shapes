package graphics.shapes.ui;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.util.Iterator;

import graphics.ui.Controller;
import graphics.ui.View;
import graphics.shapes.Shape;
import graphics.shapes.SCollection;
import graphics.shapes.attributes.ResizeAttributes;
import graphics.shapes.attributes.RotationAttributes;
import graphics.shapes.attributes.SelectionAttributes;
import graphics.shapes.handlers.Handler;

public class ShapeController extends Controller {
	
	private int state;
	private Point press_position;//to drag shape
	private Shape target;
	public ShapeController(Object model, View view) {
		super(model);
		this.setView(view);
		this.state = 1;
	}
	
	public void mousePressed(MouseEvent e) {
		System.out.println("press");
		this.press_position = e.getPoint();
		if(this.state == 1) {
			this.setTarget(this.press_position);
			if(this.target == null || (!this.target.isSelected() && !(this.target instanceof Handler))) {
				this.state = 3;
			}
			else {
				this.state =1;
			}
		}
	}
	
	public void mouseReleased(MouseEvent e) {
		System.out.println("release");
		if(this.state == 3) {
			this.state = 1;
		}
	}
	
	public void mouseClicked(MouseEvent e) {
		System.out.println("click");
		if(this.state == 1) {
			if(!e.isShiftDown()) {
				this.unselectAll();
			}
			
			if(this.target != null) {
				SelectionAttributes sa;
				if(this.target instanceof Handler) {
					Handler h = (Handler) this.target;
					sa = (SelectionAttributes)h.getMaster().getAttributes(SelectionAttributes.ID);
					if(sa != null) {
						sa.toggleSelection();
						this.getView().repaint();
					}
				}
				sa = (SelectionAttributes)this.target.getAttributes(SelectionAttributes.ID);
				if(sa != null) {
					sa.toggleSelection();
					this.getView().repaint();
				}
			}
		}
		this.state = 1;
	}
	
	public void mouseEntered(MouseEvent e) {
		System.out.println("enter");
	}
	
	public void mouseExited(MouseEvent e) {
		System.out.println("exit");
	}
	
	public void mouseMoved(MouseEvent e) {
		System.out.println("move");
	}
	
	public void mouseDragged(MouseEvent e) {
		System.out.println("drag");
		int dx = e.getX() - this.press_position.x;
		int dy = e.getY() - this.press_position.y;
		this.press_position = e.getPoint();
		if(this.state == 1) {
			if(this.target instanceof Handler) {
				((Handler)this.target).modifier(dx, dy);
			}
			else {
				this.translateSelected(dx, dy);
			}
		}
		if(this.state == 3) {
			((Shape)this.getModel()).translate(dx, dy);
		}
		this.getView().repaint();
	}
	
	public void keyPressed(KeyEvent e) {
		System.out.println("press");
	    int keyCode = e.getKeyCode();
	    switch( keyCode ) { 
	        case KeyEvent.VK_UP:
	            // handle up 
	            break;
	        case KeyEvent.VK_DOWN:
	            // handle down 
	            break;
	        case KeyEvent.VK_LEFT:
	        	rotateSelected(+1);
	            this.getView().repaint();
	            break;
	        case KeyEvent.VK_RIGHT :
	            rotateSelected(-1);
	            this.getView().repaint();
	            break;
	     }
	} 
	private void translateSelected(int x, int y) {
		if(this.getModel() == null) { return; }
		Iterator<Shape> i = ((SCollection)this.getModel()).iterator();
		while(i.hasNext()) {
			Shape s = i.next();
			if(s.isSelected()) {
				s.translate(x, y);
			}
		}
		this.getView().repaint();
	}
	
	public void rotateSelected(int dtheta) {
		Iterator<Shape> it= ((SCollection) this.getModel()).iterator();
		while(it.hasNext()) {
			Shape s = it.next();
			if(s.isSelected()) {
				RotationAttributes rot = (RotationAttributes) s.getAttributes(Attributes.RotationID);
				if(rot != null) {
					rot.add(dtheta);
				}
			}
		}	
	}

	private Shape getTarget() {
		return this.target;
	}
	
	private void setTarget(Point p) {
		if(this.getModel() == null) { return null; }
		Iterator<Shape> i = ((SCollection)this.getModel()).iterator();
		while(i.hasNext()) {
			Shape s = i.next();
			RotationAttributes rot = (RotationAttributes) s.getAttributes(Attributes.RotationID);
			if (rot == null) {
				rot= new RotationAttributes();
			}
			if(rot.getAngle()==0){
				if(s.getBounds().contains(p)) {
				return s;
				}
			}
			else{
				AffineTransform tx = new AffineTransform();
				Point center = s.getCenter();
				tx.rotate(Math.toRadians(-rot.getAngle()),center.x, center.y);
				if(s.getBounds().contains(tx.transform(p,null))) {
					return s;
				}
			}
		}
		return null;
	}

	private void unselectAll() {
		if(this.getModel() == null) { return; }
		Iterator<Shape> i = ((SCollection)this.getModel()).iterator();
		while(i.hasNext()) {
			SelectionAttributes sa = (SelectionAttributes)i.next().getAttributes(SelectionAttributes.ID);
			if(sa != null) { sa.unselect(); }
		}
		this.getView().repaint();
	}
}
