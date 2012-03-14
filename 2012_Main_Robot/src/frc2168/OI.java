package frc2168;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.DigitalIOButton;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc2168.advancedIO.IOAnalogButton;
import frc2168.advancedIO.IOModule;
import frc2168.advancedIO.JoystickAnalogButton;
import frc2168.commands.BackFlapClose;
import frc2168.commands.BackFlapOpen;
import frc2168.commands.ButtonBoxPoints;
import frc2168.commands.DriveElevatorConst;
import frc2168.commands.HighGoalAuto;
import frc2168.commands.LowGoalAuto;
import frc2168.commands.LowerBridge;
import frc2168.commands.LowerHood;
import frc2168.commands.MiddleGoalAuto;
import frc2168.commands.PID_DriveShooter;
import frc2168.commands.PID_ShooterPause;
import frc2168.commands.RaiseBridge;
import frc2168.commands.RaiseHood;
import frc2168.commands.ShiftGearsHighToLow;
import frc2168.commands.ShiftGearsLowToHigh;
import frc2168.commands.shootSingleBall;
import frc2168.commands.sleep;


/**
 * 
 * @author Kevin Harrilal, First Robotics Team 2168 <br>
 * <br>
 *         The Operator Input (OI) Class is a central object that dictates how
 *         the operator interfaces with the robot.
 * 
 *         The purpose of this object is to setup which commands are called by
 *         which buttons, and return the values of the joystick axis.
 */

public class OI {
	
	// Driver Joystick
	public Joystick drivestick = new Joystick(RobotMap.driverJoystick);
	public Button driveButtonA = new JoystickButton(drivestick, 1),
			driveButtonB = new JoystickButton(drivestick, 2),
			driveButtonX = new JoystickButton(drivestick, 3),
			driveButtonY = new JoystickButton(drivestick, 4),
			driveButtonLeftBumper = new JoystickButton(drivestick, 5),
			driveButtonRightBumper = new JoystickButton(drivestick, 6),
			driveButtonReset = new JoystickButton(drivestick, 7),
			driveButtonStart = new JoystickButton(drivestick, 8);
	public JoystickAnalogButton driveTriggerR = new JoystickAnalogButton(drivestick, 3, -0.5),
			driveTriggerL = new JoystickAnalogButton(drivestick, 3, 0.5),
			driveDPadL = new JoystickAnalogButton(drivestick, 6, -0.5),
			driveDPadR = new JoystickAnalogButton(drivestick, 6, 0.5);
	
	// Auxiliary Joystick
	public Joystick auxstick = new Joystick(RobotMap.auxJoystick);
	public Button auxButtonA = new JoystickButton(auxstick, 1),
			auxButtonB = new JoystickButton(auxstick, 2),
			auxButtonX = new JoystickButton(auxstick, 3),
			auxButtonY = new JoystickButton(auxstick, 4),
			auxButtonLeftBumper = new JoystickButton(auxstick, 5),
			auxButtonRightBumper = new JoystickButton(auxstick, 6),
			auxButtonReset = new JoystickButton(auxstick, 7),
			auxButtonStart = new JoystickButton(auxstick, 8);
	
			
	public JoystickAnalogButton auxTriggerR = new JoystickAnalogButton(auxstick, 3, -0.5),
			auxTriggerL = new JoystickAnalogButton(auxstick, 3, 0.5),
			auxDPadL = new JoystickAnalogButton(auxstick, 6, -0.5),
			auxDPadR = new JoystickAnalogButton(auxstick, 6, 0.5);
	
	// IO MODULE BUTTONS
	public IOModule ioBoard = new IOModule();	//configure the IO module
	public IOAnalogButton ioAnalog1 = new IOAnalogButton(1),		
			ioAnalog2 = new IOAnalogButton(2),
			ioAnalog3 = new IOAnalogButton(3),
			ioAnalog4 = new IOAnalogButton(4),
			ioAnalog5 = new IOAnalogButton(5),
			ioAnalog6 = new IOAnalogButton(6),
			ioAnalog7 = new IOAnalogButton(7),
			ioAnalog8 = new IOAnalogButton(8);
	
	public DigitalIOButton ioDigital1 = new DigitalIOButton(1),	//"pressed" when shorted to ground
			ioDigital2 = new DigitalIOButton(2),
			ioDigital3 = new DigitalIOButton(3),
			ioDigital4 = new DigitalIOButton(4),
			ioDigital5 = new DigitalIOButton(5),
			ioDigital6 = new DigitalIOButton(6),
			ioDigital7 = new DigitalIOButton(7),
			ioDigital8 = new DigitalIOButton(8),
			ioDigital9 = new DigitalIOButton(9),
			ioDigital10 = new DigitalIOButton(10),
			ioDigital11 = new DigitalIOButton(11),
			ioDigital12 = new DigitalIOButton(12),
			ioDigital13 = new DigitalIOButton(13),
			ioDigital14 = new DigitalIOButton(14),
			ioDigital15 = new DigitalIOButton(15),
			ioDigital16 = new DigitalIOButton(16);
	
	
	public OI() {
		//drive left axis = left drivetrain in DriveWithJoystick
		//drive right axis = right drivetrain in DriveWithJoystick
		driveButtonA.whenPressed(new LowerBridge());
		driveButtonY.whenPressed(new RaiseBridge());
		driveButtonRightBumper.whenPressed(new ShiftGearsLowToHigh());
		driveButtonLeftBumper.whenPressed(new ShiftGearsHighToLow());
		
		//aux left axis = left DriveElevatorJoystick
		//aux right axis = right ShooterWheelJoystick
		auxButtonA.whenPressed(new LowGoalAuto());
		auxButtonB.whenPressed(new MiddleGoalAuto());
		auxButtonY.whenPressed(new HighGoalAuto());
		auxButtonX.whenPressed(new PID_ShooterPause());
		
		auxButtonStart.whenPressed(new shootSingleBall());
		auxButtonReset.whenPressed(new LowerHood());
		
		auxButtonRightBumper.whenPressed(new BackFlapClose());
		auxButtonLeftBumper.whenPressed(new BackFlapOpen());
		
		ioDigital2.whenPressed(new DriveElevatorConst(RobotMap.liftVoltage)); //raise lift
		ioDigital3.whenPressed(new DriveElevatorConst(-RobotMap.liftVoltage)); //lower lift
		ioDigital4.whenPressed(new BackFlapOpen()); //Hopper Down
		ioDigital5.whenPressed(new BackFlapClose()); //Hopper Up
		ioDigital6.whenPressed(new ButtonBoxPoints(0)); //front goal shot. high/low determined by switch10
		ioDigital7.whenPressed(new ButtonBoxPoints(1)); //side goal shot
		ioDigital8.whenPressed(new ButtonBoxPoints(2)); //key shot
		ioDigital9.whenPressed(new ButtonBoxPoints(3)); //long shot
		//ioDigital10.whenPressed(); //Shooting mode switch
		ioDigital11.whenPressed(new PID_DriveShooter(0)); //set shooter to zero/turn off shooter
		ioDigital12.whenPressed(new shootSingleBall()); //fire
		ioDigital13.whenPressed(new RaiseHood()); //Switch13 position to raise hood
		ioDigital13.whenReleased(new LowerHood()); //Switch13 position to lower hood
//		ioDigital14.whenPressed();  //counter "2"
//		ioDigital15.whenPressed();  //counter "4"
//		ioDigital16.whenPressed();  //counter "8"
//		ioDigital1.whenPressed();   //counter "1"

		
	}

	/**
	 * Returns the axis value of the Left DriverStick adjusted 
	 * 
	 * @return the value of the left stick
	 */
	public double getDriveLeftAxis() {
		if (drivestick.getRawAxis(3) < 0) {		//FALCON CLAW - use electronic braking
			return ((((-RobotMap.mod + 1) * drivestick.getRawAxis(3)) + 1) * drivestick.getRawAxis(2));
		} else {
			return drivestick.getRawAxis(2);
		}
	}

	/**
	 * Returns the axis value of the Right DriverStick
	 * 
	 * @return the value of the right axis
	 */
	public double getDriveRightAxis() {
		if (drivestick.getRawAxis(3) < 0) {		//FALCON CLAW - use electronic braking
			return ((((-RobotMap.mod + 1) * drivestick.getRawAxis(3)) + 1) * drivestick.getRawAxis(5));
		} else {
			return drivestick.getRawAxis(5);
		}
	}

	/**
	 * Returns the axis value of the Left AuxiliaryStick
	 * 
	 * @return the value of the left stick
	 */
	public double getAuxLeftStick()	{
		return auxstick.getRawAxis(2); // this is supposed to be the left
	}

	/**
	 * Returns the axis value of the Right Auxiliary Stick
	 * 
	 * @return the value of the right stick
	 */
	public double getAuxRightStick() {
		return auxstick.getRawAxis(5); // this is supposed to be the right
	}
}
