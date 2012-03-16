package frc2168.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc2168.RobotMap;

public class fender2_3 extends CommandBase {

	private boolean target;
	private highGoalFender high;
	private midGoalFender mid;
	
	public fender2_3(boolean target){
		this.target = target;
	}
	
	protected void initialize() {
		// TODO Auto-generated method stub

	}

	protected void execute() {
		// TODO Auto-generated method stub
		if(target == RobotMap.HIGH_GOAL){
			high = new highGoalFender();
			high.start();
		}
		if(target == RobotMap.MIDDLE_GOAL){
			mid = new midGoalFender();
			mid.start();
		}
	}

	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

	protected void end() {
		// TODO Auto-generated method stub
		high.cancel();
		mid.cancel();
	}

	protected void interrupted() {
		// TODO Auto-generated method stub

	}

}
