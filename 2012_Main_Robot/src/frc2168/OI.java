
package frc2168;

import edu.wpi.first.wpilibj.Joystick;

public class OI {
	 Joystick drivestick = new Joystick(RobotMap.driverJoyStick);
	 Joystick modstick = new Joystick(RobotMap.driverJoyStick); 	//R2 used for "modding" the value given to the motors to implement James' "Super Secret" Idea 
	 double mod = 0.125;		// Low minimum/modifier for the "Falcon Claw Function" or James' "Super Secret" Idea
	 // Process operator interface input here.
	 
	 public double getLeftSpeed() 
	 {
		 if (modstick.getRawAxis(3)<0){
			 return ((((-mod+1)*modstick.getRawAxis(3))+1) * drivestick.getRawAxis(2)); 	//James' "Super Secret" Idea implemented on the left
			 
		 }
		 
		 
		  return drivestick.getRawAxis(2); //this is supposed to be the left axis stick	 
		 
	 }
	 
	 public double getRightSpeed() 
	 {
		 if (modstick.getRawAxis(3)<0){
			 return ((((-mod+1)*modstick.getRawAxis(3))+1) * drivestick.getRawAxis(5)); 	//James' "Super Secret" Idea implemented on the right
			 
		 }
		 
		 
		  return drivestick.getRawAxis(5); //this is supposed to be the right axis stick
		 
	 }
	 
}

