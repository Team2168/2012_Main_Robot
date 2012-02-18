package frc2168.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc2168.RobotMap;

public class HighGoalAuto extends CommandGroup
{
	public HighGoalAuto(){
		
		//Lowering hood and then shooting at high goal
		addSequential(new LowerHood());
		addSequential(new PIDShootBall(RobotMap.HIGH_GOAL_FORWARD));
		
		
	}

}