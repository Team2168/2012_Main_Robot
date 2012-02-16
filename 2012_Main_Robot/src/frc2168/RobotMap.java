package frc2168;

import edu.wpi.first.wpilibj.DriverStationLCD;


/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {

	//////////////////////////////////////////////////////////////////
	//DriverStation LCD
	public static DriverStationLCD driverstation;
	
	///////////////////////////////////////////////////////////////////
	//JOYSTICK MAPS
	
	public static final int driverJoystick=1; //USB Port 1
	public static final int auxJoystick=2; //USB Port 2
	

	///////////////////////////////DRIVETRAIN////////////////////////////////////
	//DRIVE TRAIN SUBSYSTEM CONSTANTS
	
	/*
	  The drivetrain uses four CAN motors. The image below represents a top view of the Chassis
	  so that the location of each motor can be identified. This image is to be used as a key
	  to determine the values of the CAN motor ids.
	  ____________ 
	  |			  |
	  |    FWD    |
	  |           |
	L1|           |R1
	  |           |
	L2|           |R2
	  |           |
	  |    AFT    |
	  |___________| 
	 */

	//gear shifting solenoid ports
	public static final int shiftForwardChannel = 5; //Port 5, Solenoid Card
	public static final int shiftReverseChannel = 6; //Port 6 , Solenoid Card
	
	//Creating Static CAN IDs for DriveTrain Motors
	public static final int leftmotor1 = 13, rightmotor1 = 2;
	public static final int leftmotor2 = 17, rightmotor2 = 10;
	
	//Motor Invert Direction 
	public static final boolean invertRight = false;	//if true invert signal to right motors
	public static final boolean invertLeft = true;		//if true invert signal to left motors
	
	//Falcon Claw Brake Modifier
	public static final double mod = 0.125;	// Low minimum/modifier for the "Falcon Claw Function" or James' "Super Secret" Idea
	
	//Encoder Digital IO Channels for Drivetrain;
	public static final int leftDriveTrainEncoder_A=1; //DIO 1, Digital Card
	public static final int leftDriveTrainEncoder_B=2; //DIO 2, Digital Card
	public static final int rightDriveTrainEncoder_A=3; //DIO 3, Digital Card
	public static final int rightDriveTrainEncoder_B=4; //DIO 4, Digital Card
	
	//Wheel Radius
	public static final int driveWheelRadius=4; //Colson wheel radius
	
	//DriveTraincEncoder Parameters
	public static final int driveEncoderPulsePerRot=250;
	public static final double driveEencoderDistPerTick=(Math.PI*2*driveWheelRadius)/driveEncoderPulsePerRot;
	public static final int driveEncoderMinRate=10; 
	public static final int driveEncoderMinPeriod=10;
	
	
	//Compressor switch and relay
	public static final int compressorSwitch = 7; //DIO 7, Digital Card
	public static final int compressorRelay = 1; //Relay Port 1, Digital Card
	
	
	//PID Gains for Left Side
	
	public static final double P = 0.00574562908722711;
	public static final double I = 0.000308064641742337; 
	public static final double D = -0.000130778888124088;
	
	
	////////////////////////////Hood////////////////////////////////////////
	
	public static final int shooterWheelCANID = 11, shooterWheel2CANID = 12; //Can Motor IDs
	
	//Encoder Digital IO Channels for Encoder;
	public static final int shooterWheelEncoderID_A = 5; //DIO 5, Digital Card
	public static final int shooterWheelEncoderID_B = 6; //DIO 7, Digital Card
	
	
	//Solenoid Control of hood
	public static final int hoodSolenoidPortFwd = 1; //Port 1, Solenoid Card
	public static final int hoodSolenoidPortReverse = 2; //Port 2, Solenoid Card
	
	//Hood Wheel Radius
	public static final int shooterWheelRadius=4;
	
	//HoodEncoder Parameters
	public static final int shooterEncoderPulsePerRot=250;
	public static final double shooterEncoderDistPerTick=(Math.PI*2*shooterWheelRadius)/shooterEncoderPulsePerRot;
	public static final int shooterEncoderMinRate=10; 
	public static final int shooterEncoderMinPeriod=10;
	public static final boolean shooterEncoderReverse=true;
	

	///////////////////////////////BRIDGE ARM/////////////////////////////////////
	
	//Solenoid Control of Bridge Arm
	public static final int bridgeArmSolenoidForwardChannel = 7; //Port 7, Solenoid Card
	public static final int bridgeArmSolenoidReverseChannel = 8; //Port 8, Solenoid Card
	
	///////////////////////////////Elevator////////////////////////////////////
	
	//Victors for driving elevator motors
	public static final int lift1Victor = 1; //PWM 1, Digital Card
	public static final int lift2Victor = 2; //PWM 2, Digital Card
	
	//Solenoid to Open/Close flap
	public static final int backFlapSolenoidClose = 3; //Port 3, Solenoid Card
	public static final int backFlapSolenoidOpen = 4; //Port 4, Solenoid Card
	////////////////////////////////////////////////////////////////////////////
}
