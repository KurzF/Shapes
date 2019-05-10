package graphics.shapes.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;

import javax.swing.JFrame;

import graphics.shapes.SCircle;
import graphics.shapes.SCollection;
import graphics.shapes.SRectangle;
import graphics.shapes.SText;
import graphics.shapes.attributes.ColorAttributes;
import graphics.shapes.attributes.FontAttributes;
import graphics.shapes.attributes.RotationAttributes;
import graphics.shapes.attributes.SelectionAttributes;

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
		this.model = new SCollection();
		this.model.addAttributes(new SelectionAttributes());
		
		SRectangle r = new SRectangle(new Point(20,10),50,80);
		r.addAttributes(new ColorAttributes(false,true,Color.RED,Color.RED));
		r.addAttributes(new SelectionAttributes());
		r.addAttributes(new RotationAttributes(20));
		this.model.add(r);
		
		
		
		SCircle c = new SCircle(new Point(50,50),10);
		c.addAttributes(new ColorAttributes(true,true,Color.GREEN,Color.GREEN));
		c.addAttributes(new SelectionAttributes());
		this.model.add(c);
		c.addAttributes(new ColorAttributes(true,false,Color.RED,Color.BLACK));
		
		SText t= new SText(new Point(100,80),"Épihfqhfljqgfkqsgfhkqgfhkqgfkq");
		t.addAttributes(new ColorAttributes(true,false,Color.YELLOW,Color.BLUE));
		t.addAttributes(new FontAttributes());
		t.addAttributes(new SelectionAttributes());
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
		sc.addAttributes(new RotationAttributes(70));
		this.model.add(sc);
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