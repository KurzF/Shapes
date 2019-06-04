package graphics.shapes.handles;

import java.awt.Point;
import java.util.Iterator;

import graphics.shapes.SRectangle;
import graphics.shapes.Shape;
import graphics.shapes.attributes.Attributes;
import graphics.shapes.handles.Handle;

public class ResizeHandles {
	public static final int LENGTH = 4;
	public static final int TL = 0;
	public static final int TR = 1;
	public static final int BL = 2;
	public static final int BR = 3;
	
	public static int HANDLE_SIZE = 8;
	
	private Shape master;
	private Handle[] resize_handles = new Handle[ResizeHandles.LENGTH]; 
	
	public ResizeHandles(Shape master) {
		this.master = master;
		for(int i=0; i<ResizeHandles.LENGTH; i++) {
			this.resize_handles[i] = this.defaultHandleById(i);
		}
	}
	
	public void setHandleById(int id, Handle h) {
		if(id > -1 && id < ResizeHandles.LENGTH) {
			this.resize_handles[id] = h;
		}
	}
	
	public Handle getHandle(int id) {
		if(id > -1 && id < ResizeHandles.LENGTH) {
			return this.resize_handles[id];
		}
		return null;
	}
	
	public Handle defaultHandleById(int id) {
		switch(id) {
		case ResizeHandles.TL:
			return new RHTopLeft(this.master);
		case ResizeHandles.TR:
			return new RHTopRight(this.master);
		case ResizeHandles.BL:
			return new RHBottomLeft(this.master);
		case ResizeHandles.BR:
			return new RHBottomRight(this.master);
		default:
			return null;
		}
	}

	
	/*public void translate(int dx, int dy) {
		for(int i=0; i<ResizeHandles.LENGTH; i++) {
			this.resize_handles[i].translate(dx, dy);
		}
	}*/
	
	/**
	 * Realign every handler with the master shape
	 */
	public void refresh() {
		for (int i=0; i<ResizeHandles.LENGTH; i++) {
			this.resize_handles[i].position();
		}
	}
}
