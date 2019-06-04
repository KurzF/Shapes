package graphics.shapes.ui;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class SliderListener implements ChangeListener {

	private ShapeController controller;
	
	public SliderListener(ShapeController controller) {
		this.controller = controller;
	}
	
	public void stateChanged(ChangeEvent e) {
		//When a slider value changes, the controller changes the show palette color
		this.controller.sliderChange();
	}

}
