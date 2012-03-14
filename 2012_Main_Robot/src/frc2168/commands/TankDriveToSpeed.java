package frc2168.commands;

/**
 * 
 * @author Kevin Harrilal, First Robotics Team 2168; Repurposed and edited by Sultan Jilani
 *<br><br>
 *The TankDriveToSpeed class is a Command apart of a Command Base Robot. The purpose of this class is interface
 *to a DriveTrain Object and command the DriveTrain based on direct input, providing finer control.
 *
 *This Class Requires the singleton DriveTrain Object implemented for a particular Robot and uses the TankDrive method
 *implemented within the DriveTrain Object.
 */
public class TankDriveToSpeed extends CommandBase {

	/**
	 * Default Constructor for this Command. This Constructor is used to place a lock
	 * on the DriveTrain singleton, so that only commands from this Object will be processed.
	 */
	
	double lSetPoint;
	double rSetPoint;
	
	public TankDriveToSpeed(double lSpeed, double rSpeed)
	{
		//requires the driveTrain subsystems which was created in CommandBase
		requires(driveTrain);
		lSetPoint=lSpeed;
		rSetPoint=rSpeed;
	}
	
	protected void initialize() {
		// TODO Auto-generated method stub
	}
	
	/**
	 * This method is called repeatedly while this command is issued. This command makes a called to the
	 * TankDrive() method implemented by the DriveTrain subsystem, and supplies the method with the given values.
	 */
	protected void execute() 
	{
		//every time this command is called we drive the
		//driveTrain with tank drive using input given
		driveTrain.TankDrive(lSetPoint,rSetPoint);
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
