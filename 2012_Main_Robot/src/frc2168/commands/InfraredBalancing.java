package frc2168.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc2168.RobotMap;
import frc2168.subsystems.DriveTrain;;


public class InfraredBalancing extends CommandBase {
	
	
	
	public InfraredBalancing(){
		
		requires(driveTrain);
	
	}

	
	
	protected void initialize() {
		// TODO Auto-generated method stub

	}

	protected void execute() {
		// TODO Auto-generated method stub
		
		if(driveTrain.InfraredSensorRange()> RobotMap.maxDist){
			driveTrain.TankDrive(0.0, 0.0);
			}
		if(driveTrain.InfraredSensorRange()> RobotMap.minDist){
			driveTrain.TankDrive(0.0, 0.0);
		}
		
		
		if(driveTrain.driveForward() && driveTrain.RobotTiltUp()){
			
			driveTrain.TankDrive(0.0122*driveTrain.InfraredSensorRange()+.2734, 0.0122*driveTrain.InfraredSensorRange()+.2734);
			
		}else if (driveTrain.driveBackward() && driveTrain.RobotTiltDown()){
			
			driveTrain.TankDrive(-0.0167*driveTrain.InfraredSensorRange()+0.2001, -0.0167*driveTrain.InfraredSensorRange()+0.2001);			
			
		}else{
			driveTrain.TankDrive(0.0, 0.0);
		}
		

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
