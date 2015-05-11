package sim;

import sim.engine.Schedule;
import sim.engine.SimState;
import sim.field.continuous.Continuous2D;
import sim.util.Bag;
import sim.util.Double2D;


public class Simulation extends SimState {

	private static final long serialVersionUID = -7230646003850297858L;
	public final float width = SimulationWithUI.WIDTH;
	public final float height = SimulationWithUI.HEIGHT;

	AVOSimulatorAgent avoSimulatorAgent;
	
	public int numAgents = 10;
	public Bag agentBag = new Bag();
	public Continuous2D environment;

	public Simulation(long seed) {

		super(seed);
		// set up the fields
		environment = new Continuous2D(0.25, width, height);		
	}

	public void setupScenario() {
		
		double radius=0.4*height;
		for (int i = 0; i < numAgents; ++i) 
		{			
			Double2D position = new Double2D(0.5*width+radius*Math.cos(i * 2*Math.PI/numAgents), 0.5*height+radius*Math.sin(i * 2*Math.PI/numAgents));
			Double2D tarPosition = new Double2D(0.5*width-radius*Math.cos(i * 2*Math.PI/numAgents), 0.5*height-radius*Math.sin(i * 2*Math.PI/numAgents));
			NormalAgent agent = new NormalAgent(i,position,tarPosition);
			agentBag.add(agent);
			environment.setObjectLocation(agent, agent.loc);
			schedule.scheduleRepeating(Schedule.EPOCH, 2, agent);
		}
		
		avoSimulatorAgent=new AVOSimulatorAgent(this);
		schedule.scheduleRepeating(Schedule.EPOCH, 1, avoSimulatorAgent);
	}

	@Override
	public void start() {
		super.start();	
		setupScenario();
	}

	@Override
	public void finish() {
		super.finish();
		System.exit(0);
	}

	public static void main(String[] args) {
		doLoop(Simulation.class, args);
		System.exit(0);
	}

}
