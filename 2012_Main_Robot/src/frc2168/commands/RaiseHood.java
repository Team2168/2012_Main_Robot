package frc2168.commands;


public class RaiseHood extends CommandBase {
	
	private boolean calledFromShooter;
	
	public RaiseHood()
	{
		//requires(hood);
		this.calledFromShooter = false;
		
	}
	
	public RaiseHood(boolean calledFromShooter)
	{
		//requires(hood);
		this.calledFromShooter = calledFromShooter;
		
	}

	protected void initialize() {
		
	}

	protected void execute() {
		if(!oi.ioDigital1.get()||calledFromShooter)
		hood.raiseHood();
	}

	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return hood.isRaise();
	}

	protected void end() {
		// TODO Auto-generated method stub

	}

	protected void interrupted() {
		// TODO Auto-generated method stub

	}

}
