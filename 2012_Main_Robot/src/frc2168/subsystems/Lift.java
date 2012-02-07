package frc2168.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.AnalogChannel;

public class Lift extends Subsystem {
	static AnalogChannel ballDetector = new AnalogChannel(2);
	

	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
	

	}
	
	public static double BallPresent() {
		
		// System.out.println("Adjusted BallPresent method output: " + ballDetector.getVoltage()/.0098);
		return (ballDetector.getVoltage()/.0098);
		
	}

}
