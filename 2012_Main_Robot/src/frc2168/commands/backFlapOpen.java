package frc2168.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc2168.RobotMap;

public class backFlapOpen extends CommandBase {
	
	public backFlapOpen(){
		requires(elevatorFlap);
	}

	protected void initialize() {
		// TODO Auto-generated method stub

	}

	protected void execute() {
		/*
		 * if auxbutton 2 is pressed, open the flap
		 */
		if (oi.auxButton2.get()){
			elevatorFlap.backFlapOpen();
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
