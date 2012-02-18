package frc2168;

import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc2168.commands.BackFlapClose;
import frc2168.commands.BackFlapOpen;
import frc2168.commands.DriveLiftUntilBall;
import frc2168.commands.DriveLiftUntilNoBall;
import frc2168.commands.HighGoalAuto;
import frc2168.commands.LowGoalAuto;
import frc2168.commands.MiddleGoalAuto;
import frc2168.commands.PIDShootBall;
import frc2168.commands.ShooterWheelJoystick;
import frc2168.commands.DriveToSpeed;
import frc2168.commands.LowerBridge;
import frc2168.commands.LowerHood;
import frc2168.commands.RaiseBridge;
import frc2168.commands.RaiseHood;
import frc2168.commands.ShiftGearsHighToLow;
import frc2168.commands.ShiftGearsLowToHigh;

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
public class OI
{
	public Joystick drivestick = new Joystick(RobotMap.driverJoystick);
	public Button driveButtonA = new JoystickButton(drivestick, 1),
			driveButtonB = new JoystickButton(drivestick, 2),
			driveButtonX = new JoystickButton(drivestick, 3),
			driveButtonY = new JoystickButton(drivestick, 4),
			driveButtonLeftBumper = new JoystickButton(drivestick, 5),
			driveButtonRightBumper = new JoystickButton(drivestick, 6),
			driveButtonReset = new JoystickButton(drivestick, 7),
			driveButtonStart = new JoystickButton(drivestick, 8);
	
	public Joystick auxstick = new Joystick(RobotMap.auxJoystick);
	public Button auxButtonA = new JoystickButton(auxstick, 1),
			auxButtonB = new JoystickButton(auxstick, 2),
			auxButtonX = new JoystickButton(auxstick, 3),
			auxButtonY = new JoystickButton(auxstick, 4),
			auxButtonLeftBumper = new JoystickButton(auxstick, 5),
			auxButtonRightBumper = new JoystickButton(auxstick, 6),
			auxButtonReset = new JoystickButton(auxstick, 7),
			auxButtonStart = new JoystickButton(auxstick, 8);

	public OI()
	{
		//drive left axis = left drivetrain in DriveWithJoystick
		//drive right axis = right drivetrain in DriveWithJoystick
		
		driveButtonX.whenPressed(new LowerBridge());
		driveButtonY.whenPressed(new RaiseBridge());
		driveButtonLeftBumper.whenPressed(new ShiftGearsLowToHigh());
		driveButtonRightBumper.whenPressed(new ShiftGearsHighToLow());
		
		//aux left axis = left DriveElevatorJoystick
		//aux right axis = right ShooterWheelJoystick
		auxButtonA.whenPressed(new LowGoalAuto());
		auxButtonB.whenPressed(new MiddleGoalAuto());
		auxButtonY.whenPressed(new HighGoalAuto());
		
		auxButtonStart.whenPressed(new RaiseHood());
		auxButtonReset.whenPressed(new LowerHood());
		
		auxButtonRightBumper.whenPressed(new BackFlapClose());
		auxButtonLeftBumper.whenPressed(new BackFlapOpen());
		
	}

	/**
	 * Returns the axis value of the Left DriverStick
	 */
	public double getDriveLeftAxis()
	{
		if (drivestick.getRawAxis(3) < 0)
		{
			return ((((-RobotMap.mod + 1) * drivestick.getRawAxis(3)) + 1) * drivestick
					.getRawAxis(2)); // James' "Super Secret" Idea implemented
										// on the left

		} else
		{

			return drivestick.getRawAxis(2); // this is supposed to be the left
												// axis stick
		}
	}

	/**
	 * Returns the axis value of the Right DriverStick
	 * 
	 * @return
	 */
	public double getDriveRightAxis()
	{
		if (drivestick.getRawAxis(3) < 0)
		{
			return ((((-RobotMap.mod + 1) * drivestick.getRawAxis(3)) + 1) * drivestick
					.getRawAxis(5)); // James' "Super Secret" Idea implemented
										// on the right

		} else
		{

			return drivestick.getRawAxis(5); // this is supposed to be the right
												// axis stick
		}
	}

	
	public double getAuxLeftStick()
	{
		return auxstick.getRawAxis(2); // this is supposed to be the left
	}

	/**
	 * Returns the axis value of the Right Auxillary Stick
	 * 
	 * @return
	 */
	public double getAuxRightStick()
	{
		return auxstick.getRawAxis(5); // this is supposed to be the right
	}
}
