package frc2168.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Relay.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc2168.RobotMap;

public class PegLeg extends Subsystem {

	Relay PegLeGControl;

	public PegLeg(){
		PegLeGControl = new Relay(RobotMap.PegLegRelay);
	}
	
	protected void initDefaultCommand() {

	}
	
	public void pegDown(){
		PegLeGControl.set(Relay.Value.kReverse);
	}
	
//	public boolean isLow()
//	{
//		//return PegLeGControl.get()==DoubleSolenoid.Value.kForward;
//	}
	
	public void pegUp(){
		PegLeGControl.set(Relay.Value.kForward);
	}
//	public boolean isRaise()
//	{
//		//return PegLeGControl.get()==DoubleSolenoid.Value.kReverse;
//	}

}
