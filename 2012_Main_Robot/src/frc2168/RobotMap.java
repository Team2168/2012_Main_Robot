package frc2168;

import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.can.CANTimeoutException;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	// For example to map the left and right motors, you could define the
	// following variables to use with your drivetrain subsystem.
	// public static final int leftMotor = 1;
	// public static final int rightMotor = 2;

	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static final int rangefinderPort = 1;
	// public static final int rangefinderModule = 1;
	/*
	  Imagine this to be the chasis, so the R1, L1 etc is supposed to represent the motors that are on each side._
	  ____________ 
	  |			  |
	L1|           |R1
	  |           |
	L2|           |R2
	  |           |
	L3|           |R3
	  |           |
	L4|           |R4
	  |___________|
	 */

	//Creating Static CAN IDs for DriveTrain Motors
	public static final int  R1 = 11, 
		    R2=1, //CAN ID = 1
			R3=2, //CAN ID = 2
			R4=3, //CAN ID = 3
			L1=4, //CAN ID = 4
			L2=5, //CAN ID = 5
			L3=6, //CAN ID = 6
			L4=7; //CAN ID = 7
}