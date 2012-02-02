
package frc2168;

import edu.wpi.first.wpilibj.Joystick;

public class OI {
	 Joystick drivestick = new Joystick(RobotMap.driverJoyStick);
	 
	 // Process operator interface input here.
	 
	 public double getLeftSpeed() 
	 {
		 if (drivestick.getRawAxis(3)<0){
			 return ((((-RobotMap.mod+1)*drivestick.getRawAxis(3))+1) * drivestick.getRawAxis(2)); 	//James' "Super Secret" Idea implemented on the left
			 
		 }
		 else {
		 
		 
		  return drivestick.getRawAxis(2); //this is supposed to be the left axis stick	 
		 }
	 }
	 
	 public double getRightSpeed() 
	 {
		 if (drivestick.getRawAxis(3)<0){
			 return ((((-RobotMap.mod+1)*drivestick.getRawAxis(3))+1) * drivestick.getRawAxis(5)); 	//James' "Super Secret" Idea implemented on the right
			 
		 }
		 else {
		 
		  return drivestick.getRawAxis(5); //this is supposed to be the right axis stick
		 }
	 }
	 
}

