package frc2168.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc2168.RobotMap;
import frc2168.subsystems.*;

public class DriveElevatorJoystick extends CommandBase {

	public DriveElevatorJoystick(){
		requires(elevatorFlap);
	}
	
	protected void initialize() {
		// TODO Auto-generated method stub
	}

	/**
	 * This method is called repeatedly while this command is issued. This
	 * command makes a called to the TankDrive() method implemented by the
	 * DriveTrain subsystem, and supplies the method with Joystick values from
	 * the Joystick implemented in {@link OI}.
	 */
	
	protected void execute() {
		
		
		elevatorFlap.setBallElevatorSpeed(-oi.getAuxLeftStick());

	}

	/**
	 * @return A Boolean which is True when the Command is Finished and False
	 *         when it is not.
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
