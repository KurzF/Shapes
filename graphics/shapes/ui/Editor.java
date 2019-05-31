package graphics.shapes.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;

import graphics.shapes.SCircle;
import graphics.shapes.SCollection;
import graphics.shapes.SPolygon;
import graphics.shapes.SRectangle;
import graphics.shapes.SText;
import graphics.shapes.Shape;
import graphics.shapes.attributes.Attributes;
import graphics.shapes.attributes.ColorAttributes;
import graphics.shapes.attributes.FontAttributes;
import graphics.shapes.attributes.ResizeAttributes;
import graphics.shapes.attributes.RotationAttributes;
import graphics.shapes.attributes.SelectionAttributes;
import graphics.shapes.handles.Handle;
import graphics.shapes.handles.RHBottomRight;

/**
 * Create the MVC
 * Every shape to draw must be add to the model
 */
public class Editor extends JFrame
{
	ShapesView sview;
	SCollection model;
	
	
	public Editor()
	{	
		super("Shapes Editor");

		this.addWindowListener(new java.awt.event.WindowAdapter()
		{
			public void windowClosing(java.awt.event.WindowEvent evt)
			{
				System.exit(0);
			}
		});
		
		this.buildModel();
		this.sview = new ShapesView(this.model);
		this.sview.setPreferredSize(new Dimension(300,300));
		this.getContentPane().add(this.sview, java.awt.BorderLayout.CENTER);
	}

	
	private void buildModel()
	{
		this.model = new SCollection(false);
		this.model.addAttributes(new SelectionAttributes());
		
		SRectangle r = new SRectangle(new Point(20,10),50,80);
		r.addAttributes(new ColorAttributes(false,true,Color.RED,Color.RED));
		r.addAttributes(new SelectionAttributes());
		r.addAttributes(new ResizeAttributes());
		r.addAttributes(new RotationAttributes(90));
		this.model.add(r);
		
		r.setLoc(new Point(40,40));
		
		/*SCircle c = new SCircle(new Point(50,50),10);
		c.addAttributes(new ColorAttributes(true,true,Color.GREEN,Color.GREEN));
		c.addAttributes(new SelectionAttributes());
		c.addAttributes(new ResizeAttributes(c));
		this.model.add(c);
		c.addAttributes(new ColorAttributes(true,false,Color.RED,Color.BLACK));
		
		Point center = r.getCenter();
		center.translate(-2, -2);
		c = new SCircle(r.getCenter(),4);
		c.addAttributes(new ColorAttributes(true,true,Color.GREEN,Color.GREEN));
		this.model.add(c);
				
		
		SText t= new SText(new Point(100,80),"Épihfqhfljqgfkqsgfhkqgfhkqgfkq");
		t.addAttributes(new ColorAttributes(true,false,Color.YELLOW,Color.BLUE));
		t.addAttributes(new FontAttributes());
		t.addAttributes(new SelectionAttributes());
		t.addAttributes(new RotationAttributes());
		this.model.add(t);
		
		SCollection sc = new SCollection();
		sc.addAttributes(new SelectionAttributes());
		r = new SRectangle(new Point(70,90),30,30);
		r.addAttributes(new ColorAttributes(true,true,Color.MAGENTA,Color.BLUE));
		r.addAttributes(new SelectionAttributes());
		sc.add(r);
		r = new SRectangle(new Point(100,120),31,49);
		r.addAttributes(new ColorAttributes(false,true,Color.MAGENTA,Color.BLUE));
		r.addAttributes(new SelectionAttributes());
		sc.add(r);
		c = new SCircle(new Point(150,100),20);
		c.addAttributes(new ColorAttributes(false,true,Color.BLUE,Color.DARK_GRAY));
		c.addAttributes(new SelectionAttributes());
		sc.add(c);
		sc.addAttributes(new ResizeAttributes(sc));
		sc.addAttributes(new RotationAttributes(0));
		this.model.add(sc);
		
		SPolygon p = new SPolygon();
		p.add(new Point(15,15));
		p.add(new Point(15,30));
		p.add(new Point(30,30));
		p.addAttributes(new SelectionAttributes());
		p.addAttributes(new RotationAttributes(0));
		this.model.add(p);*/
	}
	
	public static void main(String[] args)
	{
		Editor self = new Editor();
		self.pack();
		self.setVisible(true);
		self.sview.setFocusable(true);
		self.sview.grabFocus();
		
	}
}
