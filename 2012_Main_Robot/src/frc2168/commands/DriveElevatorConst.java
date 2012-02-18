package frc2168.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc2168.commands.*;

public class DriveElevatorConst extends CommandBase
{
	private double constSpeed;
	
	public DriveElevatorConst(double setSpeed){
		requires(elevatorFlap);
		constSpeed=setSpeed;
	}
	
	
	protected void initialize()
	{
		
		// TODO Auto-generated method stub

	}

	protected void execute()
	{
		elevatorFlap.setBallElevatorSpeed(constSpeed);
		// TODO Auto-generated method stub

	}

	protected boolean isFinished()
	{
		// TODO Auto-generated method stub
		return false;
	}

	protected void end()
	{
		// TODO Auto-generated method stub

	}

	protected void interrupted()
	{
		// TODO Auto-generated method stub

	}

}
