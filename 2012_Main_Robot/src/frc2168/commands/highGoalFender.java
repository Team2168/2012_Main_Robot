package frc2168.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc2168.RobotMap;

public class highGoalFender extends CommandGroup
{
	public highGoalFender(){
		
		//Lowering hood and then shooting at high goal
		addSequential(new LowerHood(true));
		addSequential(new PID_DriveShooter(RobotMap.HIGH_GOAL_FORWARD));
		
	}

}