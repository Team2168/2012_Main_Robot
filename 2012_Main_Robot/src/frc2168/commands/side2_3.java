package frc2168.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc2168.RobotMap;

public class side2_3 extends CommandBase {

	private highGoalSide high;
	private midGoalSide mid;

	public side2_3(){
		high = new highGoalSide();
		mid = new midGoalSide();
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
