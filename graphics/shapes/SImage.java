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
	    this.setResizeHandles();
	}
	
	public SImage(String url,Point p) {
		this.imageURL=url;
		this.loc=p;
	    if (imageURL != null) {
	        Toolkit kit = Toolkit.getDefaultToolkit();
	        this.image = kit.getImage(this.imageURL);
	    }
	    this.setResizeHandles();
	}
	
	public SImage(String url,Point p,int width, int height, boolean withHandles) {
		this.imageURL=url;
		this.loc=p;
	    if (imageURL != null) {
	        Toolkit kit = Toolkit.getDefaultToolkit();
	        this.image = kit.getImage(this.imageURL);
	        this.image=this.image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
	    }
	    if(withHandles) {
	    	this.setResizeHandles();
	    }
	    
	}
	
	public SImage(String url,Point p,int width, int height) {
		this(url,p,width,height, true);
	}
	
	public SImage(String url,Point p,Image i,Map<String,Attributes> map, boolean withHandles){
		super(map);
		this.loc=(Point) p.clone();
		this.imageURL=url;
	    this.image =i;
	    if(withHandles) {
	    	this.setResizeHandles();
	    }
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
		this.loc=(Point)p.clone();
		this.refresh();
	}

	@Override
	public void translate(int x, int y) {
		this.loc.translate(x, y);
		this.refresh();
	}

	@Override
	public Rectangle getBounds() {

		Rectangle rect=new Rectangle();
		rect.width=this.image.getWidth(null);
		rect.height=this.image.getHeight(null);
		rect.x=this.loc.x;
		rect.y=this.loc.y;
		return rect;
	}
	
	public void accept(ShapeVisitor vs) {
		vs.visitImage(this);
	}
	
	public Shape clone(){
		return new SImage(this.imageURL,this.getLoc(),this.image,attributes,this.getResizeHandles()!=null);
	}

	@Override
	public Point getCenter() {
		Point center = this.getLoc();
		center.translate(this.image.getWidth(null)/2, this.image.getHeight(null)/2);
		return center;
	}

	@Override
	public void setWidth(int width) {
		//this.image=this.image.getScaledInstance(width, this.image.getHeight(null), Image.SCALE_SMOOTH);
		//this.refresh();
		
	}

	@Override
	public void setHeight(int height) {
		//this.image=this.image.getScaledInstance(this.image.getWidth(null), height, Image.SCALE_SMOOTH);
		//this.refresh();
	}
}
