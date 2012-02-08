package frc2168.commands;

public class ShiftGearsHighToLow extends CommandBase {

	public ShiftGearsHighToLow()
	{
		//requires the driveTrain subsystems 
		requires(driveTrain);
	}
	
	protected void initialize() {
		// TODO Auto-generated method stub
	}
	
	protected void execute() 
	{
		driveTrain.shiftGearsHighToLow();
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
