package frc2168.commands;

import frc2168.RobotMap;

public class key2_3 extends CommandBase {

	private highGoalKey high;
	private midGoalKey mid;

	public key2_3(){
		high = new highGoalKey();
		mid = new midGoalKey();
		requires(buttonBox);
		requires(hood);
	}
	protected void initialize() {
		// TODO Auto-generated method stub
	}

	protected void execute() 
	{
		// TODO Auto-generated method stub
			if(!oi.auxButtonReset.get())
			{
				high.start();
			}
			else
			{
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
		this.cancel();
	}

	protected void interrupted() {
		// TODO Auto-generated method stub
		this.end();
	}

}
