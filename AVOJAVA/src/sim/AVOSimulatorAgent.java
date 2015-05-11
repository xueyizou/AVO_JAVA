package sim;

import sim.engine.SimState;
import sim.engine.Steppable;
import avo.AVOSimulator;

public class AVOSimulatorAgent implements Steppable 
{	
	private static final long serialVersionUID = 1L;
	
	public AVOSimulator avo_sim;
	private float time_step = 1.0f;
	public AVOSimulatorAgent(Simulation state)
	{
		// Create a new simulator instance.
		avo_sim = new AVOSimulator();

		// Specify global time step of the simulation.
		avo_sim.setTimeStep(time_step);

		// Specify default parameters for agents that are subsequently added.
	  /*set "alpha" to [0,1.0), RVO
		set "alpha" to 1.0,  VO
		set "alpha" to (1.0 , 2.0],  HRVO
		set "alpha" to (2.0, 3.0],  ORCA*/
		//neighborDist, maxNeighbors, timeHorizon, timeHorizonObst, radius, goalRadius, prefSpeed, maxSpeed, minSpeed,alpha
		avo_sim.setAgentDefaults(200,10, 10.0, 20.0, 20, 20,1,2,0.5,0.5);
		for (int i = 0; i < state.numAgents; ++i) 
		{
			NormalAgent agent=(NormalAgent)state.agentBag.get(i);
			int goalID=avo_sim.addGoal(agent.target_loc);
			avo_sim.addAgent(agent.loc, goalID);
		}
		
	}

	@Override
	public void step(SimState state) {
		avo_sim.doStep();
	}

}
