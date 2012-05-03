package frc2168.subsystems;

import frc2168.CommandBasedRobot;
import frc2168.RobotMap;
import frc2168.PIDController.AverageEncoder;
import frc2168.PIDController.PIDSpeed;
import frc2168.commands.DriveElevatorJoystick;
import frc2168.commands.DriveWithJoystick;
import frc2168.commands.ShooterWheelJoystick;
import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.CounterBase;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStationLCD;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Jaguar;
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
	
	//File Line Counter
	int count =0;
	
	
	
	public Hood(){
		
		//instantiate solenoid actuator
		hoodActuator = new DoubleSolenoid(RobotMap.hoodSolenoidPortFwd,RobotMap.hoodSolenoidPortReverse); 
		
		
		try
		{
			shooterWheel = new CANJaguar(RobotMap.shooterWheelCANID);
		} catch (CANTimeoutException e)
		{
			System.out.println("Error Initializing Shooter Jag");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try
		{
			shooterWheel2 = new CANJaguar(RobotMap.shooterWheel2CANID);
		} catch (CANTimeoutException e)
		{
			System.out.println("Error Initializing Shooter Jag");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
		
		//instantiate PWM motors

//			shooterWheel = new Jaguar(RobotMap.shooter1JagPWM);
//			shooterWheel2 = new Jaguar(RobotMap.shooter2JagPWM);
//		
		

		
		//instantiate encoder in 1x mode
		shooterWheelEncoder = new AverageEncoder(RobotMap.shooterWheelEncoderID_A, RobotMap.shooterWheelEncoderID_B,false,CounterBase.EncodingType.k1X, RobotMap.hoodAvgEncoderVal);
		
		//Set Encoder Paramters
		shooterWheelEncoder.setDistancePerPulse(RobotMap.shooterEncoderDistPerTick);
		shooterWheelEncoder.setMinRate(RobotMap.shooterEncoderMinRate);
		shooterWheelEncoder.setReverseDirection(RobotMap.shooterEncoderReverse);
		shooterWheelEncoder.start();
		
		//Spawn New PID Speed Controller with PID Gains as specified in ROBOT MAP
		shooterWheelController=new PIDSpeed("shooterController",RobotMap.shooterP,RobotMap.shooterI,RobotMap.shooterD, shooterWheelEncoder,RobotMap.shooterPIDPeriod);
		shooterWheelController.setAcceptErrorDiff(RobotMap.shooterError);
		shooterWheelController.setSIZE(RobotMap.hoodArraySize);
		shooterWheelController.setPercent(RobotMap.hoodPercent);
	}
	
	protected void initDefaultCommand() {
		setDefaultCommand(new ShooterWheelJoystick());
	
	}
	
	public void spinMotor(double speed){
	
			count++;
			try
			{
				shooterWheel.setX(speed);
				shooterWheel2.setX(speed);
				CommandBasedRobot.out.println(count + "\t" + System.currentTimeMillis() + "\t" + shooterWheel.getOutputVoltage() + "\t" + shooterWheel2.getOutputVoltage() + "\t" + shooterWheel.getOutputCurrent() + "\t" + shooterWheel2.getOutputCurrent() + "\t" + shooterWheelEncoder.getRate());
			} catch (CANTimeoutException e)
			{
				
				System.out.println("error setting jag");
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		
	//System.out.println(speed);
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