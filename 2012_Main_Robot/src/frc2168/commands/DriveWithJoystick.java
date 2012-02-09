package frc2168.commands;

/**
 * 
 * @author Kevin Harrilal, First Robotics Team 2168
 *<br><br>
 *The DriveWithJoystics class is a Command apart of a Command Base Robot. The purpose of this class is interface
 *to a DriveTrain Object and command the DriveTrain based on input from a JoyStick using the Core Methods implemented
 *by the DriveTrain Object.
 *
 *This Class Requires the singleton DriveTrain Object implemented for a particular Robot and uses the TankDrive method
 *implemented within the DriveTrain Object.
 */
public class DriveWithJoystick extends CommandBase {

	/**
	 * Default Constructor for this Command. This Constructor is used to place a lock
	 * on the DriveTrain singleton, so that only commands from this Object will be processed.
	 */
	public DriveWithJoystick()
	{
		//requires the driveTrain subsystems which was created in CommandBase
		requires(driveTrain);
	}
	
	protected void initialize() {
		// TODO Auto-generated method stub
	}
	
	/**
	 * This method is called repeatedly while this command is issued. This command makes a called to the
	 * TankDrive() method implemented by the DriveTrain subsystem, and supplies the method with Joystick values
	 * from the Joystick implemented in {@link OI}.
	 */
	protected void execute() 
	{
		//every time this command is called we drive the
		//driveTrain with tank drive using the Driver Joysticks
		driveTrain.TankDrive(oi.getDriveLeftAxis(), oi.getDriveRightAxis());
	}

	/**
	 * @return A Boolean which is True when the Command is Finished and False when it is not.
	 */
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * 
	 */
	protected void end() {
		// TODO Auto-generated method stub

	}
	/**
	 * 
	 */
	protected void interrupted() {
		// TODO Auto-generated method stub

	}

}
