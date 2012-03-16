package frc2168.commands;

import frc2168.RobotMap;



public class ShooterWheelWithPot extends CommandBase {

	public ShooterWheelWithPot(){
		requires(hood);
	}
	
	protected void initialize(){


	}

	protected void execute(){
		if(oi.ioDigital1.get())
		hood.spinMotor(-(oi.ioBoard.getNormalizedAnalog(RobotMap.shooterWheelCoursePSOC)*.9+oi.ioBoard.getNormalizedAnalog(RobotMap.shooterWheelFinePSOC)*.1));
	
	}
		
	

	protected boolean isFinished() {
		return false;
	}

	protected void end() {
		// TODO Auto-generated method stub

	}

	protected void interrupted() {
		// TODO Auto-generated method stub

	}

}
