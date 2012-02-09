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
		/*
		 * if aux button1 is pressed then Close flap
		 */
		if (oi.auxButton1.get()){
			elevatorFlap.backFlapClose();
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
