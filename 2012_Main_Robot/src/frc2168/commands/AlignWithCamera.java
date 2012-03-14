package frc2168.commands;

import frc2168.Camera.Camera;
import frc2168.RobotMap;

public class AlignWithCamera extends CommandBase {

	Camera camera;
	
	public AlignWithCamera(){
		camera = new Camera();
		requires(driveTrain);
		requires(hood);
	}
	
	protected void initialize() {
		// TODO Auto-generated method stub

	}

	protected void execute() {
		// TODO Auto-generated method stub
		
		//align robot with camera
		//assume ioDigital5 is the correct button
		//assume true for 3 false for 2
		boolean targetInput = oi.ioDigital5.get();
		double angleToTarget;
		if(targetInput){
			angleToTarget = camera.getAngle(RobotMap.kTop);
			//spins hood up to speed while turning
			hood.spinMotor(camera.getDistance(RobotMap.kTop));
		}
		else{
			angleToTarget = camera.getAngle(RobotMap.kRight);
			//spins hood up to speed while turning
			hood.spinMotor(camera.getDistance(RobotMap.kRight)); 
		}
		//assume positive needs to turn left
		if(angleToTarget > RobotMap.visionAngleThreshold){//4
			driveTrain.TankDrive(-RobotMap.slowCameraAlignSpeed, RobotMap.slowCameraAlignSpeed);
		}
		//assume positive needs to turn right
		if(angleToTarget < -RobotMap.visionAngleThreshold){//4
			driveTrain.TankDrive(RobotMap.slowCameraAlignSpeed, -RobotMap.slowCameraAlignSpeed);
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
