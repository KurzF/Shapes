package graphics.shapes.attributes;

public class RotationAttributes extends Attributes{
	private int angle;

	
	public RotationAttributes() {
		this.angle=0;
	}
	
	public String getID() {
		return Attributes.RotationID;
	}
	
	public RotationAttributes(int angle) {
		this.angle=angle;
	}
	
	public int getAngle() {
		return angle;
	}
	
	public void setAngle(int newRot) {
		this.angle=newRot ;
	}
	
	public void incrAngle() {
		this.angle++ ;
	}
	
	public void decrAngle() {
		this.angle-- ;
	}

	public void add(int dtheta) {
		this.angle+=dtheta;
	}

}