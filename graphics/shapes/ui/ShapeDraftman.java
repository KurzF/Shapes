package graphics.shapes.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Iterator;

import graphics.shapes.SCircle;
import graphics.shapes.SCollection;
import graphics.shapes.SRectangle;
import graphics.shapes.SText;
import graphics.shapes.Shape;
import graphics.shapes.ShapeVisitor;
import graphics.shapes.attributes.ColorAttributes;
import graphics.shapes.attributes.FontAttributes;
import graphics.shapes.attributes.ResizeAttributes;
import graphics.shapes.attributes.SelectionAttributes;
import graphics.shapes.handlers.Handler;

public class ShapeDraftman implements ShapeVisitor {

	public static ColorAttributes DEFAULTCOLORATTRIBUTES = new ColorAttributes();
	public static FontAttributes DEFAULTFONTATTRIBUTES = new FontAttributes();
	static private int handler_size = 5;
	private Graphics2D g;
	
	public ShapeDraftman(Graphics g) {
		this.g = (Graphics2D) g;
	}
	
	private void drawSelection(Shape s) {
		SelectionAttributes sa = (SelectionAttributes) s.getAttributes(SelectionAttributes.ID);
		if(sa != null && sa.isSelected()) {
			Rectangle r = s.getBound();
			this.g.setColor(Color.BLACK);
			//this.g.drawRect(r.x-ShapeDraftman.handler_size, r.y-ShapeDraftman.handler_size, ShapeDraftman.handler_size, ShapeDraftman.handler_size );
			//this.g.drawRect(r.x-ShapeDraftman.handler_size, r.y+r.height, ShapeDraftman.handler_size, ShapeDraftman.handler_size);
			//this.g.drawRect(r.x+r.width, r.y+r.height, ShapeDraftman.handler_size, ShapeDraftman.handler_size);
			//this.g.drawRect(r.x+r.width, r.y-ShapeDraftman.handler_size, ShapeDraftman.handler_size, ShapeDraftman.handler_size);
			if(s.getAttributes(ResizeAttributes.ID)!=null) {
				Iterator<Handler> i = ((ResizeAttributes)s.getAttributes(ResizeAttributes.ID)).iterator();
				while(i.hasNext()) {
					i.next().accept(this);
				}
			};
		}
	}
	
	@Override
	public void visitRectangle(SRectangle rect) {
		ColorAttributes ca = (ColorAttributes) rect.getAttributes(ColorAttributes.ID);
		if(ca == null) { ca = ShapeDraftman.DEFAULTCOLORATTRIBUTES; }
		if(ca.filled()) { 
			this.g.setColor(ca.filledColor());
			this.g.fill(rect.getRect());
		}
		if(ca.stroked()) {
			this.g.setColor(ca.strokedColor());
			this.g.draw(rect.getRect());
		}
		this.drawSelection(rect);
	}
	
	public void visitCollection(SCollection c) {
		Iterator<Shape> i = c.iterator();
		while(i.hasNext()) {
			i.next().accept(this);
		}
		this.drawSelection(c);
	}
	
	public void visitCircle(SCircle c) {
		ColorAttributes ca = (ColorAttributes) c.getAttributes(ColorAttributes.ID);
		if(ca == null) { ca = ShapeDraftman.DEFAULTCOLORATTRIBUTES; }
		if(ca.filled()) {
			this.g.setColor(ca.filledColor());
			this.g.fillOval(c.getLoc().x,c.getLoc().y, c.getDiameter(), c.getDiameter());
		}
		if(ca.stroked()) {
			this.g.setColor(ca.strokedColor());
			this.g.drawOval(c.getLoc().x,  c.getLoc().y,  c.getDiameter(), c.getDiameter());
		}
		this.drawSelection(c);
	}
	
	public void visitText(SText t) {
			
		ColorAttributes ca = (ColorAttributes) t.getAttributes(ColorAttributes.ID);
		if(ca == null) { ca = ShapeDraftman.DEFAULTCOLORATTRIBUTES; }
		if(ca.filled()) { 
			this.g.setColor(ca.filledColor());
			this.g.fill(t.getBound());
		}
		if(ca.stroked()) {
			this.g.setColor(ca.strokedColor());
			this.g.draw(t.getBound());
		}
		FontAttributes fa = (FontAttributes) t.getAttributes(FontAttributes.ID);
		if(fa == null) { fa = ShapeDraftman.DEFAULTFONTATTRIBUTES; }
		this.g.setColor(fa.fontColor());
		this.g.setFont(fa.font);
		this.g.drawString(t.getText(), t.getLoc().x, t.getLoc().y);
		
		this.drawSelection(t);
	}
}