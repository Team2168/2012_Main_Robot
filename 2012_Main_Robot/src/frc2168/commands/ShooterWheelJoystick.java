package frc2168.commands;



public class ShooterWheelJoystick extends CommandBase {

	public ShooterWheelJoystick(){
		requires(hood);
	}
	
	protected void initialize(){


	}

	protected void execute(){
		hood.spinMotor(oi.getAuxLeftStick());
	
	}
		
	

	protected boolean isFinished() {
		return false;
	}

	protected void end() {
		// TODO Auto-generated method stub

	}

	protected void interrupted() {
		// TODO Auto-generated method stub

	}

}
