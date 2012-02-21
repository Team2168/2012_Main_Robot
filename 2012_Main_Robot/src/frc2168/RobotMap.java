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
	public static final int driveWheelRadius=2;//Colson wheel radius in inches
	
	//average encoder
	public static final int driveAvgEncoderVal = 10;
	
	//DriveTraincEncoder Parameters
	public static final int driveEncoderPulsePerRot=250;
	public static final double driveEencoderDistPerTick=(Math.PI*2*driveWheelRadius)/driveEncoderPulsePerRot;
	public static final int driveEncoderMinRate=10; 
	public static final int driveEncoderMinPeriod=10;
	public static final boolean leftDriveTrainEncoderReverse=false;
	public static final boolean rightDriveTrainEncoderReverse=false;
	
	//drivetrain controller steady state determination
	public static final int drivetrainArraySize = 50;
	public static final double drivetrainPercent = 0.1;
	
	
	//Compressor switch and relay
	public static final int compressorSwitch = 7; //DIO 7, Digital Card
	public static final int compressorRelay = 1; //Relay Port 1, Digital Card
	public static final int ballPresentLight = 3; // Relay Port 3, Digital Card
	
	
	//period to run PID loops
	public static final long driveTrainPIDPeriod =40;//40ms loop
	
	//PID Gains for Left Side & Right Side
	//Bandwidth =
	//Phase Margin = 
	public static final double driveTrainP = 0.00574562908722711;
	public static final double driveTrainI = 0.000308064641742337; 
	public static final double driveTrainD = -0.000130778888124088;
	
	
	////////////////////////////HOOD////////////////////////////////////////
	
	public static final int shooterWheelCANID = 11, shooterWheel2CANID = 12; //Can Motor IDs
	
	//Encoder Digital IO Channels for Encoder;
	public static final int shooterWheelEncoderID_A = 5; //DIO 5, Digital Card
	public static final int shooterWheelEncoderID_B = 6; //DIO 7, Digital Card
	
	
	//Constant value for shooter wheel
	public static final double SIDE_KEY_TO_TOP = 840.0;
	public static final double LOW_GOAL_FORWARD = 400.0;
	public static final double MIDDLE_GOAL_FORWARD = 520.0;
	public static final double HIGH_GOAL_FORWARD = 720.0;
	
	
	
	
	//Solenoid Control of hood
	public static final int hoodSolenoidPortFwd = 1; //Port 1, Solenoid Card
	public static final int hoodSolenoidPortReverse = 2; //Port 2, Solenoid Card
	
	//Hood Wheel Radius in inches
	public static final int shooterWheelRadius=3;
	
	//HoodEncoder Parameters
	public static final int shooterEncoderPulsePerRot=250;
	public static final double shooterEncoderDistPerTick=(Math.PI*2*shooterWheelRadius)/shooterEncoderPulsePerRot;
	public static final int shooterEncoderMinRate=10; 
	public static final int shooterEncoderMinPeriod=10;
	public static final boolean shooterEncoderReverse=true;
	
	//average encoder
	public static final int hoodAvgEncoderVal = 20;
	
	//hood controller steady state determination
	public static final int hoodArraySize = 50;
	public static final double hoodPercent = 0.1;
	
	//period to run PID loops
	public static final long shooterPIDPeriod =40;//40ms loop
	
	//PID Gains for shooter wheel
	//Bandwidth =
	//Phase Margin = 
	public static final double shooterP = 0.000974562908722711;
	public static final double shooterI = 0.000108064641742337; 
	public static final double shooterD = -0.000130778888124088;
	

	///////////////////////////////BRIDGE ARM/////////////////////////////////////
	
	//Solenoid Control of Bridge Arm
	public static final int bridgeArmSolenoidForwardChannel = 8; //Port 8, Solenoid Card
	public static final int bridgeArmSolenoidReverseChannel = 7; //Port 7, Solenoid Card
	
	///////////////////////////////Elevator////////////////////////////////////
	
	//Victors for driving elevator motors
	public static final int lift1Victor = 1; //PWM 1, Digital Card
	public static final int lift2Victor = 2; //PWM 2, Digital Card
	
	//Solenoid to Open/Close flap
	public static final int backFlapSolenoidClose = 3; //Port 3, Solenoid Card
	public static final int backFlapSolenoidOpen = 4; //Port 4, Solenoid Card
	
	//ball detected
	public static final double ballPresentVoltage = 1.6;
	
	//Constant to drive elevator
	public static final double liftVoltage = -0.25;

	////////////////////////////////////////////////////////////////////////////
	
}
