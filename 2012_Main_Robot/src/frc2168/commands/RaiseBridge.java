package frc2168.commands;

public class RaiseBridge extends CommandBase {

	public RaiseBridge(){
		requires(bridgeArm);
	}
	
	protected void initialize() {
		
	}

	protected void execute() {
		bridgeArm.raiseArm();
	}

	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return bridgeArm.isRaise();
	}

	protected void end() {
		// TODO Auto-generated method stub

	}

	protected void interrupted() {
		// TODO Auto-generated method stub

	}

}
