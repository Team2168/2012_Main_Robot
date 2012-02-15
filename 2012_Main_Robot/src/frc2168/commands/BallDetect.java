package frc2168.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.AnalogChannel;

public class BallDetect extends CommandBase {
	
	public BallDetect(){
		
	}

	protected void initialize() {
		
		
		// TODO Auto-generated method stub

	}

	protected void execute() {
		
		System.out.println("Voltage " + oi.ballDetector.getVoltage());		//Print voltage value
		
		
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
