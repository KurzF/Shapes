package graphics.shapes;

import graphics.shapes.attributes.Attributes;
import graphics.shapes.attributes.ResizeAttributes;
import graphics.shapes.attributes.RotationAttributes;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/**
 * A drawable collection of shapes
 */
public class SCollection extends Shape {

	private ArrayList<Shape> collection;


	
	public SCollection(ArrayList<Shape> collection, Map<String, Attributes> attributes, boolean withHandles) {
		super(attributes);
		ArrayList<Shape> save=new ArrayList<Shape>();
		Iterator<Shape> i = collection.iterator();
		while(i.hasNext()) {
			Shape s = i.next();
			save.add(s.clone());
		}
		this.collection=save;
		if(withHandles) {
			this.setResizeHandles();
		}
	}
	
	public SCollection(boolean withHandles) {
		super();
		this.collection = new ArrayList<Shape>();
		if(withHandles) {
			this.setResizeHandles();
		}
		
	}
	
	public SCollection() {
		this(true);
	}
	
	public Iterator<Shape> iterator() {
		return this.collection.iterator();
	}
	
	public void add(Shape s) {
		this.collection.add(s);
		this.refresh();
	}
	
	@Override
	public Point getLoc() {
		//The location is the top left point
		Iterator<Shape> i = this.iterator();
		if(!i.hasNext()) { return new Point(0,0); }
		Point loc = i.next().getLoc();
		while(i.hasNext()) {
			Point p = i.next().getLoc();
			if(p.x < loc.x) { loc.x = p.x; }
			if(p.y < loc.y) { loc.y = p.y; }
		}
		return loc;
	}

	@Override
	public void setLoc(Point p) {
		this.translate(p.x - this.getLoc().x, p.y - this.getLoc().y);
		this.refresh();
	}

	@Override
	public void translate(int x, int y) {
		Iterator<Shape> i = this.iterator();
		while(i.hasNext()) {
			i.next().translate(x,y);
		}
		this.refresh();
	}

	@Override
	public Rectangle getBounds() {
		Iterator<Shape> i = this.iterator();
		if(!i.hasNext()) {
			return new Rectangle(0,0,0,0);
		}
		Rectangle r = i.next().getBounds();
		Point bottom_right = r.getLocation();
		Point top_left = r.getLocation();
		bottom_right.translate(r.width, r.height);
		while(i.hasNext()) {
			Shape s=i.next();
			r = s.getBounds();
			if(r.x < top_left.x) { top_left.x = r.x; }
			if(r.y < top_left.y) { top_left.y = r.y; }
			if(r.width + r.x > bottom_right.x) { bottom_right.x = r.width + r.x; }
			if(r.height + r.y > bottom_right.y) { bottom_right.y = r.height + r.y; }
		}
		return new Rectangle(top_left.x, top_left.y,bottom_right.x-top_left.x,bottom_right.y-top_left.y);
	}
	
	@Override
	public Point getCenter() {
		Rectangle rect = this.getBounds();
		Point loc = rect.getLocation();
		loc.translate(rect.width/2, rect.height/2);
		return loc;
	}
	

	@Override
	public void accept(ShapeVisitor sv) {
		sv.visitCollection(this);
	}

	@Override
	public void setWidth(int width) {
		Rectangle rect = this.getBounds();
		Iterator<Shape> i = this.iterator();
		while(i.hasNext()) {
			Shape elem = i.next();
			Point elem_loc = elem.getLoc();
			elem.setLoc(new Point((int)(rect.x+(elem_loc.x-rect.x)*(width*1.0/rect.width)), elem_loc.y));
			elem.setWidth((int) (elem.getWidth()*(float)width/rect.width));
		}
		this.refresh();
		
	}

	@Override
	public void setHeight(int height) {
		Rectangle rect = this.getBounds();
		Iterator<Shape> i = this.iterator();
		while(i.hasNext()) {
			Shape elem = i.next();
			Point elem_loc = elem.getLoc();
			elem.setLoc(new Point(elem_loc.x, (int)(rect.y+(elem_loc.y-rect.y)*((float)height/rect.height))) );
			elem.setHeight((int) (elem.getHeight()*(float)height/rect.height));
		}
		this.refresh();
	}
	
	public void setCollection(int index, Shape elem) {
		if(index>=0 && index < this.collection.size()) {
			this.collection.set(index, elem);
			System.out.println("setCol");
		}
		else {
			System.out.println("setCollection error: " + index);
		}
	}
	
	public Shape getShape(int index) {
		return this.collection.get(index);
	}

	@Override
	public Shape clone() {
		return new SCollection(this.collection,this.attributes, this.getResizeHandles()!=null);
	}
	
	public String toString() {
		Iterator<Shape> it = this.collection.iterator();
		String ret = "";
		while(it.hasNext()) {
			ret += it.next() +"\n";
		}
		return ret;
	}
}
