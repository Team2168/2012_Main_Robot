package frc2168.commands;


public class RaiseHood extends CommandBase {

	protected void initialize() {
		requires(hood);
	}

	protected void execute() {
		hood.raiseHood();
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