package frc2168.commands;

import frc2168.RobotMap;
import frc2168.commands.CommandBase;

public class GetCameraDistance extends CommandBase {
	
	//distance, speed
	private double[][] distanceToSpeed = {
			{0.00, 0.00},
			{1.00, 1.00}
			
	};
	
	public GetCameraDistance(){
		requires(camera);
	}

	protected void initialize() {
		// TODO Auto-generated method stub

	}

	protected void execute() {
		//return the distance to the top hoop
		double distance = camera.getDistance(RobotMap.kTop);
		int tableLength = distanceToSpeed.length;
		for(int i=0; i<tableLength; i++){
			if(distance == distanceToSpeed[i][0]){
				hood.shooterWheelController.setSp(distanceToSpeed[i][1]);
				hood.spinMotor(hood.shooterWheelController.getCo());
			}
		}
	}

	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return hood.shooterWheelController.isEnabled()==false;
	}

	protected void end() {
		// TODO Auto-generated method stub

	}

	protected void interrupted() {
		// TODO Auto-generated method stub
		end();
	}

}
