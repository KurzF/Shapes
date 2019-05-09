package graphics.shapes.ui;

import java.awt.Point;
import java.awt.event.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.Iterator;

import graphics.ui.Controller;
import graphics.ui.View;

import graphics.shapes.Shape;
import graphics.shapes.SCollection;
import graphics.shapes.attributes.ResizeAttributes;
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
	
	public void keyPressed(KeyEvent evt) {
		System.out.println(evt.getKeyChar());
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
	
	private Shape getTarget() {
		return this.target;
	}
	
	private void setTarget(Point p) {
		if(this.getModel() == null) { this.target = null; return; }
		Iterator<Shape> i = ((SCollection)this.getModel()).iterator();
		while(i.hasNext()) {
			Shape s = i.next();
			ResizeAttributes ra = ((ResizeAttributes)s.getAttributes(ResizeAttributes.ID));
			if(ra != null) {
				Iterator<Handler> h = ra.iterator();
				while(h.hasNext()) {
					Shape hand = h.next();
					if(hand.getBound().contains(p)) {
						this.target = hand;
						return;
					}
				}
			}
			if(s.getBound().contains(p)) {
				this.target = s;
				return;
			}
		}
		this.target = null;
		System.out.println(this.target);
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
