package frc2168.commands;

import edu.wpi.first.wpilibj.command.Command;

public class PID_ShooterPause extends CommandBase
{
	
	public PID_ShooterPause()
	{
		requires(buttonBox);
		requires(hood);
	}

	protected void initialize()
	{
		hood.shooterWheelController.Pause();

	}

	protected void execute()
	{
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub

	}

	protected boolean isFinished()
	{
		// TODO Auto-generated method stub
		
		return hood.shooterWheelController.isEnabled()==false;
		
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
