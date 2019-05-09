package graphics.shapes.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
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
	static private int handler_size = 5;
	private Graphics2D g;
	
	public ShapeDraftman(Graphics g) {
		this.g = (Graphics2D) g;
	}
	
	
	private void drawSelection(Shape s) {
		
		SelectionAttributes sa = (SelectionAttributes) s.getAttributes(Attributes.SelectionID);
		if(sa != null && sa.isSelected()) {
			Rectangle r = s.getBound();
			this.g.setColor(Color.BLACK);
			this.g.drawRect(r.x-ShapeDraftman.handler_size, r.y-ShapeDraftman.handler_size, ShapeDraftman.handler_size, ShapeDraftman.handler_size );
			this.g.drawRect(r.x+r.width, r.y+r.height, ShapeDraftman.handler_size, ShapeDraftman.handler_size);
		}
	}
	
	
	
	public void visitRectangle(SRectangle rect) {
		Point location=rect.getLoc();
		ColorAttributes ca = (ColorAttributes) rect.getAttributes("color");
		RotationAttributes rotat = (RotationAttributes) rect.getAttributes("rotation");
		if(ca == null) { 
			ca = ShapeDraftman.DEFAULTCOLORATTRIBUTES; 
		}
		if (rotat == null) {
			rotat= new RotationAttributes();
		}
		if(ca.filled()) { 
			this.g.setColor(ca.filledColor());
			this.g.fill(rect.getRect());
		}
		if(ca.stroked()) {
			this.g.setColor(ca.strokedColor());
		}
		Rectangle r=rect.getRect();
		g.rotate(Math.toRadians(rotat.getAngle()),location.x+(r.width/2+r.width%2),location.y+(r.height/2+r.height%2));
		this.g.drawRect(r.x,r.y,r.width,r.height);
		drawSelection(rect);
		g.rotate(Math.toRadians(-rotat.getAngle()),location.x+(r.width/2+r.width%2),location.y+(r.height/2+r.height%2));
	}
	
	
	
	public void visitCollection(SCollection c) {
		Iterator<Shape> i = c.iterator();
		Point location=c.getLoc();
		RotationAttributes rotat = (RotationAttributes) c.getAttributes("rotation");
		if (rotat == null) {
			rotat= new RotationAttributes();
		}
		g.rotate(Math.toRadians(rotat.getAngle()),location.x,location.y);
		while(i.hasNext()) {
			i.next().accept(this);
		}
		this.drawSelection(c);
		g.rotate(Math.toRadians(-rotat.getAngle()),location.x,location.y);
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
		RotationAttributes rotat = (RotationAttributes) t.getAttributes("rotation");
		ColorAttributes ca = (ColorAttributes) t.getAttributes(Attributes.ColorID);
		if(ca == null) { ca = ShapeDraftman.DEFAULTCOLORATTRIBUTES; }
		if (rotat == null) {
			rotat= new RotationAttributes();
		}
		if(ca.filled()) { 
			this.g.setColor(ca.filledColor());
			this.g.fill(t.getBound());
		}
		if(ca.stroked()) {
			this.g.setColor(ca.strokedColor());
			this.g.draw(t.getBound());
		}
		g.rotate(Math.toRadians(rotat.getAngle()),location.x+(t.getBound().x/2),location.y+(t.getBound().y/2));
		if(ca.filled()) { 
			this.g.setColor(ca.filledColor());
			this.g.fill(t.getBound());
		}
		if(ca.stroked()) {
			this.g.setColor(ca.strokedColor());
			this.g.draw(t.getBound());
		}
		FontAttributes fa = (FontAttributes) t.getAttributes(Attributes.FontID);
		if(fa == null) { fa = ShapeDraftman.DEFAULTFONTATTRIBUTES; }
		this.g.setColor(fa.fontColor());
		this.g.setFont(fa.font);
		this.g.drawString(t.getText(), t.getLoc().x, t.getLoc().y);
		drawSelection(t);
		g.rotate(Math.toRadians(-rotat.getAngle()),location.x+(t.getBound().x/2),location.y+(t.getBound().y/2));
	}
	

}