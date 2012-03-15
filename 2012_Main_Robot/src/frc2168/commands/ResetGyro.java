package frc2168.commands;

import edu.wpi.first.wpilibj.command.Command;

public class ResetGyro extends CommandBase {
	

	public ResetGyro(){
		requires(driveTrain);
	}
	
	
	protected void initialize() {
		
		// TODO Auto-generated method stub

	}

	protected void execute() {
		
		driveTrain.resetGyro();
		
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
