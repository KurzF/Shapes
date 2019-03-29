package graphics.shapes;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Iterator;

public class SCollection extends Shape {

	private ArrayList<Shape> collection;

	public SCollection() {
		super();
		this.collection = new ArrayList<Shape>();
	}
	
	public Iterator<Shape> iterator() {
		return this.collection.iterator();
	}
	
	public void add(Shape s) {
		this.collection.add(s);
	}
	
	@Override
	public Point getLoc() {
		//The location is the top left point
		Iterator<Shape> i = this.iterator();
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
		this.translate((int)(p.getX() - this.getLoc().getX()), (int)(p.getY() - this.getLoc().getY()));
	}

	@Override
	public void translate(int x, int y) {
		Iterator<Shape> i = this.iterator();
		while(i.hasNext()) {
			i.next().translate(x,y);
		}
	}

	@Override
	public Rectangle getBound() {
		Point loc = this.getLoc();
		Iterator<Shape> i = this.iterator();
		Rectangle r = i.next().getBound();
		Point bottom_right = r.getLocation();
		bottom_right.translate(r.width, r.height);
		while(i.hasNext()) {
			r = i.next().getBound();
			if(r.width + r.x > bottom_right.x) { bottom_right.x = r.width + r.x; }
			if(r.height + r.y > bottom_right.y) { bottom_right.y = r.height + r.y; }
		}
		return new Rectangle(loc.x, loc.y,bottom_right.x-loc.x,bottom_right.y-loc.y);
	}

	@Override
	public void accept(ShapeVisitor sv) {
		sv.visitCollection(this);
	}
}
