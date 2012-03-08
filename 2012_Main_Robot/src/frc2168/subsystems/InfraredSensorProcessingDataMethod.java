package frc2168.subsystems;

import com.sun.squawk.util.MathUtils;

import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc2168.RobotMap;

public class InfraredSensorProcessingDataMethod extends Subsystem {

	AnalogChannel InfraredForBalancing;
	
	public InfraredSensorProcessingDataMethod(){
		
		InfraredForBalancing = new AnalogChannel(RobotMap.InfraredSensor);
	}
	
	public double InfraredSensorRange(){
		return MathUtils.pow((30.68)*(InfraredForBalancing.getVoltage()),-1.105);
	}
	
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub

	}

}
