package frc2168.commands;

import edu.wpi.first.wpilibj.command.Command;

/**
 * 
 * @author Kevin Harrilal, First Robotics Team 2168
 *
 *This is a macro command to automatically spin the shooter wheel at the given speed
 *
 *
*/


public class PID_DriveShooter extends CommandBase
{
	
	private double driveWithMe;
	
	public PID_DriveShooter()
	{
		requires(hood);
		driveWithMe=0;
		
	}
	
	public PID_DriveShooter(double setPoint)
	{
		this();
		driveWithMe=setPoint;
		
		
	}

	protected void initialize()
	{
		// TODO Auto-generated method stub
		//start the PID controller if it is paused
		hood.shooterWheelController.reset();
		hood.shooterWheelController.Enable();

	}

	protected void execute()
	{
		// TODO Auto-generated method stub
		//drive wheel based on shooter controller output
		if (driveWithMe!=0)
		hood.shooterWheelController.setSp(driveWithMe);
		hood.spinMotor(hood.shooterWheelController.getCo());
	}

	protected boolean isFinished()
	{
		// TODO Auto-generated method stub
		return hood.shooterWheelController.isEnabled()==false;
	}

	protected void end()
	{
		// TODO Auto-generated method stub
		hood.shooterWheelController.Pause();
	}

	protected void interrupted()
	{
		// TODO Auto-generated method stub
		end();

	}

}
