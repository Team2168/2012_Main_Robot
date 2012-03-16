package frc2168.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc2168.RobotMap;

public class highGoalSide extends CommandGroup {
	public highGoalSide(){
		
		//Raising hood and then shooting at low goal
		addSequential(new RaiseHood(true));
		//no low goal side?
		addSequential(new PID_DriveShooter(RobotMap.HIGH_GOAL_SIDE));
		
		
	}
}
