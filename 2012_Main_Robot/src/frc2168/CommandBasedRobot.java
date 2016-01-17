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
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.Writer;
import java.util.Date;
import java.util.Vector;

import javax.microedition.io.Connector;

import com.sun.squawk.io.BufferedReader;
import com.sun.squawk.io.BufferedWriter;
import com.sun.squawk.microedition.io.FileConnection;
import com.sun.squawk.util.StringTokenizer;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Relay.Value;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
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
    
    SendableChooser autoChooser;
    SendableChooser dashChooser;
    SendableChooser playbackChooser;
    
    //objects for reading file
	public static PrintStream out;
	public static DataOutputStream theFile;
	public static FileConnection fc;
	
	public static String playback;
	
	//objects for writing file	
	public static InputStreamReader in;
	public DataInputStream theInputFile;
	public FileConnection inputfc;
	public static BufferedReader myReader;
	public StringTokenizer myTokens;
	public static Vector countVector = new Vector(50);
	public static Vector timeVector = new Vector(50);
	public static Vector leftMotor = new Vector(50);
	public static Vector rightMotor = new Vector(50);
	
	
	
	

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	
    	//Add all functions which should start when the robot is enabled here
    	//such as compressor..etc
        
    	
    	// This init() function basically calls OI.Java
    	CommandBase.init();
    	
//    	//enable the network table
//    	try {
//			//NetworkTable.initialize();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
    	
    	Relay comp = new Relay(2);
    	comp.set(Value.kForward);
    	
    	//start compressor
    	compressor = new Compressor(RobotMap.compressorSwitch, RobotMap.compressorRelay);
        compressor.start();
        
        
        
    	
        //Initialize auto mode chooser
        autoSelectInit();
        
        //Initialize dashboard
        dashSelectInit();

        //Initialize playback
        playbackSelectInit();
        
        
    	ballLight = new LightIfBall();
    	
    	
	
    }

    public void autonomousInit() {
        // schedule the autonomous command (example).
    	
    	//get which auto command to run
    	autonomousCommand = (Command) autoChooser.getSelected();
    	autonomousCommand.start();
        
    	//start dashboard
    	dashboard = (Command) dashChooser.getSelected();
    	dashboard.start();
    	
    	playback = (String) playbackChooser.getSelected();
    	if (playback=="Playback")
    		readFile();
    	
    	//run light
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
		if (autonomousCommand!=null)
			autonomousCommand.cancel();
		System.out.println("Teleop Init");
		
    	//start dashboard
    	dashboard = (Command) dashChooser.getSelected();
    	dashboard.start();
    	
    	playback = (String) playbackChooser.getSelected();
    	if (playback=="Record")
    		writeFile();
    	
    	ballLight.start();
    	


 
//    	out.println("count" + "\t" + "time(milsec)" + "\t" + "driveTrainLeft" + "\t" + "driveTrainRight");// +"\t" + "shooterWheel1Current"+ "\t" + "shooterWheel2Current" + "\t" + "encoderRate(in/sec)");
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() 
    {
        Scheduler.getInstance().run();
        
    }
    
    
    public void disabledInit() 
    {
    	if(out!=null)
    	{
	    	out.flush();
	        out.close();
    	}
    	

    }
    
    private void readFile()
    {
    	
		//open a input stream to the file
    	try
    	{           
			inputfc= (FileConnection)Connector.open("file:///"+RobotMap.filename, Connector.READ);
	    	theInputFile=inputfc.openDataInputStream();
	    	in = new InputStreamReader(theInputFile);
	    	myReader = new BufferedReader(in);
	    	
    	}
    	catch (Exception e)
    	{
    	
    	}
    	
    	
    	//read the data from the file and store it in a vector
    	String line;
    	try
		{
    		while ((line = myReader.readLine()) != null)
    		{

	    		myTokens = new StringTokenizer(line,"\t\n");
	    		int count = Integer.parseInt(myTokens.nextToken());
	    		int time = Integer.parseInt(myTokens.nextToken());
	    		double first = Double.parseDouble(myTokens.nextToken());
	    		double second = Double.parseDouble(myTokens.nextToken());
	    		
	    		countVector.addElement(new Integer(count));
	    		timeVector.addElement(new Integer(time));
	    		leftMotor.addElement(new Double(first));
	    		rightMotor.addElement(new Double(second));
    		
    		}
		} 
    	catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    	System.out.println("Size of Vector: " + rightMotor.size());
    }
    
    private void writeFile()
    {
	//open file to write
		try
		{
			
			fc= (FileConnection)Connector.open("file:///myauto1.txt", Connector.WRITE);
			fc.create();
			theFile = fc.openDataOutputStream();
			out = new PrintStream(theFile);
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
    }
    
    
    private void autoSelectInit()
    {
        autoChooser = new SendableChooser();
        autoChooser.addDefault("3-point Auto", new KeyToTopAutoCommand());
        autoChooser.addObject("2-point Auto", new Auto_MidGoalFromKey());
        SmartDashboard.putData("Autonomous mode chooser", autoChooser);
    }
    
    private void dashSelectInit()
    {
        dashChooser = new SendableChooser();
        dashChooser.addDefault("Competition Dash", new CompetitionDashboard());
        dashChooser.addObject("PID Debug Dash", new ShooterWheelPIDDashboard());
        SmartDashboard.putData("Dashboard Chooser", dashChooser);
    }
    
    private void playbackSelectInit()
    {
    	playbackChooser = new SendableChooser();
    	playbackChooser.addDefault("Do Nothing", "Do Nothing");
    	playbackChooser.addObject("Record", "Record");
    	playbackChooser.addObject("Playback", "Playback");
        SmartDashboard.putData("Playback/Record", playbackChooser);
    }
    
    
    
}
