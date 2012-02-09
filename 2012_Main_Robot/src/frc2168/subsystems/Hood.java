package frc2168.subsystems;

import frc2168.RobotMap;
import frc2168.commands.DriveShooterWheel;
import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStationLCD;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.can.CANTimeoutException;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Hood extends Subsystem {
	
	CANJaguar shooterWheel;
	CANJaguar shooterWheel2;
	DoubleSolenoid hoodActuator;
	Encoder shooterWheelEncoder;
	
	
	public Hood(){
		
		hoodActuator = new DoubleSolenoid(RobotMap.hoodSolenoidPortFwd,RobotMap.hoodSolenoidPortReverse); 
		
		try{
			shooterWheel = new CANJaguar(RobotMap.shooterWheelCANID);
			shooterWheel2 = new CANJaguar(RobotMap.shooterWheel2CANID);
		}
		catch(CANTimeoutException e){
			e.printStackTrace();
			RobotMap.driverstation = DriverStationLCD.getInstance();
			RobotMap.driverstation.println(DriverStationLCD.Line.kMain6, 1, "Error initializing Jag in hood");
			RobotMap.driverstation.updateLCD();
		}
		shooterWheelEncoder = new Encoder(RobotMap.shooterWheelEncoderID_A, RobotMap.shooterWheelEncoderID_B);
		
		//Set Encoder Paramters
		shooterWheelEncoder.setDistancePerPulse(RobotMap.shooterEncoderDistPerTick);
		shooterWheelEncoder.setMinRate(RobotMap.shooterEncoderMinRate);
		shooterWheelEncoder.start();
	}
	
	protected void initDefaultCommand() {
		setDefaultCommand(new DriveShooterWheel());
	}
	
	public void spinMotor(double speed){
		try {
			shooterWheel.setX(speed);
			shooterWheel2.setX(speed);
		} catch (CANTimeoutException e) {
			e.printStackTrace();
			RobotMap.driverstation = DriverStationLCD.getInstance();
			RobotMap.driverstation.println(DriverStationLCD.Line.kMain6, 1, "Error setting Jag in hood");
			RobotMap.driverstation.updateLCD();
		}
	}
	
	public void lowerHood(){
		hoodActuator.set(DoubleSolenoid.Value.kForward);
	}
	
	public boolean isLow()
	{
		return hoodActuator.get()==DoubleSolenoid.Value.kForward;
	}

	public void raiseHood(){
		hoodActuator.set(DoubleSolenoid.Value.kReverse);
	}
	
	public boolean isRaise()
	{
		return hoodActuator.get()==DoubleSolenoid.Value.kReverse;
	}
}