package graphics.shapes;

import graphics.shapes.attributes.Attributes;

import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.util.Map;

public class SImage extends Shape{
	private String imageURL;
	private Image image;
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
	    this.refresh();
	}
	
	public SImage(String url,Point p) {
		this.imageURL=url;
		this.loc=p;
	    if (imageURL != null) {
	        Toolkit kit = Toolkit.getDefaultToolkit();
	        this.image = kit.getImage(this.imageURL);
	    }
	    this.setResizeHandles();
	    this.refresh();
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
	    	this.refresh();
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
	    	this.refresh();
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
		return (Point)this.loc.clone();
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
		new SImage(this.imageURL,this.getLoc(),width, this.getHeight());
		this.refresh();
		
	}

	@Override
	public void setHeight(int height) {
		new SImage(this.imageURL,this.getLoc(), this.getWidth(), height);
		this.refresh();
	}
}
