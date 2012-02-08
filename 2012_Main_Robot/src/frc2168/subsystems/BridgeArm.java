package frc2168.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc2168.RobotMap;

public class BridgeArm extends Subsystem {

	DoubleSolenoid raiseLowerSolenoid;

	public BridgeArm(){
		raiseLowerSolenoid = new DoubleSolenoid(RobotMap.bridgeArmSolenoidForwardChannel, RobotMap.bridgeArmSolenoidReverseChannel);
	}
	
	protected void initDefaultCommand() {

	}
	
	public void lowerArm(){
		raiseLowerSolenoid.set(DoubleSolenoid.Value.kForward);
	}
	
	public void raiseArm(){
		raiseLowerSolenoid.set(DoubleSolenoid.Value.kReverse);
	}

}
