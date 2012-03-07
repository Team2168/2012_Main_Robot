package frc2168.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc2168.RobotMap;

public class DriveToFenderShootMiddleAutoCommand extends CommandGroup {

	
		public DriveToFenderShootMiddleAutoCommand(){
			//raise hood before shooting
			addSequential(new RaiseHood());
			
			//raise bridge arm
			addSequential(new RaiseBridge());
			
			//raise flap to allow the elevator to pull balls up
			addSequential(new BackFlapClose());
			
			//Shift the drive train to high gear
			addSequential(new ShiftGearsLowToHigh());
			
			//drive to the front of the fender
			addSequential(new DriveToFenderAutoCommand());
					
			
			//shoot one ball to middle goal from in front of the fender
			addSequential(new PIDShootBall(RobotMap.LOW_GOAL_FORWARD));
			//shoot one ball to middle goal from in front of the fender
			addSequential(new PIDShootBall(RobotMap.LOW_GOAL_FORWARD));
			
			addSequential(new sleep(),1);
			

			
			
			
			
		}
		
		
	

}
