package frc2168.dashboard;

import edu.wpi.first.wpilibj.buttons.InternalButton;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.networktables.NetworkTableKeyNotDefined;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc2168.RobotMap;
import frc2168.commands.BackFlapClose;
import frc2168.commands.BackFlapOpen;
import frc2168.commands.CommandBase;
import frc2168.commands.DriveElevatorJoystick;
import frc2168.commands.LowerBridge;
import frc2168.commands.LowerHood;
import frc2168.commands.RaiseBridge;
import frc2168.commands.RaiseHood;
import frc2168.commands.ShooterWheelJoystick;
import frc2168.commands.driveShooterPID;

public class ShooterWheelPIDDashboard extends CommandBase
{
	
	public ShooterWheelPIDDashboard()
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
		SmartDashboard.putData(new driveShooterPID());
		
		
		//show the scheduler
		SmartDashboard.putData("Scheduler",Scheduler.getInstance());
		
		
		//return commands to joysticks for everything required below
		SmartDashboard.putData(new DriveElevatorJoystick());
		SmartDashboard.putData(new ShooterWheelJoystick());	
		
		
		//create text boxes for entry
		SmartDashboard.putDouble("elevatorVolatge", 0);
		SmartDashboard.putDouble("shooterVoltage", 0);
		
		SmartDashboard.putDouble("shooterSetPoint", 0);
		
		//show shooter gains
		SmartDashboard.putDouble("P", RobotMap.shooterP);
		SmartDashboard.putDouble("I", RobotMap.shooterI);
		SmartDashboard.putDouble("D", RobotMap.shooterD);
		

		
		//require subsystems
		//requires(elevatorFlap);
		//requires(hood);
	
		
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
		SmartDashboard.putDouble("Controller Output", hood.shooterWheelController.getCo());
		
		
		//drive shooter wheel
		try
		{
			elevatorFlap.setBallElevatorSpeed(SmartDashboard.getDouble("elevatorVolatge"));
			hood.spinMotor(SmartDashboard.getDouble("shooterVoltage"));
			hood.shooterWheelController.setSp(SmartDashboard.getDouble("shooterSetPoint"));
			
			
			//get shooter gains from dashboard
			hood.shooterWheelController.setpGain(SmartDashboard.getDouble("P"));
			hood.shooterWheelController.setiGain(SmartDashboard.getDouble("I"));
			hood.shooterWheelController.setdGain(SmartDashboard.getDouble("D"));
			
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
		SmartDashboard.putData(new ShooterWheelPIDDashboard());
	}

}
