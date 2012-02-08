package frc2168.commands;

import edu.wpi.first.wpilibj.networktables.NetworkTableKeyNotDefined;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * 
 * @author Kevin Harrilal, First Robotics Team 2168
 * *<br><br>
 *The DriveToSpeed class is a Command apart of a Command Base Robot. The purpose of this class is interface
 *to a DriveTrain Object and command the DriveTrain based on the control of a FRC2168 PID Speed Controller using the Core Methods implemented
 *by the DriveTrain Object.
 *
 *This Class Requires the singleton DriveTrain Object implemented for a particular Robot and uses the PIDSpeedOutput method
 *implemented within the DriveTrain Object.
 */
 
public class DriveToSpeed extends CommandBase {

	/**
	 * Default Constructor for this Command. This Constructor is used to place a lock
	 * on the DriveTrain singleton, so that only commands from this Object will be processed.
	 */
	public DriveToSpeed(){
		// TODO Auto-generated method stub
		requires(driveTrain);
	}
	
	protected void initialize() {
		// TODO Auto-generated method stub
		driveTrain.speedController.Enable();

	}

	/**
	 * This method is called repeatedly while this command is issued. This command makes a called to the
	 * PIDSpeedOutput() method implemented by the DriveTrain subsystem, and supplies the method with speed values
	 * from the PID Speed Controller implemented within the DriveTrain object{@link OI}.
	 * <br><br>
	 * This Command takes live inputs from the SmartDashboard.
	 */
	protected void execute() {
		// TODO Auto-generated method stub

		try {
			
			//set SpeedController Inputs Based on values from the DashBoard
			driveTrain.speedController.setSp(SmartDashboard.getDouble(driveTrain.speedController.getName()+"_setPoint"));
			driveTrain.speedController.setMaxPosOutput(SmartDashboard.getDouble(driveTrain.speedController.getName()+"_max Pos Output"));
			driveTrain.speedController.setMaxNegOutput(SmartDashboard.getDouble(driveTrain.speedController.getName()+"_max Neg Output"));
			driveTrain.speedController.setMinPosOutput(SmartDashboard.getDouble(driveTrain.speedController.getName()+"_min Pos Output"));
			driveTrain.speedController.setMinNegOutput(SmartDashboard.getDouble(driveTrain.speedController.getName()+"_min Neg Output"));
			driveTrain.speedController.setR(SmartDashboard.getDouble(driveTrain.speedController.getName()+"_deriv Filter Constant"));
			driveTrain.speedController.setAcceptErrorDiff(SmartDashboard.getDouble(driveTrain.speedController.getName()+"_acceptable Err"));
		
		} catch (NetworkTableKeyNotDefined e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//lets try changing setpoint first
		driveTrain.PIDSpeedOutput(driveTrain.speedController.getCo());
	}

	/**
	 * 
	 */
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return oi.drivestick.getRawButton(2);
	}

	/**
	 * 
	 */
	protected void end() {
		// TODO Auto-generated method stub
		driveTrain.speedController.Pause();

	}

	/**
	 * 
	 */
	protected void interrupted() {
		// TODO Auto-generated method stub

	}

}
