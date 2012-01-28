package frc2168.subsystems;

import edu.wpi.first.wpilibj.can.CANTimeoutException;


import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.CANJaguar.ControlMode;
import edu.wpi.first.wpilibj.RobotDrive;

import frc2168.RobotMap;
import frc2168.commands.DriveWithJoystick;

public class DriveTrain extends Subsystem {
	CANJaguar leftMotor;
	CANJaguar rightMotor;
	//RobotDrive drive;
	
	protected void initDefaultCommand()
	{  
		//drive = new RobotDrive(RobotMap.leftmotor, RobotMap.rightmotor);
		
		
		
		
		try 
		{
			 leftMotor = new CANJaguar (RobotMap.leftmotor); //CAN ID = 
			
			 rightMotor = new CANJaguar (RobotMap.rightmotor); //CAN ID = 

//			CANJaguar R1 = new CANJaguar (RobotMap.R3); //CAN ID = 
//
//			CANJaguar R2 = new CANJaguar (RobotMap.R4); //CAN ID = 
//
//			CANJaguar L1 = new CANJaguar (RobotMap.R5); //CAN ID = 
//
//			CANJaguar L2 = new CANJaguar (RobotMap.R6); //CAN ID = 
//

			

		}
		catch (CANTimeoutException e) {
			e.printStackTrace();
		}
	}
	
	public void initDefaultCommand1() {
		setDefaultCommand(new DriveWithJoystick());
	}
		// TODO Auto-generated method stub

	public DriveTrain() {
		
		/*This might not be right but the reason I did this is 
		 * because there's no constructor in RobotDrive, 
		 * that lets me put in all 8 motors.
		*/
	}
	
	public void TankDrive(double leftSpeed, double rightSpeed) {
		//Driving in Percent V Bus mode
		try
		{
			leftMotor.setX(leftSpeed);
			rightMotor.setX(rightSpeed);
		} catch (CANTimeoutException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
