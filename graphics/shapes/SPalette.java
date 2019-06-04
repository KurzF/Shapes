package graphics.shapes;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;

import graphics.shapes.attributes.Attributes;
import graphics.shapes.attributes.ColorAttributes;

public class SPalette extends Shape {
	
	private Rectangle rect;

	public SPalette(Point p, int width, int height) {
		super();
		this.rect = new Rectangle(p);
		this.rect.width = width;
		this.rect.height = height;
	}
	
	public Rectangle getRect() {
		return this.rect;
	}
	
	public Point getLoc() {
		return this.rect.getLocation();
	}

	public void setLoc(Point loc) {
		this.rect.setLocation(loc);
	}

	public void translate(int dx, int dy) {
		//SPalette must not be translated
	}

	public Rectangle getBounds() {
		return this.rect.getBounds();
	}

	public void accept(ShapeVisitor visitor) {
		visitor.visitPalette(this);
	}

	public Color getColor() {
		return ((ColorAttributes) this.getAttributes(Attributes.ColorID)).filledColor();
	}

	public void setColor(Color color) {
		((ColorAttributes) this.getAttributes(Attributes.ColorID)).setFilledColor(color);
	}

	@Override
	public Point getCenter() {
		// TODO Auto-generated method stub
		return null;
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
	public Shape clone() {
		// TODO Auto-generated method stub
		return null;
	}

}
