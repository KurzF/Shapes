package graphics.shapes.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JPanel;
import javax.swing.JSlider;

import graphics.shapes.SPalette;
import graphics.shapes.Shape;
import graphics.ui.Controller;
import graphics.ui.View;
import graphics.shapes.attributes.ColorAttributes;
import graphics.shapes.SCollection;
/**
 * The view to draw shape
 */
public class ShapesView extends View {
	
	private ShapeDraftman draftman;
	private ShapesPainter painter;
	
	
	public ShapesView(Object model) {
		super(model);
		this.painter = new ShapesPainter();
		
		//ShapesView gives the ShapesPainter to the ShapesController
		((ShapeController) this.getController()).setPainter(painter);
		
		this.buildPalette();

		((ShapeController) this.getController()).setPalette();
	}
	
	public void setSlider(JSlider slider) {
		this.add(slider);
	}
	
	private void buildPalette() {
		//Palette building
		SPalette p1 = new SPalette(new Point(20,20),40,30);
		p1.addAttributes(new ColorAttributes(true,true,Color.BLACK,Color.BLACK));
		((SCollection) this.getModel()).add(p1);
		
		SPalette p2 = new SPalette(new Point(80,20),40,30);
		p2.addAttributes(new ColorAttributes(true,true,Color.WHITE,Color.BLACK));
		((SCollection) this.getModel()).add(p2);
				
		SPalette p3 = new SPalette(new Point(20,70),40,30);
		p3.addAttributes(new ColorAttributes(true,true,Color.RED,Color.BLACK));
		((SCollection) this.getModel()).add(p3);
				
		SPalette p4 = new SPalette(new Point(80,70),40,30);
		p4.addAttributes(new ColorAttributes(true,true,Color.BLUE,Color.BLACK));
		((SCollection) this.getModel()).add(p4);
				
		SPalette p5 = new SPalette(new Point(20,120),40,30);
		p5.addAttributes(new ColorAttributes(true,true,Color.ORANGE,Color.BLACK));
		((SCollection) this.getModel()).add(p5);
				
		SPalette p6 = new SPalette(new Point(80,120),40,30);
		p6.addAttributes(new ColorAttributes(true,true,Color.MAGENTA,Color.BLACK));
		((SCollection) this.getModel()).add(p6);
				
		SPalette p7 = new SPalette(new Point(20,170),40,30);
		p7.addAttributes(new ColorAttributes(true,true,Color.YELLOW,Color.BLACK));
		((SCollection) this.getModel()).add(p7);
				
		SPalette p8 = new SPalette(new Point(80,170),40,30);
		p8.addAttributes(new ColorAttributes(true,true,Color.GREEN,Color.BLACK));
		((SCollection) this.getModel()).add(p8);
	}
	
	public Controller defaultController(Object model) {
		return new ShapeController(model,this);
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.draftman = new ShapeDraftman(g);
		Shape s = (Shape) this.getModel();
		if(s == null) { return; }
		s.accept(this.draftman);
	}
}
