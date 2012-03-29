package frc2168.commands;

public class PegLegDown extends CommandBase {

	public PegLegDown()
	{
		//requires the driveTrain subsystems 
		requires(pegleg);
	}
	
	protected void initialize() {
		// TODO Auto-generated method stub
	}
	
	protected void execute() 
	{
		pegleg.pegUp();
	}

	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return true;
	}

	protected void end() {
		// TODO Auto-generated method stub

	}

	protected void interrupted() {
		// TODO Auto-generated method stub

	}

}
