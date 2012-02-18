package frc2168.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc2168.RobotMap;

public class PIDShootBall extends CommandGroup
{
	public PIDShootBall()
	{
		addParallel(new PID_DriveShooter());
		addSequential(new PID_ShooterAtSpeed());
		addSequential(new DriveElevatorConst(RobotMap.constVoltage),2);
		addSequential(new PID_ShooterPause());
	}

}
