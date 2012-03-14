package frc2168.commands;

import edu.wpi.first.wpilibj.KinectStick;
import edu.wpi.first.wpilibj.command.Command;
import frc2168.*;

public class ShootKinect extends Command {
	
	KinectStick kinect = new KinectStick(1);
	
	protected void initialize() {
		// TODO Auto-generated method stub
		
		new TankDriveToSpeed(kinect.getRawAxis(2),kinect.getRawAxis(5));
		
	}

	protected void execute() {
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
