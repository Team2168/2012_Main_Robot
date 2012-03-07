package frc2168.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc2168.RobotMap;

public class KeyToMiddleAutoCommand extends CommandGroup
{
	public KeyToMiddleAutoCommand(){
		//raise hood before shooting
		addSequential(new RaiseHood());
		
		//raise bridge arm
		addSequential(new RaiseBridge());
		
		//raise flap to allow the elevator to pull balls up
		addSequential(new BackFlapClose());
		
		//Shift the drive train to high gear
		addSequential(new ShiftGearsLowToHigh());
		
		
		//shoot one ball to top goal from the side of the key
		addSequential(new PIDShootBall(RobotMap.AUTO_KEY_TO_MIDDLE));
		//shoot one ball to top goal from the side of the key
		addSequential(new PIDShootBall(RobotMap.AUTO_KEY_TO_MIDDLE));
		
		addSequential(new sleep(),1);
//		
		addSequential(new DriveToSpeed(0.6),1);
		
		
		
		
	}
	
	
}
