package frc2168.commands;

import edu.wpi.first.wpilibj.command.Command;

public class PID_ShooterAtSpeed extends CommandBase
{
	public PID_ShooterAtSpeed()
	{
		
	}

	protected void initialize()
	{
		// TODO Auto-generated method stub

		
	}

	protected void execute()
	{
		// TODO Auto-generated method stub

	}

	protected boolean isFinished()
	{
		// TODO Auto-generated method stub
		return hood.shooterWheelController.atSpeed();
	}

	protected void end()
	{
		// TODO Auto-generated method stub

	}

	protected void interrupted()
	{
		// TODO Auto-generated method stub
		end();
	}

}
