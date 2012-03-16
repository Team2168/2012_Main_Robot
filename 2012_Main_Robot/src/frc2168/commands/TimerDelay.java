package frc2168.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc2168.RobotMap;

public class TimerDelay extends CommandBase {
	
	int chan = 0;
	
	public TimerDelay(int channel){
		chan=channel;
	}

	protected void initialize() {
		// TODO Auto-generated method stub
		
	}

	protected void execute() {
		// TODO Auto-generated method stub
		
		oi.ioBoard.getTimeDelay(chan, RobotMap.maxValue, RobotMap.minValue);

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
