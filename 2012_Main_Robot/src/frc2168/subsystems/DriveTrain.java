package frc2168.subsystems;

import edu.wpi.first.wpilibj.can.CANTimeoutException;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.*;


/**
 * This is a sample class subsystem
 * @author Owner
 *
 */
public class DriveTrain extends Subsystem {
	CANJaguar CANJag;
	Joystick driverStick;
	

	protected void initDefaultCommand() {
		driverStick = new Joystick (1);
		
		try {
			CANJag = new CANJaguar (1);
			} 
		catch (CANTimeoutException ex) {
            ex.printStackTrace();
		// TODO Auto-generated method stub
      
        

		}


		 driverStick.getRawAxis(2);
		 
		try {CANJag.setX(1);
			 } 
		catch (CANTimeoutException ex) {
				 ex.printStackTrace ();
			 }
		}
	}

