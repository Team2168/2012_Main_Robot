package frc2168;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc2168.commands.DriveToSpeed;

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
	public Joystick drivestick = new Joystick(RobotMap.driverJoyStick);
	public Joystick auxstick = new Joystick(RobotMap.auxJoystick);
	public Button button1 = new JoystickButton(drivestick, 1),
			button2 = new JoystickButton(drivestick, 2),
			button3 = new JoystickButton(drivestick, 3),
			button4 = new JoystickButton(drivestick, 4),
			button5 = new JoystickButton(drivestick, 5),
			button6 = new JoystickButton(drivestick, 6),
			button7 = new JoystickButton(drivestick, 7),
			button8 = new JoystickButton(drivestick, 8);
	
	public Button auxButton1 = new JoystickButton(auxstick, 1),
			auxButton2 = new JoystickButton(auxstick, 2),
			auxButton3 = new JoystickButton(auxstick, 3),
			auxButton4 = new JoystickButton(auxstick, 4),
			auxButton5 = new JoystickButton(auxstick, 5),
			auxButton6 = new JoystickButton(auxstick, 6),
			auxButton7 = new JoystickButton(auxstick, 7),
			auxButton8 = new JoystickButton(auxstick, 8);

	public OI() {
		button1.whenPressed(new DriveToSpeed());

	}

	/**
	 * Returns the axis value of the Left DriverStick
	 */
	public double getLeftSpeed() {
		if (drivestick.getRawAxis(3) < 0) {
			return ((((-RobotMap.mod + 1) * drivestick.getRawAxis(3)) + 1) * drivestick
					.getRawAxis(2)); // James' "Super Secret" Idea implemented
										// on the left

		} else {

			return drivestick.getRawAxis(2); // this is supposed to be the left
												// axis stick
		}
	}

	/**
	 * Returns the axis value of the Right DriverStick
	 * 
	 * @return
	 */
	public double getRightSpeed() {
		if (drivestick.getRawAxis(3) < 0) {
			return ((((-RobotMap.mod + 1) * drivestick.getRawAxis(3)) + 1) * drivestick
					.getRawAxis(5)); // James' "Super Secret" Idea implemented
										// on the right

		} else {

			return drivestick.getRawAxis(5); // this is supposed to be the right
												// axis stick
		}
	}

}
