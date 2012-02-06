
package frc2168;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc2168.subsystems.Lift;

public class OI {
	 Joystick drivestick = new Joystick(RobotMap.driverJoyStick);
	 
	 // Process operator interface input here.
	 
	 public double getLeftSpeed() 
	 {
		 if (drivestick.getRawAxis(3)<0){
			 return ((((-RobotMap.falconClawMod+1)*drivestick.getRawAxis(3))+1) * drivestick.getRawAxis(2)); 	//James' "Super Secret" Idea implemented on the left
			 
		 }
		 else {
		 
		 
		  return drivestick.getRawAxis(2); //this is supposed to be the left axis stick	 
		 }
	 }
	 
	 public double getRightSpeed() 
	 {
		 if (drivestick.getRawAxis(3)<0){
			 return ((((-RobotMap.falconClawMod+1)*drivestick.getRawAxis(3))+1) * drivestick.getRawAxis(5)); 	//James' "Super Secret" Idea implemented on the right
			 
		 }
		 else {
		 
		  return drivestick.getRawAxis(5); //this is supposed to be the right axis stick
		 }
	 }
	 
	 public static void smartDashData(){
	    	SmartDashboard.putData("SchedulerData", Scheduler.getInstance());
	    	SmartDashboard.putDouble("falconClawMod", RobotMap.falconClawMod);
	        SmartDashboard.getDouble("falconClawMod", RobotMap.falconClawMod);
	        SmartDashboard.putBoolean("invertRight", RobotMap.invertRight);
	        SmartDashboard.putBoolean("invertLeft", RobotMap.invertLeft);
	        SmartDashboard.getBoolean("invertRight", RobotMap.invertRight);
	        SmartDashboard.getBoolean("invertLeft", RobotMap.invertLeft);
	        SmartDashboard.putDouble("BallPresent",Lift.BallPresent());
	        SmartDashboard.getDouble("BallPresent",Lift.BallPresent());
	 }
	 
}

