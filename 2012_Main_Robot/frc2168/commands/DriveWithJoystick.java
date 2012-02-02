package frc2168.commands;

public class DriveWithJoystick extends CommandBase {

	public DriveWithJoystick()
	{
		//requires the driveTrain subsystems 
		requires(driveTrain);
	}
	
	protected void initialize() {
		// TODO Auto-generated method stub
	}
	
	protected void execute() 
	{
		//every time this command is called we drive the
		//driveTrain with tank drive
		driveTrain.TankDrive(oi.getLeftSpeed(), oi.getRightSpeed());
	}

	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

	protected void end() {
		// TODO Auto-generated method stub

	}

	protected void interrupted() {
		// TODO Auto-generated method stub

	}

}
