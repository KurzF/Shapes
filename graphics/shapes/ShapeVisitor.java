package graphics.shapes;

/**
 * An interface to draw Shape
 */
public interface ShapeVisitor {
	public abstract void visitRectangle(SRectangle rect);
	public abstract void visitCollection(SCollection c);
	public abstract void visitCircle(SCircle c);
	public abstract void visitText(SText t);
	public abstract void visitPolygon(SPolygon p);
}