/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc2168;

/**
 * Working Command Base Robot Code Template with CAN Drive
 */
import java.io.DataOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.Writer;

import javax.microedition.io.Connector;

import com.sun.squawk.io.BufferedWriter;
import com.sun.squawk.microedition.io.FileConnection;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import frc2168.commands.*;
import frc2168.dashboard.CompetitionDashboard;
import frc2168.dashboard.ShooterWheelDebugDashboard;
import frc2168.dashboard.ShooterWheelPIDDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class CommandBasedRobot extends IterativeRobot {

    Compressor compressor;

	//Create commands for Atonomous and Teleop Periods
    Command TeleopHoodDefault;
    Command TeleopDriveTrainDefault;
    Command TeleopDriveElevator;
    Command dashboard;
    Command autonomousCommand;
    Command fire;
    Command ballLight;
    
	public static PrintStream out;
	public DataOutputStream theFile;
	public FileConnection fc;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	
    	//Add all functions which should start when the robot is enabled here
    	//such as compressor..etc
        
    	
    	// This init() function basically calls OI.Java
    	CommandBase.init();
    	
    	//start compressor
    	compressor = new Compressor(RobotMap.compressorSwitch, RobotMap.compressorRelay);
        compressor.start();
    	
    	// instantiate the command used for the autonomous period

    	
    	// instantiate the command used for the teleop period
//        TeleopHoodDefault = new ShooterWheelJoystick();
//        TeleopDriveTrainDefault = new DriveWithJoystick();
//        TeleopDriveElevator = new DriveElevatorJoystick();
        dashboard = new CompetitionDashboard();
       // dashboard = new ShooterWheelPIDDashboard();
        
        
         
        
        //shoot 2 points auto
    	autonomousCommand = new Auto_MidGoalFromKey();
        
        //shoot 3 points auto
        //autonomousCommand = new KeyToTopAutoCommand();
    	
    	ballLight = new LightIfBall();
    }

    public void autonomousInit() {
        // schedule the autonomous command (example).
    	
    	
    	autonomousCommand.start();
        dashboard.start();
        ballLight.start();
        
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() 
    {
        Scheduler.getInstance().run();
    }

    public void teleopInit() 
    {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to 
		// continue until interrupted by another command, remove
		// this line or comment it out.
		autonomousCommand.cancel();
    	//TeleopHoodDefault.start();
    	//TeleopDriveTrainDefault.start();
    	//TeleopDriveElevator.start();
    	dashboard.start();
    	
    	ballLight.start();

    	
    	try{
    		fc= (FileConnection)Connector.open("file:///"+System.currentTimeMillis()+"_PIDoutput.txt", Connector.WRITE);
    		
    		fc.create();
    		theFile = fc.openDataOutputStream();
    		out = new PrintStream(theFile);
    	} catch (Exception e){
    	
    	}
    	
    	
    	
    	out.println("count" + "\t" + "time(milsec)" + "\t" + "shooterWheel1Voltage" + "\t" + "shooterWheel2Voltage" +"\t" + "shooterWheel1Current"+ "\t" + "shooterWheel2Current" + "\t" + "encoderRate(in/sec)");
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() 
    {
        Scheduler.getInstance().run();
        
    }
}
