package frc2168.commands;
import edu.wpi.first.wpilibj.command.Command;
import frc2168.commands.CommandBase;
import frc2168.commands.HighGoalAuto;
import frc2168.commands.LowerHood;
import frc2168.commands.MiddleGoalAuto;



public class ButtonBoxPoints extends CommandBase {
	
	static int firemethod = 0;
	
	public ButtonBoxPoints(int mode){
		mode=firemethod;			//firemethod: 0=front shot, 1=side shot, 2=key shot, 3=long shot.
	}

	protected void initialize() {
		// TODO Auto-generated method stub

	}

	protected void execute() {
		if (oi.ioDigital10.get()==true) {		//Switch for shooting mode from 3 points to 2 points
			if(firemethod == 0){				//The firing method. High goal shot, side goal shot, key goal shot, long shot.
				new HighGoalAuto();				//high front goal shot
			} else if (firemethod == 1){
				new HighGoalAuto();				//high side goal shot
			} else if (firemethod == 2){
				new HighGoalAuto();				//high key goal shot
			} else if (firemethod == 3){
				//new							//The long shot
			}
		}
		
		else if (oi.ioDigital10.get()==false) {
			if(firemethod == 0){				//The firing method. mids goal shot, side goal shot, key goal shot, long shot.
				new HighGoalAuto();				//mids front goal shot
			} else if (firemethod == 1){
				new HighGoalAuto();				//mids side goal shot
			} else if (firemethod == 2){
				new HighGoalAuto();				//mids key goal shot
			} else if (firemethod == 3){
				//new							//The long shot
			}
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
