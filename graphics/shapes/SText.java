package graphics.shapes;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.Map;

import graphics.shapes.attributes.Attributes;
import graphics.shapes.attributes.FontAttributes;
public class SText extends Shape {
	
	private String text;
	private Point loc;

	public SText(Point p, String text) {
		this.setLoc(p);
		this.setText(text);
	}
	
	public SText(String t,Point p,Map<String,Attributes> map){
		super(map);
		this.text=t;
		this.loc=(Point)p.clone();
	}
	
	public String getText() {
		return this.text;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	@Override
	public Point getLoc() {
		return new Point(this.loc);
	}

	@Override
	public void setLoc(Point p) {
		this.loc = p;
	}

	@Override
	public void translate(int x, int y) {
		this.loc.translate(x, y);
	}

	@Override
	public Rectangle getBound() {
		FontAttributes fa = (FontAttributes)this.getAttributes(Attributes.FontID);
		if(fa == null) { fa = new FontAttributes(); }
		Rectangle r = fa.getBounds(this.text);
		r.translate(this.loc.x, this.loc.y);
		return r;
	}

	@Override
	public void accept(ShapeVisitor sv) {
		sv.visitText(this);
	}
	
	public Shape clone(){
		return new SText(this.text,this.loc,attributes);
	}

}