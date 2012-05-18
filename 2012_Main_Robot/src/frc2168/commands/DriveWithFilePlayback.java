package frc2168.commands;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Vector;

import javax.microedition.io.Connector;

import com.sun.squawk.io.BufferedReader;
import com.sun.squawk.microedition.io.FileConnection;
import com.sun.squawk.util.StringTokenizer;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import frc2168.CommandBasedRobot;

public class DriveWithFilePlayback extends CommandBase
{
	//objects for writing file	
	public static InputStreamReader in;
	public DataInputStream theInputFile;
	public FileConnection inputfc;
	public static BufferedReader myReader;
	public StringTokenizer myTokens;
	
	Vector countVector;
	Vector timeVector;
	Vector leftMotor;
	Vector rightMotor;
	
	String filename;
	DriverStation ds;
	
	int count;
	
	public DriveWithFilePlayback(String filename)
	{
		
		this.filename=filename;
//		countVector = new Vector(50);
//		timeVector = new Vector(50);
//		leftMotor = new Vector(50);
//		rightMotor = new Vector(50);
		ds = DriverStation.getInstance();
		
		count=0;
		requires(driveTrain);

	}
	

	protected void initialize()
	{

		count=0;
		

	}

	protected void execute()
	{
		//if(ds.)//for timing we wait for a new data packet
		{
			Double left=(Double)CommandBasedRobot.leftMotor.elementAt(count);
	    	Double right=(Double)CommandBasedRobot.rightMotor.elementAt(count);
	    	driveTrain.TankDrive(left.doubleValue(), right.doubleValue());
	    	System.out.println( count++ +"\t" + System.currentTimeMillis() +"\t" + left.doubleValue() + "\t" + right.doubleValue() );
		}
	}

	protected boolean isFinished()
	{
		// TODO Auto-generated method stub
		return count>=CommandBasedRobot.leftMotor.size();
	}

	protected void end()
	{
		// TODO Auto-generated method stub

	}

	protected void interrupted()
	{
		// TODO Auto-generated method stub

	}

}
