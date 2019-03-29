package graphics.shapes.ui;

import java.awt.Graphics;

import graphics.shapes.SCollection;
import graphics.shapes.Shape;
import graphics.ui.Controller;
import graphics.ui.View;

public class ShapesView extends View {
	
	public ShapesView(Object model) {
		super(model);
	}
	
	public Controller defaultController(Object model) {
		return new ShapeController(model,this);
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		ShapeDraftman sd = new ShapeDraftman(g);
		Shape s = (Shape) this.getModel();
		if(s == null) { return; }
		s.accept(sd);
	}
}
