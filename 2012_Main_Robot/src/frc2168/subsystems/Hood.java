package frc2168.subsystems;

import frc2168.RobotMap;
import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.can.CANTimeoutException;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Hood extends Subsystem {
	
	CANJaguar shooterWheel;
	CANJaguar shooterWheel2;
	Solenoid hoodActuator;
	Encoder shooterWheelEncoder;
	
	public Hood(){
		try{
			shooterWheel = new CANJaguar(RobotMap.shooterWheelCANID);
			shooterWheel2 = new CANJaguar(RobotMap.shooterWheel2CANID);
		}
		catch(CANTimeoutException e){
			e.printStackTrace();
		}
		shooterWheelEncoder = new Encoder(RobotMap.shooterWheelEncoderID_A, RobotMap.shooterWheelEncoderID_B);
	}
	
	protected void initDefaultCommand() {
		
	}
	
	public void spinMotor(double speed){
		
	}
	
	public void actuateHood(){
		
	}

}