package frc2168;


/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {

	///////////////////////////////////////////////////////////////////
	//JOYSTICK MAPS
	
	public static final int driverJoystick=1; //USB Port 1
	public static final int auxJoystick=2; //USB Port 2
	

	///////////////////////////////////////////////////////////////////
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

	
	//Creating Static CAN IDs for DriveTrain Motors
	public static final int leftmotor1 = 11, rightmotor1 = 12;
	public static final int leftmotor2 = 2, rightmotor2 = 10;
	
	//Motor Invert Direction 
	public static final boolean invertRight = false;	//if true invert signal to right motors
	public static final boolean invertLeft = true;		//if true invert signal to left motors
	
	//Falcon Claw Brake Modifier
	public static final double mod = 0.125;	// Low minimum/modifier for the "Falcon Claw Function" or James' "Super Secret" Idea
	
	//Encoder Digital IO Channels;
	public static final int leftDriveTrainEncoder_A=13;
	public static final int leftDriveTrainEncoder_B=14;
	public static final int rightDriveTrainEncoder_A=1;
	public static final int rightDriveTrainEncoder_B=2;
	
	//Wheel Radius
	public static final int driveWheelRadius=4; //Colson wheel radius
	
	//DriveTraincEncoder Parameters
	public static final int driveEncoderPulsePerRot=500;
	public static final double driveEencoderDistPerTick=(Math.PI*2*driveWheelRadius)/driveEncoderPulsePerRot;
	public static final int driveEncoderMinRate=10; 
	public static final int driveEncoderMinPeriod=10;
	
	
	//Compressor switch and relay
	public static final int compressorSwitch = 1; //switch channel for the compressor
	public static final int compressorRelay = 1; //relay channel for the compressor
	
	
	//PID Gains for Left Side
	
	public static final double P = 0.00574562908722711;
	public static final double I = 0.000308064641742337; 
	public static final double D = -0.000130778888124088;
	
	
	////////////////////////////ELEVATOR////////////////////////////////////////
	
	public static final int shooterWheelCANID = 6, shooterWheel2CANID = 9;
	public static final int shooterWheelEncoderID_A = 0, shooterWheelEncoderID_B = 1;
	public static final int hoodSolenoidPort = 69;
	
	//Hood Wheel Radius
	public static final int shooterWheelRadius=4;
	
	//HoodEncoder Parameters
	public static final int shooterEncoderPulsePerRot=500;
	public static final double shooterEncoderDistPerTick=(Math.PI*2*shooterWheelRadius)/shooterEncoderPulsePerRot;
	public static final int shooterEncoderMinRate=10; 
	public static final int shooterEncoderMinPeriod=10;
	
	////////////////////////////////////////////////////////////////////
}