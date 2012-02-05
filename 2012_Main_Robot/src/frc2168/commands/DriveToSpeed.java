package frc2168.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.networktables.NetworkTableKeyNotDefined;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class DriveToSpeed extends CommandBase {

	public DriveToSpeed(){
		// TODO Auto-generated method stub
		requires(driveTrain);
	}
	
	protected void initialize() {
		// TODO Auto-generated method stub

	}

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
