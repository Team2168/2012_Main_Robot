package frc2168.commands;

import frc2168.RobotMap;
import frc2168.nPointAveragor;

public class DriveLiftUntilNoBall extends CommandBase {

	boolean ball;
	
	//Balls being sensed -rough estimates- can range from 1.5" to 3" from sensor:
	//			1.5" -->  ~2.9v
	//			3"   -->  ~1.7v
	//no ball	11"  -->  ~0.42v
	private static double ballPresentVoltage;	//the voltage threshold at
													// which a ball is considered present
	private static double liftVoltage;
	
	public DriveLiftUntilNoBall(){
		requires(elevatorFlap);

		ballPresentVoltage = RobotMap.ballPresentVoltage;
		liftVoltage = RobotMap.liftVoltage;
		
		//Before doing anything, figure out if a ball is present
		if(elevatorFlap.getTopBallDetector() > ballPresentVoltage){
			ball = true;
		} else {
			ball = false;
		}
	}
	
	protected void initialize() {
		//SmartDashboard.putData("scheduler", Scheduler.getInstance());
	}

	double volt;
	protected void execute() {
		volt = elevatorFlap.getTopBallDetector();
		//System.out.println("xxx " + volt);
		
		if(volt >= ballPresentVoltage){	//if a ball is present
			elevatorFlap.setBallElevatorSpeed(liftVoltage);			//run the lift to get rid of ball
			ball = true;
		} else{												//if a ball isn't present
			elevatorFlap.setBallElevatorSpeed(0);	//drive up
			ball = false;
		}
	}

	protected boolean isFinished() {
		//we're done when there is no ball present
		return !ball;
	}

	protected void end() {
		// TODO Auto-generated method stub

	}

	protected void interrupted() {
		// TODO Auto-generated method stub

	}

}
