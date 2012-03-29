package frc2168.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc2168.RobotMap;

public class Auto_MidGoalFromKey extends CommandGroup
{
	public Auto_MidGoalFromKey(){
		//raise hood before shooting
		addParallel(new midGoalKey());

		//wait for 5 seconds then shoot first ball
		addSequential(new sleep(),4);
		addSequential(new shootSingleBall());
		
		//wait for 5 seconds then shoot second ball
		addSequential(new sleep(),4);
		addSequential(new shootSingleBall());		
		
		//wait 1 sec then stop shooter wheel
		addSequential(new sleep(),1);
		addSequential(new PID_ShooterPause());
			
			
		//drive backwards for 2 seconds
			//Shift the drive train to high gear
//			addSequential(new ShiftGearsLowToHigh());
//
//			addSequential(new DriveToSpeed(0.6),2);

	}
}


