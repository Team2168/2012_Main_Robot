package frc2168.commands;

import frc2168.RobotMap;

public class LowBallSense extends CommandBase {
	
	protected void initialize() {
		// TODO Auto-generated method stub

	}

	protected void execute() {
		if(elevatorFlap.getBottomBallDetector() >= RobotMap.ballPresentVoltage) {
			elevatorFlap.setBackLight(true);
		}
		elevatorFlap.setBackLight(false);
	}

	protected boolean isFinished() {
		return false;	//run forever
	}

	protected void end() {
		// TODO Auto-generated method stub

	}

	protected void interrupted() {
		// TODO Auto-generated method stub

	}

}
