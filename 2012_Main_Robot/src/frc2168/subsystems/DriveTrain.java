package frc2168.subsystems;

import edu.wpi.first.wpilibj.can.CANTimeoutException;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.RobotDrive;
import frc2168.RobotMap;
import frc2168.commands.DriveWithJoystick;

public class DriveTrain extends Subsystem {
	
	protected void initDefaultCommand()
	{
		
		try 
		{
			CANJaguar R1 = new CANJaguar (RobotMap.R1); //CAN ID = 
			
			/*CANJaguar R2 = new CANJaguar (RobotMap.R2); //CAN ID = 

			CANJaguar R3 = new CANJaguar (RobotMap.R3); //CAN ID = 

			CANJaguar R4 = new CANJaguar (RobotMap.R4); //CAN ID = 

			CANJaguar L1 = new CANJaguar (RobotMap.R5); //CAN ID = 

			CANJaguar L2 = new CANJaguar (RobotMap.R6); //CAN ID = 

			CANJaguar L3 = new CANJaguar (RobotMap.R7); //CAN ID = 

			CANJaguar L4 = new CANJaguar (RobotMap.R8); //CAN ID = 
			*/

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
		RobotDrive drive = new RobotDrive(RobotMap.R1, RobotMap.L1);
		RobotDrive drive2 = new RobotDrive(RobotMap.R2, RobotMap.L2);
		RobotDrive drive3 = new RobotDrive(RobotMap.R3, RobotMap.L3);
		RobotDrive drive4 = new RobotDrive(RobotMap.R4, RobotMap.L3);
		/*This might not be right but the reason I did this is 
		 * because there's no constructor in RobotDrive, 
		 * that lets me put in all 8 motors.
		*/
	}

}
