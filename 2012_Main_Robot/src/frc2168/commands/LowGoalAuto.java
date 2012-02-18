package frc2168.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc2168.RobotMap;

public class LowGoalAuto extends CommandGroup
{
	
	public LowGoalAuto(){
		
		//Raising hood and then shooting at low goal
		addSequential(new RaiseHood());
		addSequential(new PIDShootBall(RobotMap.LOW_GOAL_FORWARD));
		
		
	}

}
