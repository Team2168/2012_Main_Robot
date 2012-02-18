package frc2168.subsystems;

import frc2168.RobotMap;
import frc2168.PIDController.AverageEncoder;
import frc2168.PIDController.PIDSpeed;
import frc2168.commands.ShooterWheelJoystick;
import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.CounterBase;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStationLCD;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.can.CANTimeoutException;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Hood extends Subsystem {
	
	//We instantiate all parameters to interface with the hardware of the DriveTrain
	
	//////////////////////////////////////////////////////////////////////
	// Declare all CAN Motors associated with the Drive Train
	// We use two motors to drive the shooter wheel
	CANJaguar shooterWheel;
	CANJaguar shooterWheel2;
	
	//////////////////////////////////////////////////////////////////////
	//Declare Solenoid for hood
	DoubleSolenoid hoodActuator;
	
	//////////////////////////////////////////////////////////////////////
	//Declare sensors
	AverageEncoder shooterWheelEncoder;
	

	/////////////////////////////////////////////////////////////////////	
	//PID Controllers
	public PIDSpeed shooterWheelController;
	
	
	
	public Hood(){
		
		//instantiate solenoid actuator
		hoodActuator = new DoubleSolenoid(RobotMap.hoodSolenoidPortFwd,RobotMap.hoodSolenoidPortReverse); 
		
		
		//instantiate CAN motors
		try{
			shooterWheel = new CANJaguar(RobotMap.shooterWheelCANID);
			shooterWheel2 = new CANJaguar(RobotMap.shooterWheel2CANID);
		}
		catch(CANTimeoutException e){
			e.printStackTrace();
			RobotMap.driverstation = DriverStationLCD.getInstance();
			RobotMap.driverstation.println(DriverStationLCD.Line.kMain6, 1, "Error initializing");
			RobotMap.driverstation.println(DriverStationLCD.Line.kUser2, 1, "Jag in Hood");
			RobotMap.driverstation.updateLCD();
		}
		
		//instantiate encoder in 1x mode
		shooterWheelEncoder = new AverageEncoder(RobotMap.shooterWheelEncoderID_A, RobotMap.shooterWheelEncoderID_B,false,CounterBase.EncodingType.k1X, 10);
		
		//Set Encoder Paramters
		shooterWheelEncoder.setDistancePerPulse(RobotMap.shooterEncoderDistPerTick);
		shooterWheelEncoder.setMinRate(RobotMap.shooterEncoderMinRate);
		shooterWheelEncoder.setReverseDirection(RobotMap.shooterEncoderReverse);
		shooterWheelEncoder.start();
		
		//Spawn New PID Speed Controller with PID Gains as specified in ROBOT MAP
		shooterWheelController=new PIDSpeed("shooterController",RobotMap.shooterP,RobotMap.shooterI,RobotMap.shooterD, shooterWheelEncoder,RobotMap.shooterPIDPeriod);
		
	}
	
	protected void initDefaultCommand() {
		setDefaultCommand(new ShooterWheelJoystick());
	}
	
	public void spinMotor(double speed){
		try {
			shooterWheel.setX(speed);
			shooterWheel2.setX(speed);
		} catch (CANTimeoutException e) {
			e.printStackTrace();
			RobotMap.driverstation = DriverStationLCD.getInstance();
			RobotMap.driverstation.println(DriverStationLCD.Line.kMain6, 1, "Error setting");
			RobotMap.driverstation.println(DriverStationLCD.Line.kUser2, 2, "Jag in Hood");
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