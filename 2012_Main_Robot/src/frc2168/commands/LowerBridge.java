package frc2168.commands;

public class LowerBridge extends CommandBase {

	protected void initialize() {
		requires(bridgeArm);
	}

	protected void execute() {
		bridgeArm.lowerArm();
	}

	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return bridgeArm.isLow();
	}

	protected void end() {
		// TODO Auto-generated method stub

	}

	protected void interrupted() {
		// TODO Auto-generated method stub

	}

}
