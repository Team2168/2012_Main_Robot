package frc2168.commands;


public class LowerHood extends CommandBase {
	
private boolean calledFromShooter;
	
	public LowerHood()
	{
		//requires(hood);
		this.calledFromShooter = false;
		
	}
	
	public LowerHood(boolean calledFromShooter)
	{
		//requires(hood);
		this.calledFromShooter = calledFromShooter;
		
	}

	protected void initialize() {
		
	}

	protected void execute() {
		if(!oi.ioDigital1.get()||calledFromShooter)
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
