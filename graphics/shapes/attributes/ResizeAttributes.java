package graphics.shapes.attributes;

import java.util.TreeMap;

import graphics.shapes.SRectangle;
import graphics.shapes.Shape;
import graphics.shapes.handles.Handle;
import graphics.shapes.handles.RHBottomLeft;
import graphics.shapes.handles.RHBottomRight;
import graphics.shapes.handles.RHTopLeft;
import graphics.shapes.handles.RHTopRight;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Store Handler to resize the shape with the mouse
 */
public class ResizeAttributes extends Attributes {
	
	private boolean resizable;
	/**
	 * Create 4 Handlers (one for each bounds corner)
	 * @param master The shape to resize
	 */
	public ResizeAttributes() {
		super();
		this.resizable = true;
	}
	
	public boolean getResizable() {
		return this.resizable;
	}
	
	public void setResizable(boolean resizable) {
		this.resizable = resizable;
	}
	
	@Override
	public String getID() {
		return Attributes.ResizeID;
	}

	public Attributes clone() {
		return new ResizeAttributes();
	}

}
