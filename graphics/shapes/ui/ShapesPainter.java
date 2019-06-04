package graphics.shapes.ui;

import java.awt.Color;

import graphics.shapes.SCircle;
import graphics.shapes.SCollection;
import graphics.shapes.SImage;
import graphics.shapes.SPalette;
import graphics.shapes.SPolygon;
import graphics.shapes.SRectangle;
import graphics.shapes.SText;
import graphics.shapes.ShapeVisitor;

public class ShapesPainter implements ShapeVisitor {
	
	private Color currentColor;
	
	public ShapesPainter() {
		this(Color.BLACK);		//the initial color is black
	}
	
	public ShapesPainter(Color initialColor) {
		this.setCurrentColor(initialColor);
	}
	
	
	public void setCurrentColor(Color color) {
		this.currentColor = color;
	}
	public Color getCurrentColor() {
		return currentColor;
	}
	

	public void visitRectangle(SRectangle srect) {
		srect.setColor(currentColor);
	}

	public void visitCircle(SCircle scirc) {
		scirc.setColor(currentColor);
	}

	public void visitText(SText stext) {
		stext.setColor(currentColor);
	}

	public void visitPalette(SPalette spal) {
		this.setCurrentColor(spal.getColor());
	}

	public void visitCollection(SCollection scoll) {
		scoll.setColor(currentColor);
	}

	@Override
	public void visitPolygon(SPolygon p) {
		p.setColor(currentColor);
		
	}

	@Override
	public void visitImage(SImage i) {
		i.setColor(currentColor);
	}

}
