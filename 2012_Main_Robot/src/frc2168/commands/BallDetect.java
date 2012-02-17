package frc2168.commands;

import frc2168.nPointAveragor;
import edu.wpi.first.wpilibj.AnalogChannel;

public class BallDetect extends CommandBase {
	
	nPointAveragor averager = new nPointAveragor(5);	
	public BallDetect(){
		
		
		
	}

	protected void initialize() {
		
		
		// TODO Auto-generated method stub

	}

	protected void execute() {
		
	//	System.out.println("Voltage " + oi.ballDetector.getVoltage());		//Print voltage value
		
		
		averager.putData(oi.ballDetector.getVoltage());
		
		if(averager.getAverage()>2.0){		//when ball is present (at just below max value)
			
			elevatorFlap.setBallElevatorSpeed(0);
			
		} else if (averager.getAverage()<=2.0){
			
			elevatorFlap.setBallElevatorSpeed(.5);
			
		}
		
		
		// TODO Auto-generated method stub

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
