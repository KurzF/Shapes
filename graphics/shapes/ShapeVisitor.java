package graphics.shapes;

import java.awt.Rectangle;

public interface ShapeVisitor {
	public abstract void visitRectangle(SRectangle rect);
}
