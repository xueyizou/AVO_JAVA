package sim;

import sim.engine.SimState;
import sim.engine.Steppable;
import sim.util.Double2D;


public class NormalAgent implements Steppable{

	private static final long serialVersionUID = -1295587848829946926L;
	
	public int id;
	public Double2D target_loc;
	public Double2D loc = new Double2D(0, 0);
	public boolean goal_reached = false;

	public NormalAgent(int id, Double2D loc, Double2D target_loc) {
		this.id = id;
		this.loc=loc;
		this.target_loc=target_loc;
		this.goal_reached = false;	
	}


	@Override
	public void step(SimState simState) {
		Simulation state=(Simulation) simState;
		Double2D newLoc=state.avoSimulatorAgent.avo_sim.getAgentPosition(id);
		state.environment.setObjectLocation(this, newLoc);
	}

}
