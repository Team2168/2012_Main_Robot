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
import frc2168.commands.PIDShootBall;
import frc2168.commands.PID_ShooterPause;
import frc2168.commands.RaiseBridge;
import frc2168.commands.RaiseHood;
import frc2168.commands.ShooterWheelJoystick;
import frc2168.commands.PID_DriveShooter;
import frc2168.commands.shootSingleBall;

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
		SmartDashboard.putData(new PID_DriveShooter());
		
		//create virtual button to hold
		InternalButton shootBallButton = new InternalButton();
		SmartDashboard.putData("shootBall",shootBallButton);
		shootBallButton.whenPressed(new shootSingleBall());

		
		//show the scheduler
		SmartDashboard.putData("Scheduler",Scheduler.getInstance());
		
		
		//return commands to joysticks for everything required below
		SmartDashboard.putData(new DriveElevatorJoystick());
		SmartDashboard.putData(new ShooterWheelJoystick());	
		
		
		//create text boxes for entry

		SmartDashboard.putDouble("shooterSetPoint", 0);
		
		//show shooter gains
		SmartDashboard.putDouble("P", RobotMap.shooterP);
		SmartDashboard.putDouble("I", RobotMap.shooterI);
		SmartDashboard.putDouble("D", RobotMap.shooterD);

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
		SmartDashboard.putBoolean("atSpeed", hood.shooterWheelController.atSpeed());
		SmartDashboard.putBoolean("enable", hood.shooterWheelController.isEnabled());
		SmartDashboard.putDouble("executionTime", hood.shooterWheelController.getExecutionTime());
		//SmartDashboard.putDouble("shooterSetPoint", hood.shooterWheelController.getSp());
		
		//drive shooter wheel
		try
		{

	
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

	}

}
