package frc2168.commands;

public class ShiftGearsLowToHigh extends CommandBase {

	public ShiftGearsLowToHigh()
	{
		//requires the driveTrain subsystems 
		requires(driveTrain);
	}
	
	protected void initialize() {
		// TODO Auto-generated method stub
	}
	
	protected void execute() 
	{
		driveTrain.shiftGearsLowToHigh();
	}

	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return driveTrain.gearIsHigh();
	}

	protected void end() {
		// TODO Auto-generated method stub

	}

	protected void interrupted() {
		// TODO Auto-generated method stub

	}

}
