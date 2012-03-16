package frc2168.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc2168.RobotMap;

public class fender2_3 extends CommandBase {

	private highGoalFender high;
	private midGoalFender mid;

	public fender2_3(){

	}

	protected void initialize() {
		// TODO Auto-generated method stub

	}

	protected void execute() {
		// TODO Auto-generated method stub
		if(oi.ioDigital1.get() == RobotMap.AUTO_MODE){
			if(oi.ioDigital10.get() == RobotMap.HIGH_GOAL){
				high = new highGoalFender();
				high.start();
			}
			if(oi.ioDigital10.get() == RobotMap.MIDDLE_GOAL){
				mid = new midGoalFender();
				mid.start();
			}
		}
		if(oi.ioDigital1.get() == RobotMap.MANUAL_MODE){
			end();
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
