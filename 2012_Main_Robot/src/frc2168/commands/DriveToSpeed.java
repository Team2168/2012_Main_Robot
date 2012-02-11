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
<<<<<<< OURS
		driveTrain.speedController.enDebug();
		driveTrain.speedController.Enable();

=======
		driveTrain.leftSpeedController.Enable();
		driveTrain.rightSpeedController.Enable();
		driveTrain.leftSpeedController.enDebug();
		driveTrain.rightSpeedController.enDebug();
>>>>>>> THEIRS
	}

	/**
	 * This method is called repeatedly while this command is issued. This command makes a called to the
	 * PIDSpeedOutput() method implemented by the DriveTrain subsystem, and supplies the method with speed values
	 * from the PID Speed Controller implemented within the DriveTrain object{@link OI}.
	 * <br><br>
	 * This Command takes live inputs from the SmartDashboard.
	 */
	protected void execute() 
	{
		// TODO Auto-generated method stub

		if(driveTrain.leftSpeedController.isDebugEnabled())
		{
			try 
			{
				//set SpeedController Inputs Based on values from the DashBoard
				driveTrain.leftSpeedController.setSp(SmartDashboard.getDouble(driveTrain.leftSpeedController.getName()+"_setPoint"));
				driveTrain.leftSpeedController.setMaxPosOutput(SmartDashboard.getDouble(driveTrain.leftSpeedController.getName()+"_max Pos Output"));
				driveTrain.leftSpeedController.setMaxNegOutput(SmartDashboard.getDouble(driveTrain.leftSpeedController.getName()+"_max Neg Output"));
				driveTrain.leftSpeedController.setMinPosOutput(SmartDashboard.getDouble(driveTrain.leftSpeedController.getName()+"_min Pos Output"));
				driveTrain.leftSpeedController.setMinNegOutput(SmartDashboard.getDouble(driveTrain.leftSpeedController.getName()+"_min Neg Output"));
				driveTrain.leftSpeedController.setR(SmartDashboard.getDouble(driveTrain.leftSpeedController.getName()+"_deriv Filter Constant"));
				driveTrain.leftSpeedController.setAcceptErrorDiff(SmartDashboard.getDouble(driveTrain.leftSpeedController.getName()+"_acceptable Err"));
						
			} 
			catch (NetworkTableKeyNotDefined e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();

			}
		
			//copy values from left to right
			driveTrain.rightSpeedController.setSp(driveTrain.leftSpeedController.getSp());
			driveTrain.rightSpeedController.setMaxPosOutput(driveTrain.leftSpeedController.getMaxPosOutput());
			driveTrain.rightSpeedController.setMaxNegOutput(driveTrain.leftSpeedController.getMaxNegOutput());
			driveTrain.rightSpeedController.setMinPosOutput(driveTrain.leftSpeedController.getMinPosOutput());
			driveTrain.rightSpeedController.setMinNegOutput(driveTrain.leftSpeedController.getMinNegOutput());
			driveTrain.rightSpeedController.setR(driveTrain.leftSpeedController.getR());
			driveTrain.rightSpeedController.setAcceptErrorDiff(driveTrain.leftSpeedController.getAcceptErrorDiff());
			
	
			//drive based on PID loop
			driveTrain.TankDrive(driveTrain.leftSpeedController.getCo(),driveTrain.rightSpeedController.getCo());

		}
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
		driveTrain.leftSpeedController.Pause();
		driveTrain.rightSpeedController.Pause();
	}

	/**
	 * 
	 */
	protected void interrupted() {
		// TODO Auto-generated method stub

	}

}
