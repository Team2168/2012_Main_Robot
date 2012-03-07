package frc2168.commands;

import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc2168.OI;
import frc2168.RobotMap;
import frc2168.subsystems.*;

public class DriveToFenderAutoCommand extends CommandBase {

//	private static final Subsystem ultraSonicSensor = null;

	public DriveToFenderAutoCommand(){
		requires(driveTrain);
		requires(ultraSonicSensor);
	}
	
	protected void initialize() {
		
		
		// TODO Auto-generated method stub

	}

	protected void execute() {
		
		
		if (ultraSonicSensor.ultraSonicReading() < 5){
			driveTrain.TankDrive(0.5,0.5);
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
