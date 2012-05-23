package frc2168.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc2168.OI;
import frc2168.subsystems.BridgeArm;
import frc2168.subsystems.Camera;
import frc2168.subsystems.DriveTrain;
import frc2168.subsystems.Hood;
import frc2168.subsystems.ElevatorWithFlap;


/**
 * @author Kevin Harrilal, First Robotics Team 2168
 * <br><br>
 * The base for all commands. All atomic commands should subclass CommandBase.
 * CommandBase creates and stores each Control Object. To access a
 * subsystem elsewhere in your code in your code use CommandBase.exampleSubsystem
 * 
 */
public abstract class CommandBase extends Command {

    public static OI oi;
    // Create a single static instance of all of your subsystems
    public static DriveTrain driveTrain = new DriveTrain ();
    public static Hood hood = new Hood();
    public static BridgeArm bridgeArm = new BridgeArm();
    public static ElevatorWithFlap elevatorFlap = new ElevatorWithFlap();
    public static Camera camera = new Camera();

    public static void init() {
        // This MUST be here. If the OI creates Commands (which it very likely
        // will), constructing it during the construction of CommandBase (from
        // which commands extend), subsystems are not guaranteed to be
        // yet. Thus, their requires() statements may grab null pointers. Bad
        // news. Don't move it.
        oi = new OI();        
       

    }

    public CommandBase(String name) 
    {
        super(name);
    }

    public CommandBase() 
    {
        super();
    }
}
