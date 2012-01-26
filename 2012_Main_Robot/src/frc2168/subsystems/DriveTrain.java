package frc2168.subsystems;

import edu.wpi.first.wpilibj.can.CANTimeoutException;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.*;


/**
 * This is a sample class subsystem
 * @author Owner
 *
 */
public class DriveTrain extends Subsystem 
{
	CANJaguar CANJag;
	Joystick driverStick = new Joystick(1);
	


	protected void initDefaultCommand() 
	{ 
		setDefaultCommand(new driveWithStick());
	
		
		
		// TODO Auto-generated method stub
		
	}
	
	public void driveWithStick()
	{
	
		try 
		{
			CANJag = new CANJaguar (1);
		} 
		catch (CANTimeoutException ex) 
		{
	        ex.printStackTrace();
		}
        


		 driverStick.getRawAxis(2);
		 
		try 
		{
			CANJag.setX(1);
		} 
		catch (CANTimeoutException ex) 
		{
				 ex.printStackTrace ();
		}
	
	}
}

