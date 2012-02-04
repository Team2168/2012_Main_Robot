package frc2168.subsystems;

import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.CounterBase;
import edu.wpi.first.wpilibj.DriverStationLCD;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.can.CANTimeoutException;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc2168.PIDSpeed;
import frc2168.RobotMap;
import frc2168.testthread;
import frc2168.commands.DriveWithJoystick;

public class DriveTrain extends Subsystem
{
	//We instantiate all parameters to interface with the hardware of the DriveTrain
	
	//////////////////////////////////////////////////////////////////////
	// Declare all CAN Motors associated with the Drive Train
	// We use two motors for each side of our drive train
		CANJaguar leftMotor1;
	//	CANJaguar rightMotor1;
	//	CANJaguar leftMotor2;
	//	CANJaguar rightMotor2;
	
	//////////////////////////////////////////////////////////////////////
	//Declare Sensors
		Encoder leftMotorEncoder;	
		Encoder	rightMotorEncoder;
	
	//Enable smartDashboard	
		DriverStationLCD driverstation;
		SmartDashboard smartdashboard;
	
	//PID Controller
		PIDSpeed speedController;
		
		long period =40;//40ms loop

	
	

	/**
	 * Default Constructor for DriveTrain Subsystem. This Constructor instantiates
	 * the CAN Jaguar motors for the Drivetrain and all the sensors;
	 */
	public DriveTrain()
	{
		//enable left and right encoders
		leftMotorEncoder = new Encoder(RobotMap.leftDriveTrainEncoder_A,
				RobotMap.leftDriveTrainEncoder_B,false,CounterBase.EncodingType.k1X);
		leftMotorEncoder.setDistancePerPulse(RobotMap.driveEencoderDistPerTick);
		leftMotorEncoder.setMinRate(RobotMap.driveEncoderMinRate);
		leftMotorEncoder.start();
		
		speedController=new PIDSpeed("LeftSpeedController",1,2,3, leftMotorEncoder,period);
		
		
		// enable CAN Jag Motors using constant motor IDs specified in RobotMap
		try
		{
			 leftMotor1 = new CANJaguar(RobotMap.leftmotor1);
			// rightMotor1 = new CANJaguar(RobotMap.rightmotor1);
			// leftMotor2 = new CANJaguar (RobotMap.leftmotor2);
			// rightMotor2 = new CANJaguar (RobotMap.rightmotor2);
		} catch (CANTimeoutException e)
		{
			e.printStackTrace();
			driverstation = DriverStationLCD.getInstance();
			driverstation.println(DriverStationLCD.Line.kMain6, 1, "Error initializing Jag");
            driverstation.updateLCD();
            
            
            //spawn new threads
    		// TODO Auto-generated method stub
	
		} 
		
		

		
	}

	/**
	 * Set the default command of this DriveTrain to drive with sticks
	 */
	protected void initDefaultCommand()
	{
		setDefaultCommand(new DriveWithJoystick());
		speedController.enDebug();
		speedController.Enable();
	}

	/**
	 * TankDrive Method commands the DriveTrain subsystem with TankDrive style
	 * control
	 * 
	 * @param leftSpeed
	 *            represents the %Voltage to command the left side motors
	 * @param rightSpeed
	 *            represents the %Voltage to command the right side motors
	 */
	public void TankDrive(double leftSpeed, double rightSpeed)
	{
		if (RobotMap.invertLeft){
			leftSpeed = -leftSpeed; 	//Implementing the inversion of the left.
		} 
		
		if (RobotMap.invertRight){
			rightSpeed = -rightSpeed;
		}
		
		
		// Driving in Percent V Bus mode
		try
		{
			leftMotor1.setX(leftSpeed);
			// leftMotor2.setX(leftSpeed);
			// rightMotor1.setX(rightSpeed);
			// rightMotor2.setX(rightSpeed);
		} catch (CANTimeoutException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			driverstation.println(DriverStationLCD.Line.kMain6, 1, "Error setting Jag");
            driverstation.updateLCD();
		} 
	}

}
