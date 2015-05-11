package sim;

import java.awt.Color;

import javax.swing.JFrame;

import sim.display.Controller;
import sim.display.Display2D;
import sim.display.GUIState;
import sim.engine.SimState;
import sim.portrayal.continuous.ContinuousPortrayal2D;
import sim.portrayal.simple.OvalPortrayal2D;

public class SimulationWithUI extends GUIState {

	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;

	public Display2D display;
	public JFrame displayFrame;

	public static void main(String[] args) {
		// randomizes by currentTimeMillis
		new SimulationWithUI().createController();
	}

	public Object getSimulationInspectedObject() {
		// non-volatile
		return state;
	}

	ContinuousPortrayal2D environmentPortrayal = new ContinuousPortrayal2D();

	public SimulationWithUI() {
		super(new Simulation(System.currentTimeMillis()));
	}

	public SimulationWithUI(SimState state) {
		super(state);
	}

	public static String getName() {
		return "AVOJava Simulation";
	}

	public void start() {
		super.start();
		setupPortrayals();
	}

	public void load(SimState state) {
		super.load(state);
		setupPortrayals();
	}

	public void setupPortrayals() {

		Simulation crowd = (Simulation) state;

		environmentPortrayal.setField(crowd.environment);
		environmentPortrayal.setPortrayalForAll(new OvalPortrayal2D(Color.black, 30));

		// reschedule the displayer
		display.reset();
		display.setBackdrop(Color.white);

		// redraw the display
		display.repaint();
	}

	public void init(Controller c) {
		super.init(c);

		// make the displayer
		display = new Display2D(WIDTH, HEIGHT, this);
		display.setClipping(false);

		displayFrame = display.createFrame();
		displayFrame.setTitle("AVO/MASON");

		// register the frame so it appears in the "Display" list
		c.registerFrame(displayFrame);

		displayFrame.setVisible(true);
		display.attach(environmentPortrayal, "Agents");
	}

	public void quit() {
		super.quit();

		if (displayFrame != null)
			displayFrame.dispose();
		displayFrame = null;
		display = null;
	}

}
