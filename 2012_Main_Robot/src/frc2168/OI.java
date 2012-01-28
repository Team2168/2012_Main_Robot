
package frc2168;

import edu.wpi.first.wpilibj.Joystick;

public class OI {
	 Joystick drivestick = new Joystick(1);
	 // Process operator interface input here.
	 
	 public double getLeftSpeed() {
		 return drivestick.getRawAxis(2); //this is supposed to be the left axis stick
		 
	 }
	 
	 public double getRightSpeed() {
		 return drivestick.getRawAxis(5); //this is supposed to be the right axis stick
		 
	 }
	 
	 public double setLeftSpeed(){
		 return 
	 }
	 
	 
}

