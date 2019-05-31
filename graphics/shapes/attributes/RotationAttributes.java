package graphics.shapes.attributes;

/**
 * Describe a shape rotation
 */
public class RotationAttributes extends Attributes{
	private int angle;

	/**
	 * 
	 * @param angle Angle of rotaion (in degre)
	 */
	public RotationAttributes(int angle) {
		this.angle=angle%360;
	}
	public RotationAttributes() {
		this.angle=0;
	}
	
	public String getID() {
		return Attributes.RotationID;
	}
	

	
	public int getAngle() {
		return angle;
	}
	
	public void setAngle(int newRot) {
		this.angle=newRot%360 ;
	}
	
	public void incrAngle() {
		this.angle++ ;
	}
	
	public void decrAngle() {
		this.angle-- ;
	}
	
	/**
	 * Add dtheta to the current angle
	 * @param dtheta angle (in degre)
	 */
	public void add(int dtheta) {
		this.angle+=dtheta;
		System.out.println("Angle: " +this.angle);
	}
	
	public Attributes clone(){
		return new RotationAttributes(this.angle);
	}

}