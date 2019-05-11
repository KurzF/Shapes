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
import graphics.shapes.attributes.Attributes;
import graphics.shapes.attributes.RotationAttributes;
import graphics.shapes.attributes.SelectionAttributes;

public class ShapeController extends Controller {
	
	private int state;
	private Point press_position;//to drag shape
	
	public ShapeController(Object model, View view) {
		super(model);
		this.setView(view);
		this.state = 1;
	}
	
	public void mousePressed(MouseEvent e) {
		System.out.println("press");
		this.press_position = e.getPoint();
		if(this.state == 1) {
			Shape s = this.getTarget(this.press_position);
			if(s == null || !s.isSelected()) {
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
			Shape s = this.getTarget(this.press_position);
			if(!e.isShiftDown()) {
				this.unselectAll();
			}
			SelectionAttributes sa;
			if(s != null) {
				sa = (SelectionAttributes)s.getAttributes(Attributes.SelectionID);
				if(sa != null) {
					sa.toggleSelection();
					this.getView().repaint();
				}
			}
		}
		this.state = 1;
	}
	
	public void mouseExit(MouseEvent e) {
		System.out.println("exit");
	}
	
	public void mouseMoved(MouseEvent e) {
		System.out.println("move");
	}
	
	public void mouseDragged(MouseEvent e) {
		System.out.println("drag");
		if(this.state ==1) {
			int dx = e.getX() - this.press_position.x;
			int dy = e.getY() - this.press_position.y;
			this.press_position = e.getPoint();
			this.translateSelected(dx, dy);
		}
	}
	
	public void keyTyped(KeyEvent evt) {
		System.out.println("key");
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
	        	rotateSelected(-1);
	            this.getView().repaint();
	            break;
	        case KeyEvent.VK_RIGHT :
	            rotateSelected(+1);
	            this.getView().repaint();
	            break;
	     }
	} 
	
	private void translateSelected(int x, int y) {
		Iterator<Shape> i = ((SCollection)this.getModel()).iterator();
		while(i.hasNext()) {
			Shape s = i.next();
			SelectionAttributes sa = (SelectionAttributes)s.getAttributes(Attributes.SelectionID);
			if(sa != null && sa.isSelected()) {
				s.translate(x, y);
			}
		}
		this.getView().repaint();
	}
	
	
	public void rotateSelected(int dtheta) {
		SCollection model = (SCollection) this.getModel();
		Shape s;
		
		for (Iterator<Shape> it = model.iterator(); it.hasNext();) {
			s = it.next();
			if ((RotationAttributes) s.getAttributes(Attributes.RotationID) == null) {
				s.addAttributes(new RotationAttributes());
			}
			if (((SelectionAttributes) s.getAttributes(Attributes.SelectionID)).isSelected()) {
				if (dtheta==1) {
					((RotationAttributes) s.getAttributes(Attributes.RotationID)).incrAngle();
				}
				if (dtheta==-1) {
					((RotationAttributes) s.getAttributes(Attributes.RotationID)).decrAngle();
				}
			}
		}	
	}
	
	
	private Shape getTarget(Point p) {
		if(this.getModel() == null) { return null; }
		Iterator<Shape> i = ((SCollection)this.getModel()).iterator();
		while(i.hasNext()) {
			Shape s = i.next();
			RotationAttributes rot = (RotationAttributes) s.getAttributes(Attributes.RotationID);
			if (rot == null) {
				rot= new RotationAttributes();
			}
			if(rot.getAngle()==0){
				if(s.getBound().contains(p)) {
				return s;
				}
			}
			else{
				AffineTransform tx = new AffineTransform();
				tx.rotate(Math.toRadians(-rot.getAngle()),s.getLoc().x+s.getBound().width/2,s.getLoc().y+s.getBound().height/2);
				if(s.getBound().contains(tx.transform(p,null))) {
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
			SelectionAttributes sa = (SelectionAttributes)i.next().getAttributes(Attributes.SelectionID);
			if(sa != null) { sa.unselect(); }
		}
		this.getView().repaint();
	}
}