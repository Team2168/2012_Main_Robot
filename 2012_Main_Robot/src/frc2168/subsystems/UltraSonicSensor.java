package frc2168.subsystems;

import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc2168.RobotMap;

public class UltraSonicSensor extends Subsystem {
	
	Ultrasonic ultraSonicSensor;


	public UltraSonicSensor() {
		ultraSonicSensor = new Ultrasonic(RobotMap.ultraSonicSensorInput,RobotMap.ultraSonicSensorOutput);

	}
	
	public double ultraSonicReading(){
		return ultraSonicSensor.getRangeInches();
	}
	
	protected void initDefaultCommand() {
		
		// TODO Auto-generated method stub
		

	}
	
	

}
