package graphics.shapes.ui;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.util.Iterator;

import graphics.ui.Controller;
import graphics.ui.View;
import graphics.shapes.Shape;
import graphics.shapes.SCollection;
import graphics.shapes.attributes.Attributes;
import graphics.shapes.attributes.ResizeAttributes;
import graphics.shapes.attributes.RotationAttributes;
import graphics.shapes.attributes.SelectionAttributes;
import graphics.shapes.handles.Handle;
import graphics.shapes.handles.ResizeHandles;

/**
 * The controller of ShapeView
 *
 */
public class ShapeController extends Controller{
	
	private int state;
	private Point press_position;//to drag shape
	private Shape target;
	private int index; //target (master in case of handler) index in SCollection
	
	private SCollection clipboard;
	private Shape copy; //All GUI control must copy the modified shape in this;
	
	public ShapeController(Object model, View view) {
		super(model);
		this.setView(view);
		this.state = 1;
		this.clipboard = new SCollection();
	}
	
	public void mousePressed(MouseEvent e) {
		System.out.println("press"+this.state);
		this.press_position = e.getPoint();
		if(this.state == 1) {
			this.setTarget(this.press_position);
			if(this.target == null || (!this.target.isSelected() && !(this.target instanceof Handle))) {
				this.state = 3;
			}
			else {
				this.state =1;
			}
		}
	}
	
	public void mouseReleased(MouseEvent e) {
		System.out.println("release"+this.state);
		if(this.state == 3) {
			this.state = 1;
		}
	}
	
	public void mouseClicked(MouseEvent e) {
		System.out.println("press" + this.state);
		if(this.state == 1) {
			if(!e.isShiftDown()) {
				this.unselectAll();
			}
			this.setTarget(e.getPoint());
			if(this.target != null) {
				SelectionAttributes sa;
				if(this.target instanceof Handle) {
					Handle h = (Handle) this.target;
					sa = (SelectionAttributes)h.getMaster().getAttributes(Attributes.SelectionID);
					if(sa != null) {
						sa.toggleSelection();
						this.getView().repaint();
					}
				}
				sa = (SelectionAttributes)this.target.getAttributes(Attributes.SelectionID);
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
	
	}
	
	public void mouseDragged(MouseEvent e) {
		System.out.println("drag"+this.state);
		Point movement = new Point(e.getX() - this.press_position.x, e.getY() - this.press_position.y);
		System.out.println(movement);
		this.press_position = e.getPoint();
		if(this.state == 1) {
			if(this.target instanceof Handle) {
				/*ResizeAttributes ra = (ResizeAttributes)this.copy.getAttributes(Attributes.ResizeID);
				if(ra != null && ra.getResizable() && this.copy.getResizeHandles()!=null) {
					
					Handle h = this.copy.getResizeHandles().getHandle(((Handle)this.target).getId());
					if(h != null) {
						h.setLoc(e.getPoint());
					}
					((SCollection)this.getModel()).setCollection(this.index,this.copy);
				}*/
				ResizeAttributes ra = (ResizeAttributes)this.copy.getAttributes(Attributes.ResizeID);
				if(ra != null && ra.getResizable()) {
					RotationAttributes rot = (RotationAttributes)((Handle)this.target).getMaster().getAttributes(Attributes.RotationID);
					if(rot != null) {
						Point center = ((Handle)this.target).getMaster().getCenter();
						Point2D press_rotate = this.press_position;
						AffineTransform at = new AffineTransform();
						at.rotate(Math.toRadians(-rot.getAngle()), center.x, center.y);
						at.transform(press_rotate, press_rotate);
						((Handle)this.target).modifier(new Point((int)press_rotate.getX(), (int)press_rotate.getY()));
					}
				}
				
			}
			else {
				this.translateSelected((int)movement.getX(), (int)movement.getY());
			}
		}
		
		if(this.state == 3) {
			((Shape)this.getModel()).translate((int)movement.getX(), (int)movement.getY());
		}
		this.getView().repaint();
		
	}
	
	public void keyPressed(KeyEvent e) {
		System.out.println("press");
	    int keyCode = e.getKeyCode();
	    switch( keyCode ) { 
	        case KeyEvent.VK_UP:
	            // handle up 
	        	System.out.println(this.getModel());
	            break;
	        case KeyEvent.VK_DOWN:
	            // handle down 
	            break;
	        case KeyEvent.VK_LEFT:
	        	this.rotateSelected(-1);
	            this.getView().repaint();
	            break;
	        case KeyEvent.VK_RIGHT :
	            this.rotateSelected(1);
	            this.getView().repaint();
	            break;
	        case KeyEvent.VK_C:
	        	if(e.isControlDown()) {
	        		this.copy();
	        	}
	        	break;
	        case KeyEvent.VK_V:
	        	if(e.isControlDown()) {
	        		this.paste();
	        	}
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
		System.out.println(this.target+":"+this.copy );
		return this.target;
	}
	
	private void setTarget(Point p) {
		if(this.getModel() == null) {
			this.target = null;
			this.copy = null;
			this.index = -1;
			return;
		}
		Iterator<Shape> i = ((SCollection)this.getModel()).iterator();
		int index = -1;
		while(i.hasNext()) {
			Shape s = i.next();
			index++;
			RotationAttributes rot = (RotationAttributes) s.getAttributes(Attributes.RotationID);
			if (rot == null) {
				rot= new RotationAttributes();
			}
			if(rot.getAngle()==0){
				ResizeAttributes ra = ((ResizeAttributes)s.getAttributes(Attributes.ResizeID));
				if(ra != null && ra.getResizable()) {
					for(int k=0; k<ResizeHandles.LENGTH; k++) {
						Shape hand = s.getResizeHandles().getHandle(k);
						if(hand.getBounds().contains(p)) {
							this.target = hand;
							this.copy = ((Handle)hand).getMaster().clone();
							this.index = index;
							return;
						}
					}
				}
				if(s.getBounds().contains(p)) {
					this.target = s;
					this.copy = s.clone();
					this.index = index;
					return;
				}
			}
			else{
				AffineTransform tx = new AffineTransform();
				Point center = s.getCenter();
				tx.rotate(Math.toRadians(-rot.getAngle()),center.x, center.y);
				ResizeAttributes ra = ((ResizeAttributes)s.getAttributes(Attributes.ResizeID));
				if(ra != null && ra.getResizable()) {
					for(int k=0; k<ResizeHandles.LENGTH; k++) {
						Shape hand = s.getResizeHandles().getHandle(k);
						if(hand.getBounds().contains(tx.transform(p,null))) {
							this.target = hand;
							this.copy = ((Handle)hand).getMaster().clone();
							this.index = index;
							return;
						}
					}
				}
				if(s.getBounds().contains(tx.transform(p,null))) {
					this.target = s;
					this.copy = s.clone();
					this.index = index;
					return;
				}
			}
		}
		System.out.println(this.target);
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
	
	private void copy() {
		Iterator<Shape> i = ((SCollection)this.getModel()).iterator();
		while(i.hasNext()) {
			Shape s = i.next();
			SelectionAttributes sa = (SelectionAttributes)s.getAttributes(Attributes.SelectionID);
			if(sa != null && sa.isSelected()) {
				this.clipboard.add(s.clone());
			}
		}
	}
	
	private void paste() {
		Iterator<Shape> i = this.clipboard.iterator();
		while(i.hasNext()) {
			Shape s = i.next();
			((SCollection)this.getModel()).add(s);
		}
		this.clipboard=new SCollection();
	}
}
