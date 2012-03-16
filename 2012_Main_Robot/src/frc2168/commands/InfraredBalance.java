package frc2168.commands;

public class InfraredBalance extends CommandBase {
	
	public InfraredBalance(){		
		requires(driveTrain);
	}

	
	protected void initialize() {
		// TODO Auto-generated method stub

	}

	/**
	 * follow another robot on the bridge
	 */
	protected void execute() {
		double driveSpeed = 0.0;
		
		if(driveTrain.driveForward()){				//Does the robot need to drive forward to maintain distance?
			//which way is the ramp tilted?
			//  changes our min/max speed
			if(driveTrain.tiltedUp()){				//need to drive uphill
				//TODO: ADD call to pointslope w/ min/max presets
			} else if (driveTrain.tiltedDown()) {	//need to drive downhill
				//TODO: ADD call to pointslope w/ min/max presets
			} else {								//we're balanced.. don't move!
				driveTrain.TankDrive(driveSpeed, driveSpeed);
			}
		} else if(driveTrain.driveBackward()){		//Does the robot need to drive backward to maintain distance?
			//which way is the ramp tilted?
			//  changes our min/max speed
			if(driveTrain.tiltedUp()){				//need to drive uphill
				//TODO: ADD call to pointslope w/ min/max presets
			} else if (driveTrain.tiltedDown()) {	//need to drive downhill
				//TODO: ADD call to pointslope w/ min/max presets
			} else {								//we're balanced.. don't move!
				driveTrain.TankDrive(driveSpeed, driveSpeed);
			}
		
		} else {									//Stop, we're in the sweet spot
			driveTrain.TankDrive(0.0, 0.0);
		}
		
		
		/*if(driveTrain.driveForward() && driveTrain.RobotTiltedUp()){
			
			driveTrain.TankDrive(0.0122*driveTrain.InfraredSensorRange()+.2734, 0.0122*driveTrain.InfraredSensorRange()+.2734);
			
		}else if (driveTrain.driveBackward() && driveTrain.tiltedDown()){
			
			driveTrain.TankDrive(-0.0167*driveTrain.InfraredSensorRange()+0.2001, -0.0167*driveTrain.InfraredSensorRange()+0.2001);			
			
		}else{
			driveTrain.TankDrive(0.0, 0.0);
		}*/
		

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

	private double pointSlope(double x1, double y1, double x2, double y2, double input) {
		double m = (y1 - y2) / (x1 - x2);
		double b = y1 - (m * x1);
		
		return m * input +b;
	}
}
