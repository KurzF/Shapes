package graphics.shapes.ui;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.Iterator;

import graphics.ui.Controller;
import graphics.ui.View;

import graphics.shapes.Shape;
import graphics.shapes.SCollection;
import graphics.shapes.attributes.Attributes;
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
			int dx = (int)(e.getX() - this.press_position.getX());
			int dy = (int)(e.getY() - this.press_position.getY());
			this.press_position = e.getPoint();
			this.translateSelected(dx, dy);
		}
	}
	
	public void keyTyped(KeyEvent evt) {
		System.out.println("key");
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
	
	private Shape getTarget(Point p) {
		Iterator<Shape> i = ((SCollection)this.getModel()).iterator();
		while(i.hasNext()) {
			Shape s = i.next();
			if(s.getBound().contains(p)) {
				return s;
			}
		}
		return null;
	}
	
	private void unselectAll() {
		Iterator<Shape> i = ((SCollection)this.getModel()).iterator();
		while(i.hasNext()) {
			SelectionAttributes sa = (SelectionAttributes)i.next().getAttributes(Attributes.SelectionID);
			if(sa != null) { sa.unselect(); }
		}
		this.getView().repaint();
	}
}
