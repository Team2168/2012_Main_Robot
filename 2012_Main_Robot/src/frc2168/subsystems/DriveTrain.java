package frc2168.subsystems;

import edu.wpi.first.wpilibj.can.CANTimeoutException;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.CANJaguar;
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
		
		setDefaultCommand(new DriveWithJoystick());

	}
		// TODO Auto-generated method stub

	

}
