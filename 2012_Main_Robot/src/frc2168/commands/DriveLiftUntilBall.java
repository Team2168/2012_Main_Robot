package frc2168.commands;

import frc2168.nPointAveragor;
import edu.wpi.first.wpilibj.DriverStationLCD;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class DriveLiftUntilBall extends CommandBase {
	
	nPointAveragor averager;
	boolean ball;
	
	//Balls being sensed -rough estimates- can range from 1.5" to 3" from sensor:
	//			1.5" -->  ~2.9v
	//			3"   -->  ~1.7v
	//no ball	11"  -->  ~0.42v
	private static double ballPresentVoltage = 1.6;	//the voltage threshold at
													// which a ball is considered present
	private static double liftVoltage = -0.25;
	
	
	
	public DriveLiftUntilBall(){
		requires(elevatorFlap);
		averager = new nPointAveragor(3); //create a new moving average of size n
		
		//Before doing anything, figure out if a ball is present
		if(elevatorFlap.getBallDetector() > ballPresentVoltage){
			ball = true;
		} else {
			ball = false;
		}
	}

	protected void initialize() {
		//SmartDashboard.putData("scheduler", Scheduler.getInstance());
	}

	protected void execute() {
		//Stop lift at one ball
		//averager.putData(elevatorFlap.getBallDetector());
		
		//Print Debug distance voltage data
		//DriverStationLCD.getInstance().println(DriverStationLCD.Line.kMain6, 1, "BallDist = " + Double.toString(elevatorFlap.getBallDetector()));
		//DriverStationLCD.getInstance().updateLCD();
		
		if(elevatorFlap.getBallDetector() >= ballPresentVoltage){	//if a ball is present
			elevatorFlap.setBallElevatorSpeed(0);			//stop the lift
			ball = true;
		} else{												//if a ball isn't present
			elevatorFlap.setBallElevatorSpeed(liftVoltage);	//drive up
			ball = false;
		}
	}

	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return ball;
	}

	protected void end() {
		
	}

	protected void interrupted() {
		// TODO Auto-generated method stub

	}

}
