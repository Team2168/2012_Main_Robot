package frc2168.subsystems;

import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.CounterBase;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DriverStationLCD;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.can.CANTimeoutException;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc2168.PIDController.PIDSpeed;
import frc2168.RobotMap;
import frc2168.commands.DriveWithJoystick;



/**
 * 
 * @author Kevin Harrilal, First Robotics Team 2168
 *<br><br>
 *The DriveTrain class is a Subsystem apart of a CommandBase Robot.
 *The purpose of this class is to be a single static object which interface with the hardware used to drive
 *a robot and give methods which can be used to command the DriveTrain in different ways.
 *<br><br>
 *This DriveTrain object is driven by 4 Jaguar motor controller connected to 4 CIM motors. There are
 *two CIM motors on each side of this skid-steer Chassis. This DriveTrain was intended to be driven
 *using Tank Drive style control, where each side (left and right) of the wheel base are commanded
 *Independently.
 *<br><br>
 *The DriveTrain also implements different sensors for use such as an encoder on each side, and an ultrasonic
 *sensor to detect objects in-front and behind.
 *<br><br>
 *The methods this object implements are essentially the core abilities this DriveTrain has. These methods
 *are designed to be called by Command Objects to create more complex instructions to control the driveTrain.
 *The default mechanism to control this driveTrain is via Joystick Control.
 *<br><br>
 *This DriveTrain also instantiates PID Control Loops which run in separate threads, and can be used
 *to command the DriveTrain to drive at a set Speed, or to Drive to a linear position and stop.
 *
 */
public class DriveTrain extends Subsystem
{
	//We instantiate all parameters to interface with the hardware of the DriveTrain
	
	//////////////////////////////////////////////////////////////////////
	// Declare all CAN Motors associated with the Drive Train
	// We use two motors for each side of our drive train
		Jaguar leftMotor1;
		Jaguar rightMotor1;
		Jaguar leftMotor2;
		Jaguar rightMotor2;
	
	//////////////////////////////////////////////////////////////////////
	//Declare Sensors
		Encoder leftMotorEncoder;	
		Encoder	rightMotorEncoder;
		
	
	/////////////////////////////////////////////////////////////////////	
	//PID Controllers
		public PIDSpeed leftSpeedController;
		public PIDSpeed rightSpeedController;		
		

	////////////////////////////////////////////////////////////////////
	//Shifting solenoids
		DoubleSolenoid gearShifter;
		
	////////////////////////////////////////////////////////////////////

	/**
	 * Default Constructor for DriveTrain Subsystem. This Constructor instantiates
	 * the CAN Jaguar motors for the DriveTrain and all the sensors based on the Parameters specified
	 * in {@link RobotMap}.
	 * <br><br>
	 * If the CAN Jaguars can not be initialized a CANTimeOut Exception is thrown and an error will be printed
	 * to the User Message window of the Driver Station.
	 * <br><br>
	 * This Constructor also instantiates multiple PID Control Threads which are used by the core
	 * PID methods for autonomous driving. The Gains of The PID controller are specified in {@link RobotMap}
	 * 
	 */
	public DriveTrain()
	{
		//enable shifting solenoids
		gearShifter = new DoubleSolenoid(RobotMap.shiftForwardChannel, RobotMap.shiftReverseChannel);
		
		//enable left and right encoders
		leftMotorEncoder = new Encoder(RobotMap.leftDriveTrainEncoder_A,
		RobotMap.leftDriveTrainEncoder_B,false,CounterBase.EncodingType.k1X);
	
		rightMotorEncoder = new Encoder(RobotMap.rightDriveTrainEncoder_A,
				RobotMap.rightDriveTrainEncoder_B,false,CounterBase.EncodingType.k1X);
		
		//Set Encoder Paramters
		leftMotorEncoder.setDistancePerPulse(RobotMap.driveEencoderDistPerTick);
		leftMotorEncoder.setMinRate(RobotMap.driveEncoderMinRate);
		leftMotorEncoder.setReverseDirection(RobotMap.leftDriveTrainEncoderReverse);
		rightMotorEncoder.setDistancePerPulse(RobotMap.driveEencoderDistPerTick);
		rightMotorEncoder.setMinRate(RobotMap.driveEncoderMinRate);
		rightMotorEncoder.setReverseDirection(RobotMap.rightDriveTrainEncoderReverse);
		
		//leftMotorEncoder.setReverseDirection(true);
		leftMotorEncoder.start();
		rightMotorEncoder.start();
		
		//Spawn New PID Speed Controller with PID Gains as specified in ROBOT MAP
		leftSpeedController=new PIDSpeed("LeftSpeedController",RobotMap.driveTrainP,RobotMap.driveTrainI,RobotMap.driveTrainD, leftMotorEncoder,RobotMap.driveTrainPIDPeriod);
		rightSpeedController=new PIDSpeed("RightSpeedController",RobotMap.driveTrainP,RobotMap.driveTrainI,RobotMap.driveTrainD, rightMotorEncoder,RobotMap.driveTrainPIDPeriod);
		
		//set steady state determination for left controller
		leftSpeedController.setSIZE(RobotMap.drivetrainArraySize);
		leftSpeedController.setPercent(RobotMap.drivetrainPercent);

		//set steady state determination for left controller
		rightSpeedController.setSIZE(RobotMap.drivetrainArraySize);
		rightSpeedController.setPercent(RobotMap.drivetrainPercent);
		
		// enable PWM Jag Motors using constant motor IDs specified in RobotMap
	
			 leftMotor1 = new Jaguar(RobotMap.driveLeft1JagPWM);
			 rightMotor1 = new Jaguar(RobotMap.driveRight1JagPWM);
			 leftMotor2 = new Jaguar (RobotMap.driveLeft2JagPWM);
			 rightMotor2 = new Jaguar (RobotMap.driveRight2JagPWM);
			 

	
	}

	/**
	 * This method is called when there is no other command which requires the DriveTrain
	 * subsystem. This Method is used  to set the default command of this DriveTrain. Essentially command
	 * of the driveTrain will be given to the command called from this method when no other command requires 
	 * the DriveTrain subsystem.
	 * <br><br> 
	 * The Default command is set to drive with JoySticks using the {@link DriveWithSticks} command.
	 */
	protected void initDefaultCommand()
	{

		setDefaultCommand(new DriveWithJoystick());
		
	}

	/**
	 * TankDrive Method commands the DriveTrain subsystem with TankDrive style
	 * control. This method takes care of inverting the motors so that same direction
	 * control can be applied to both motors (i.e +1 to both motors for forward and -1 to both motors for reverse). 
	 * <br><br>
	 * This method determines which motors to invert by reading the Invert parameters set in {@link RobotMap}
	 * <br><br>
	 * If the CAN Jaguars can not be commanded a CANTimeOut Exception is thrown and an error will be printed
	 * to the User Message window of the Driver Station.
	 * 
	 * @param leftSpeed
	 *            represents the %Voltage to command the left side motors (Range = -1 to +1)
	 * @param rightSpeed
	 *            represents the %Voltage to command the right side motors (Range = -1 to +1)
	 */
	public void TankDrive(double leftSpeed, double rightSpeed)
	{
		//Determine which Motors need to be inverted
		if (RobotMap.invertLeft)
		{
			leftSpeed = -leftSpeed; 	//Implementing the inversion of the left.
		} 
		if (RobotMap.invertRight)
		{
			rightSpeed = -rightSpeed;
		}
		
		// Driving PWM motors in Percent V Bus mode

			leftMotor1.set(leftSpeed);

			leftMotor2.set(leftSpeed);

			rightMotor1.set(rightSpeed);

			rightMotor2.set(rightSpeed);

	}
	
	/**
	 * This method is intended to be called by a Command which reads from a PID Controller. 
	 * @param output represents the %Voltage to command the left side motors (Range = -1 to +1). Both Left and Right side motors of the DriveTrain are commanded to the value represented by this Parameter. 
	 */
    public void PIDSpeedOutput(double output) 
    {
    	TankDrive(output, output);
    }

    public void shiftGearsHighToLow(){
    	gearShifter.set(DoubleSolenoid.Value.kForward);
    }
    
    public boolean gearIsHigh()
    {
    	return gearShifter.get()==DoubleSolenoid.Value.kReverse;
    }
    
    public void shiftGearsLowToHigh(){
    	gearShifter.set(DoubleSolenoid.Value.kReverse);
    }
    
    public boolean gearIsLow()
    {
    	return gearShifter.get()==DoubleSolenoid.Value.kForward;
    }
}
