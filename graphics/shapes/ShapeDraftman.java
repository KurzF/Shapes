package graphics.shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import graphics.shapes.attributes.ColorAttributes;
import graphics.shapes.attributes.SelectionAttributes;

public class ShapeDraftman implements ShapeVisitor {

	public ColorAttributes DEFAULTCOLORATTRIBUTES = new ColorAttributes();
	static private int handler_size = 5;
	private Graphics2D g;
	
	public ShapeDraftman(Graphics g) {
		this.g = (Graphics2D) g;
	}
	
	private void drawSelection(Rectangle rect) {
		System.out.println(rect.x);
		this.g.setColor(Color.BLACK);
		this.g.drawRect(rect.x-ShapeDraftman.handler_size, rect.y-ShapeDraftman.handler_size, ShapeDraftman.handler_size, ShapeDraftman.handler_size );
		this.g.drawRect(rect.x+rect.width, rect.y+rect.height, this.handler_size, this.handler_size);
	}
	
	@Override
	public void visitRectangle(SRectangle rect) {
		SelectionAttributes sa = (SelectionAttributes) rect.getAttributes("selection");
		if(sa != null && sa.isSelected()) {
			this.drawSelection(rect.getRect());
		}
		ColorAttributes ca = (ColorAttributes) rect.getAttributes("color");
		if(ca == null) { ca = this.DEFAULTCOLORATTRIBUTES; }
		if(ca.filled()) { 
			this.g.setColor(ca.filledColor());
			this.g.fill(rect.getRect());
		}
		if(ca.stroked()) {
			this.g.setColor(ca.strokedColor());
			this.g.draw(rect.getRect());
		}
	}
}