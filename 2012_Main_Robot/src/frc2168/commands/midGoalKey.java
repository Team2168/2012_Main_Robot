package frc2168.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc2168.RobotMap;

public class midGoalKey extends CommandGroup {

	public midGoalKey(){
		
		//Raising hood and then shooting at middle goal
		addSequential(new RaiseHood());
		addSequential(new PID_DriveShooter(RobotMap.MIDDLE_GOAL_FORWARD));
		
		
	}
}
