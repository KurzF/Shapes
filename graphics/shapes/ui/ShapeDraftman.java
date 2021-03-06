package graphics.shapes.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.util.Iterator;

import graphics.shapes.SCircle;
import graphics.shapes.SCollection;
import graphics.shapes.SRectangle;
import graphics.shapes.SText;
import graphics.shapes.Shape;
import graphics.shapes.ShapeVisitor;
import graphics.shapes.attributes.Attributes;
import graphics.shapes.attributes.ColorAttributes;
import graphics.shapes.attributes.FontAttributes;
import graphics.shapes.attributes.RotationAttributes;
import graphics.shapes.attributes.SelectionAttributes;

public class ShapeDraftman implements ShapeVisitor {

	public static ColorAttributes DEFAULTCOLORATTRIBUTES = new ColorAttributes();
	public static FontAttributes DEFAULTFONTATTRIBUTES = new FontAttributes();
	private static int handler_size = 5;
	private Graphics2D g;
	
	public ShapeDraftman(Graphics g) {
		this.g = (Graphics2D) g;
	}
	
	
	private void drawSelection(Shape s) {
		
		SelectionAttributes sa = (SelectionAttributes) s.getAttributes(Attributes.SelectionID);
		if(sa != null && sa.isSelected()) {
			Rectangle r = s.getBounds();
			this.g.setColor(Color.BLACK);
			this.g.drawRect(r.x-ShapeDraftman.handler_size, r.y-ShapeDraftman.handler_size, ShapeDraftman.handler_size, ShapeDraftman.handler_size );
			this.g.drawRect(r.x+r.width, r.y+r.height, ShapeDraftman.handler_size, ShapeDraftman.handler_size);
		}
	}
	
	
	
	public void visitRectangle(SRectangle rect) {
		ColorAttributes ca = (ColorAttributes) rect.getAttributes(Attributes.ColorID);
		RotationAttributes rot = (RotationAttributes) rect.getAttributes(Attributes.RotationID);
		if(ca == null) { 
			ca = ShapeDraftman.DEFAULTCOLORATTRIBUTES; 
		}
		if (rot == null) {
			rot = new RotationAttributes();
		}
		AffineTransform at = g.getTransform();
		Point center = rect.getCenter();
		this.g.rotate(Math.toRadians(rot.getAngle()),center.x, center.y);
		if(ca.filled()) { 
			this.g.setColor(ca.filledColor());
			this.g.fill(rect.getRect());
		}
		if(ca.stroked()) {
			this.g.setColor(ca.strokedColor());
		}
		this.g.draw(rect.getRect());
		this.drawSelection(rect);
		this.g.setTransform(at); //remove transform
	}
	
	
	
	public void visitCollection(SCollection c) {
		Iterator<Shape> i = c.iterator();
		Point location=c.getLoc();
		RotationAttributes rot = (RotationAttributes) c.getAttributes(Attributes.RotationID);
		if (rot == null) {
			rot = new RotationAttributes();
		}
		AffineTransform at = g.getTransform();
		Point center = c.getCenter();
		g.rotate(Math.toRadians(rot.getAngle()),center.x, center.y);
		while(i.hasNext()) {
			i.next().accept(this);
		}
		this.drawSelection(c);
		this.g.setTransform(at);
	}
	
	public void visitCircle(SCircle c) {
		ColorAttributes ca = (ColorAttributes) c.getAttributes(Attributes.ColorID);
		if(ca == null) { ca = ShapeDraftman.DEFAULTCOLORATTRIBUTES; }
		if(ca.filled()) {
			this.g.setColor(ca.filledColor());
			this.g.fillOval(c.getLoc().x,c.getLoc().y, c.getRadius()*2, c.getRadius()*2);
		}
		if(ca.stroked()) {
			this.g.setColor(ca.strokedColor());
			this.g.drawOval(c.getLoc().x,  c.getLoc().y,  c.getRadius()*2, c.getRadius()*2);
		}
		drawSelection(c);
	}
	
	public void visitText(SText t) {
		Point location=t.getLoc();
		RotationAttributes rot = (RotationAttributes) t.getAttributes("rotation");
		ColorAttributes ca = (ColorAttributes) t.getAttributes(Attributes.ColorID);
		if(ca == null) { ca = ShapeDraftman.DEFAULTCOLORATTRIBUTES; }
		if (rot == null) {
			rot= new RotationAttributes();
		}
		
		AffineTransform at = g.getTransform();
		Point center = t.getCenter();
		g.rotate(Math.toRadians(rot.getAngle()),center.x, center.y);
		if(ca.filled()) { 
			this.g.setColor(ca.filledColor());
			this.g.fill(t.getBounds());
		}
		if(ca.stroked()) {
			this.g.setColor(ca.strokedColor());
			this.g.draw(t.getBounds());
		}
		FontAttributes fa = (FontAttributes) t.getAttributes(Attributes.FontID);
		if(fa == null) { fa = ShapeDraftman.DEFAULTFONTATTRIBUTES; }
		this.g.setColor(fa.fontColor());
		this.g.setFont(fa.font());
		this.g.drawString(t.getText(), t.getLoc().x, t.getLoc().y);
		this.drawSelection(t);
		this.g.setTransform(at);
	}
	
	public FontRenderContext getFontRenderContext() {
		return g.getFontRenderContext();
	}
}