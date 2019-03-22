package graphics.shapes.ui;

import java.awt.Graphics;
import java.awt.Graphics2D;

import graphics.shapes.Shape;
import graphics.shapes.ShapeDraftman;
import graphics.ui.Controller;
import graphics.ui.View;

public class ShapesView extends View {
	
	public ShapesView(Object model) {
		super(model);
	}
	
	public Controller defaultController(Object model) {
		return new ShapeController(model);
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		ShapeDraftman sd = new ShapeDraftman(g);
		((Shape) this.getModel()).accept(sd);
	}
}
