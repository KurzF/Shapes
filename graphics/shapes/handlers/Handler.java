package graphics.shapes.handlers;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;

import graphics.shapes.Shape;
import graphics.shapes.ShapeVisitor;
import graphics.shapes.attributes.ColorAttributes;
import graphics.shapes.ui.ShapeController;

public abstract class Handler extends Shape{
	
	protected Shape master;
	protected Shape shape; //the handler shape
	
	public Handler(Shape master) {
		this.master = master;
	}
	
	public abstract void modifier(int dx, int dy);
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
	
	public void accept(ShapeVisitor sv) {
		this.shape.accept(sv);
	}

	@Override
	public void grow(int dx, int dy) {
		// TODO Auto-generated method stub
		
	}
	
}
