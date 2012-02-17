package frc2168.commands;

import edu.wpi.first.wpilibj.command.Command;

public class PID_DriveShooter extends CommandBase
{
	
	public PID_DriveShooter()
	{
		requires(hood);
		
	}
	
	public PID_DriveShooter(double setPoint)
	{
		this();
		hood.shooterWheelController.setSp(setPoint);
		
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
