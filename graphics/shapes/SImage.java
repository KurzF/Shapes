package graphics.shapes;

import graphics.shapes.attributes.Attributes;

import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.util.Map;

public class SImage extends Shape{
	private String imageURL;
	public Image image;
	private Point loc;
	
	public SImage(String url) {
		super();
		this.loc=new Point(10,10);
		this.imageURL = url;
	    if (imageURL != null) {
	        Toolkit kit = Toolkit.getDefaultToolkit();
	        this.image = kit.getImage(this.imageURL);
	    }
	}
	
	public SImage(String url,Point p) {
		this.imageURL=url;
		this.loc=p;
	    if (imageURL != null) {
	        Toolkit kit = Toolkit.getDefaultToolkit();
	        this.image = kit.getImage(this.imageURL);
	    }
	}
	
	public SImage(String url,Point p,int width, int height) {
		this.imageURL=url;
		this.loc=p;
	    if (imageURL != null) {
	        Toolkit kit = Toolkit.getDefaultToolkit();
	        this.image = kit.getImage(this.imageURL);
	        this.image=this.image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
	    }
	}
	
	public SImage(String url,Point p,Image i,Map<String,Attributes> map){
		super(map);
		this.loc=(Point) p.clone();
		this.imageURL=url;
	    this.image =i;
	}
	
	public String getUrl() {
		return this.imageURL;
	}
	
	public Image getImage() {
		return this.image;
	}
	
	@Override
	public Point getLoc() {
		return this.loc;
	}

	@Override
	public void setLoc(Point p) {
		this.loc=p;
	}

	@Override
	public void translate(int x, int y) {
		this.loc.translate(x, y);
	}

	@Override
	public Rectangle getBound() {
		int width;
		int height;
		Rectangle rect=new Rectangle();
		width=this.image.getWidth(null);
		height=this.image.getHeight(null);
		rect.width=width;
		rect.height=height;
		rect.x=this.loc.x;
		rect.y=this.loc.y;
		return rect;
	}
	
	public void accept(ShapeVisitor vs) {
		vs.visitImage(this);
	}
	
	public Shape clone(){
		return new SImage(this.imageURL,this.loc,this.image,attributes);
	}
}
