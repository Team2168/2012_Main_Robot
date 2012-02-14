package frc2168.commands;

public class LowerBridge extends CommandBase {
	
	public LowerBridge(){
		requires(bridgeArm);
	}

	protected void initialize() {
		
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
