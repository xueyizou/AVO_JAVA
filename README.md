# AVO_JAVA

ADAPTIVE VELOCITY OBSTACLES (JAVA)
Velocity Obstacles (VO) is one of the most popular local collision avoidance approaches. A velocity obstacle is the set of all velocities of an agent that will result in a collision with another agent (or obstacle) at some moment in time, assuming that the other agent maintains its current velocity.
The Velocity Obstacles approach has some variations, including Reciprocal Velocity Obstacles (RVO)that implicitly assumes the other agents make a similar collision avoidance reasoning, HybridReciprocal Velocity Obstacle (HRVO)that solves the “reciprocal dance” (i.e. two agents cannot reach agreement on which side to pass each other) problem of RVO, and Optimal Reciprocal Collision Avoidance (ORCA) that provide sufficient conditions for collision-free motion by reducing the problem to solving a low-dimensional linear program.

For the purpose of using the Velocity Obstacles approach and its variations in my simulation inMASON , referring to the existing C++ libraries (which can be found on http://gamma.cs.unc.edu/RVO2/), I wrote a Java library, Adaptive Velocity Obstacles (AVO).

Apart from all the functions of RVO, HRVO, and ORCA libraries, the AVO library make it easy to set algorithm parameters and to select whichever  VO variations you want to use. Now this AVO library can be found on https://github.com/superxueyizou/AVO .

The parameters setting are as follows:

set "alpha" to [0,1.0), RVO
set "alpha" to 1.0,  VO
set "alpha" to (1.0 , 2.0],  HRVO
set "alpha" to (2.0, 3.0],  ORCA
