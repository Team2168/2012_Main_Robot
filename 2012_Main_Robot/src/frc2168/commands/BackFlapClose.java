package frc2168.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc2168.RobotMap;

public class BackFlapClose extends CommandBase {

	public BackFlapClose(){
		requires(elevatorFlap);
	}
	protected void initialize() {
		// TODO Auto-generated method stub

	}

	protected void execute() {
			elevatorFlap.backFlapClose();
		}
			
		// TODO Auto-generated method stub


	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return elevatorFlap.isClose();
	}

	protected void end() {
		// TODO Auto-generated method stub

	}

	protected void interrupted() {
		// TODO Auto-generated method stub

	}

}
