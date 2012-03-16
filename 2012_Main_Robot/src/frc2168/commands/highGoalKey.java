package frc2168.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc2168.RobotMap;

public class highGoalKey extends CommandGroup {
	public highGoalKey(){
		
		//Raising hood and then shooting at middle goal
		addSequential(new RaiseHood());
		addSequential(new PID_DriveShooter(RobotMap.HIGH_GOAL_KEY));
		
		
	}
}
