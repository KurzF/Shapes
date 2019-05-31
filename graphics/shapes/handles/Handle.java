package graphics.shapes.handles;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Point2D;

import graphics.shapes.SRectangle;
import graphics.shapes.Shape;
import graphics.shapes.ShapeVisitor;
import graphics.shapes.attributes.ColorAttributes;
import graphics.shapes.ui.ShapeController;

/**
 * 
 * @author kurzf
 * Allow to modify master thanks to the callback modifier()
 * 
 */
public abstract class Handle extends Shape{


	protected Shape master;
	protected Shape shape; //the handler shape
	
	public Handle(Shape master) {
		super();
		this.master = master;
		this.shape = new SRectangle(new Point(0,0),10,10, false);
	}
	
	public abstract void modifier(Point loc);
	public abstract void position();
	
	public Point getLoc() {
		return this.shape.getLoc();
	}
	
	public void translate(int x, int y) {
		this.shape.translate(x, y);
	}
	
	public Rectangle getBounds() {
		return this.shape.getBounds();
	}
	
	public Point getCenter() {
		return this.shape.getCenter();
	}
	
	public Shape getShape() {
		return this.shape;
	}
	
	public void setShape(Shape s) {
		this.shape = s;
	}
	
	public Shape getMaster() {
		return this.master;
	}
	
	public abstract int getId(); //use by ShapeController
	
	public void accept(ShapeVisitor sv) {
		this.shape.accept(sv);
	}
}
