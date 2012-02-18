package frc2168.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc2168.RobotMap;

public class MiddleGoalAuto extends CommandGroup
{
	
	public MiddleGoalAuto(){
		
		//Raising hood and then shooting at middle goal
		addSequential(new RaiseHood());
		addSequential(new PIDShootBall(RobotMap.MIDDLE_GOAL_FORWARD));
		
		
	}

}
