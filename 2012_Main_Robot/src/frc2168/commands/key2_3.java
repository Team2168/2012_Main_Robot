package frc2168.commands;

import frc2168.RobotMap;

public class key2_3 extends CommandBase {

	private highGoalKey high;
	private midGoalKey mid;

	public key2_3(){

	}
	protected void initialize() {
		// TODO Auto-generated method stub

	}

	protected void execute() {
		// TODO Auto-generated method stub
		if(oi.ioDigital1.get()){
			if(oi.ioDigital10.get() == RobotMap.HIGH_GOAL){
				high = new highGoalKey();
				high.start();
			}

			if(oi.ioDigital10.get() == RobotMap.MIDDLE_GOAL){
				mid = new midGoalKey();
				mid.start();
			}
		}
		else{
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
