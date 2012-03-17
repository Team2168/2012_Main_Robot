package frc2168.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc2168.RobotMap;

public class KeyToTopAutoCommand extends CommandGroup
{
	public KeyToTopAutoCommand(){
		//raise hood before shooting
		addSequential(new RaiseHood(true));

		//raise bridge arm
		addSequential(new RaiseBridge());

		//raise flap to allow the elevator to pull balls up
		addSequential(new BackFlapClose());

		//Shift the drive train to high gear
		addSequential(new ShiftGearsLowToHigh());


		//shoot one ball to top goal from the side of the key
		addSequential(new PIDShootBall(RobotMap.HIGH_GOAL_KEY));
		//shoot one ball to top goal from the side of the key
		addSequential(new PIDShootBall(RobotMap.HIGH_GOAL_KEY));

		addSequential(new sleep(),1);
//		
		addSequential(new DriveToSpeed(0.6),1);




	}


}