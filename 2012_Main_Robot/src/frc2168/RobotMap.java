package frc2168;


/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {

	/*
	 *Drivetrain motors
	  Imagine this to be the chasis, so the R1, L1 etc is supposed to represent the motors that are on each side._
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

	
	//Joystick Maps
	
	public static final int driverJoyStick=1;
	public static final int auxJoystick=2;
	
	//Creating Static CAN IDs for DriveTrain Motors
	public static final int leftmotor1 = 11, rightmotor1 = 12;

	//public static final int leftmotor2 = 2, rightmotor2 = 10;
	

	
	
	//create other sensor maps below
	//Digital Map
	
	/*
	leftDriveTrainEncoder_A=1;
	leftDriveTrainEncoder_B=2;
	rightDriveTrainEncoder_A=1;
	rightDriveTrainEncoder_B=2;
	 
	 */
}