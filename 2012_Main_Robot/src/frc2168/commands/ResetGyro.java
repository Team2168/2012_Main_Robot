package frc2168.commands;

//need this to link gyro reset to button

public class ResetGyro extends CommandBase {

	public ResetGyro(){
		//requires(driveTrain);		//I don't think we actually need to require drivetrain. We don't affect it's motion by resetting Gyro
	}
	
	protected void initialize() {
		
	}

	protected void execute() {
		driveTrain.resetGyro();
	}

	protected boolean isFinished() {
		return false;
	}

	protected void end() {

	}

	protected void interrupted() {

	}

}
