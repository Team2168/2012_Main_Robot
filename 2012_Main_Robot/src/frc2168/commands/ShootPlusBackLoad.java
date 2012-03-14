package frc2168.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.CommandGroup;
import frc2168.*;

public class ShootPlusBackLoad extends CommandGroup
{
	
	OI oi = new OI();
	
	public ShootPlusBackLoad(){
		//raise hood before shooting
		addSequential(new RaiseHood());
		
		//raise bridge arm
		addSequential(new RaiseBridge());
		
		//raise flap to allow the elevator to pull balls up
		addSequential(new BackFlapClose());
		
		//Shift the drive train to high gear
		addSequential(new ShiftGearsLowToHigh());
		
		//shoot one ball to top goal from the side of the key
		addSequential(new PIDShootBall(RobotMap.AUTO_KEY_TO_TOP));
		//shoot one ball to top goal from the side of the key
		addSequential(new PIDShootBall(RobotMap.AUTO_KEY_TO_TOP));
		
		addSequential(new sleep(),1);
		
		addSequential(new BackFlapOpen());
		
		Timer.delay(oi.ioBoard.getTimeDelay(1, RobotMap.maxDelayValue, RobotMap.minDelayValue));		//IDK Potentiometer channel, Imma say one.
		
		//raise flap to allow the elevator to pull balls up
		addSequential(new BackFlapClose());
		
		//Shift the drive train to high gear
		addSequential(new ShiftGearsLowToHigh());
		
		//shoot one ball to top goal from the side of the key
		addSequential(new PIDShootBall(RobotMap.AUTO_KEY_TO_TOP));
		//shoot one ball to top goal from the side of the key
		addSequential(new PIDShootBall(RobotMap.AUTO_KEY_TO_TOP));
		
		addSequential(new sleep(),1);
				
		
	}
	
	
}
