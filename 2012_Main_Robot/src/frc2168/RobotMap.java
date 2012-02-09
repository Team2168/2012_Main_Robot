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
	public static final int shiftForwardChannel = 7;
	public static final int shiftReverseChannel = 8;
	
	//Creating Static CAN IDs for DriveTrain Motors
	public static final int leftmotor1 = 4, rightmotor1 = 8;
	public static final int leftmotor2 = 3, rightmotor2 = 10;
	
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
	public static final int driveEncoderPulsePerRot=250;
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
	
	public static final int shooterWheelCANID = 2, shooterWheel2CANID = 5;
	public static final int shooterWheelEncoderID_A = 7, shooterWheelEncoderID_B = 8;
	public static final int hoodSolenoidPortFwd = 1, hoodSolenoidPortReverse = 2;
	
	//Hood Wheel Radius
	public static final int shooterWheelRadius=4;
	
	//HoodEncoder Parameters
	public static final int shooterEncoderPulsePerRot=250;
	public static final double shooterEncoderDistPerTick=(Math.PI*2*shooterWheelRadius)/shooterEncoderPulsePerRot;
	public static final int shooterEncoderMinRate=10; 
	public static final int shooterEncoderMinPeriod=10;
	
	////////////////////////////////////////////////////////////////////////////
	
	///////////////////////////////BRIDGE ARM/////////////////////////////////////
	
	public static final int bridgeArmSolenoidForwardChannel = 5;
	public static final int bridgeArmSolenoidReverseChannel = 6;
	
	
	////////////////////////////////////////////////////////////////////
}