package frc2168.commands;

import edu.wpi.first.wpilibj.command.Command;

public class PrintSpeed extends CommandBase
{

	public PrintSpeed()
	{
		
	}
	
	protected void initialize()
	{
		// TODO Auto-generated method stub

	}

	protected void execute()
	{
		System.out.println("Ball Fired at:" + hood.shooterWheelController.getEncoderRate());

	}

	protected boolean isFinished()
	{
		// TODO Auto-generated method stub
		return true;
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
