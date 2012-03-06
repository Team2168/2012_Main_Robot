package frc2168.commands;
import edu.wpi.first.wpilibj.command.Command;
import frc2168.commands.CommandBase;
import frc2168.commands.HighGoalAuto;
import frc2168.commands.LowerHood;
import frc2168.commands.MiddleGoalAuto;



public class ButtonBoxPoints extends CommandBase {

	protected void initialize() {
		// TODO Auto-generated method stub

	}

	protected void execute() {
		if (oi.ioDigital10.get()==true) {		//Switch for shooting mode
			new HighGoalAuto();
		}
		
		else if (oi.ioDigital10.get()==false) {
			new MiddleGoalAuto();
		}
		// TODO Auto-generated method stub

	}

	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

	protected void end() {
		// TODO Auto-generated method stub

	}

	protected void interrupted() {
		// TODO Auto-generated method stub

	}

}
