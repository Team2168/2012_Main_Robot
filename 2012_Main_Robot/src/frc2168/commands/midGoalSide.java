package frc2168.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc2168.RobotMap;

public class midGoalSide extends CommandGroup {
	public midGoalSide(){
		
		//Raising hood and then shooting at low goal
		addSequential(new RaiseHood(true));
		addSequential(new PID_DriveShooter(RobotMap.MIDDLE_GOAL_SIDE));
		
		
	}
}
