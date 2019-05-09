package graphics.shapes.attributes;

public class RotationAttributes extends Attributes{
	private int angle;

	
	public RotationAttributes() {
		this.angle=0;
	}
	
	public String getID() {
		return Attributes.RotationID;
	}
	
	public RotationAttributes(int angl) {
		this.angle=angl;
	}
	
	public int getAngle() {
		return angle;
	}
	
	public void setRotation(int newRot) {
		this.angle=newRot ;
	}
	
	public void incrAngle() {
		this.angle++ ;
	}
	
	public void decrAngle() {
		this.angle-- ;
	}


}