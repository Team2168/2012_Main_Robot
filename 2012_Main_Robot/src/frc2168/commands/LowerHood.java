package frc2168.commands;


public class LowerHood extends CommandBase {

	protected void initialize() {
		requires(hood);
	}

	protected void execute() {
		hood.lowerHood();
	}

	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return hood.isLow();
	}

	protected void end() {
		// TODO Auto-generated method stub

	}

	protected void interrupted() {
		// TODO Auto-generated method stub

	}

}
