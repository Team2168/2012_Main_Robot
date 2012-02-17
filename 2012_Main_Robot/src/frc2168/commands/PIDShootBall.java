package frc2168.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class PIDShootBall extends CommandGroup
{
	public PIDShootBall()
	{
		addParallel(new PID_DriveShooter());
		addSequential(new PID_ShooterAtSpeed());
		addSequential(new RaiseBridge());
		addSequential(new PID_ShooterAtSpeed());
		addSequential(new LowerBridge());
		addSequential(new PID_ShooterPause());
	}

}
