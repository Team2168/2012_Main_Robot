package frc2168.commands;

import edu.wpi.first.wpilibj.command.Command;

public class driveShooterPID extends CommandBase
{
	
	public driveShooterPID()
	{
		requires(hood);
		
	}

	protected void initialize()
	{
		// TODO Auto-generated method stub
		//start the PID controller if it is paused
		hood.shooterWheelController.Enable();

	}

	protected void execute()
	{
		// TODO Auto-generated method stub
		//drive wheel based on shooter controller output
		hood.spinMotor(hood.shooterWheelController.getCo());
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
