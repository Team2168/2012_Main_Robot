package frc2168.subsystems;

import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.DriverStationLCD;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.can.CANTimeoutException;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc2168.RobotMap;
import frc2168.testthread;
import frc2168.commands.DriveWithJoystick;

public class DriveTrain extends Subsystem
{

	// Declare all CAN Motors associated with the Drive Train
	// We use two motors for each side of our drive train

	
	
	DriverStationLCD driverstation;
	
	CANJaguar leftMotor1;
//	CANJaguar rightMotor1;
//	CANJaguar leftMotor2;
//	CANJaguar rightMotor2;
	
	Encoder motorEncoder;

	/**
	 * Default Constructor for DriveTrain Subsystem. This Constructor enables
	 * CAN Jaguar motors for the Drivetrain
	 */
	public DriveTrain()
	{
		motorEncoder = new Encoder(13,14);
		motorEncoder.setDistancePerPulse((Math.PI*2*4)/500);
		motorEncoder.setMinRate(10);
		motorEncoder.setMaxPeriod(10);
		
		
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
			driverstation.println(DriverStationLCD.Line.kMain6, 1, "Error initializing Jag");
            driverstation.updateLCD();
            
            
            //spawn new threads
    		// TODO Auto-generated method stub

    		testthread timer = new testthread(1,motorEncoder);
    		 //timer = new testthread(2);
			
		} 
	}

	/**
	 * Set the default command of this DriveTrain to drive with sticks
	 */
	protected void initDefaultCommand()
	{
		setDefaultCommand(new DriveWithJoystick());
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
