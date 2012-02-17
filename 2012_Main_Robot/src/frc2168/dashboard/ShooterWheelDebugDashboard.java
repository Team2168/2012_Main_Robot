package frc2168.dashboard;

import edu.wpi.first.wpilibj.buttons.InternalButton;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.networktables.NetworkTableKeyNotDefined;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc2168.commands.BackFlapClose;
import frc2168.commands.BackFlapOpen;
import frc2168.commands.CommandBase;
import frc2168.commands.DriveElevatorJoystick;
import frc2168.commands.LowerBridge;
import frc2168.commands.LowerHood;
import frc2168.commands.RaiseBridge;
import frc2168.commands.RaiseHood;
import frc2168.commands.ShooterWheelJoystick;

public class ShooterWheelDebugDashboard extends CommandBase
{
	
	public ShooterWheelDebugDashboard()
	{
        //Show what command your subsystem is running on the SmartDashboard
        SmartDashboard.putData(driveTrain);
        SmartDashboard.putData(hood);
        SmartDashboard.putData(bridgeArm);
        SmartDashboard.putData(elevatorFlap);
        
        //add buttons on the dashboard to call commands
		SmartDashboard.putData(new LowerBridge());
		SmartDashboard.putData(new RaiseBridge());
		SmartDashboard.putData(new LowerHood());
		SmartDashboard.putData(new RaiseHood());
		SmartDashboard.putData(new BackFlapClose());
		SmartDashboard.putData(new BackFlapOpen());
		
		//show the scheduler
		SmartDashboard.putData("Scheduler",Scheduler.getInstance());
		
		
		//return commands to joysticks for everything required below
		SmartDashboard.putData(new DriveElevatorJoystick());
		SmartDashboard.putData(new ShooterWheelJoystick());	
		
		
		//create text boxes for entry
		SmartDashboard.putDouble("elevator", 0);
		SmartDashboard.putDouble("shooter", 0);

		
		//require subsystems
		requires(elevatorFlap);
		requires(hood);
	
		
	}

	//@Override
	protected void initialize()
	{
		// TODO Auto-generated method stub
		
	}

	//@Override
	protected void execute()
	{
	
		//put encoder data on screen
		SmartDashboard.putDouble("shooterEncoder", hood.shooterWheelController.getEncoderRate());
		
		//drive shooter wheel
		try
		{
			elevatorFlap.setBallElevatorSpeed(SmartDashboard.getDouble("elevator"));
			hood.spinMotor(SmartDashboard.getDouble("shooter"));
		} catch (NetworkTableKeyNotDefined e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		


	}

	//@Override
	protected boolean isFinished()
	{
		// TODO Auto-generated method stub
		return false;
	}

	//@Override
	protected void end()
	{
		// TODO Auto-generated method stub

	}

	//@Override
	protected void interrupted()
	{
		// TODO Auto-generated method stub
		SmartDashboard.putData(new ShooterWheelDebugDashboard());
	}

}
