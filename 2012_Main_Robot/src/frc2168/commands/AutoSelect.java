package frc2168.commands;

import frc2168.*;

public class AutoSelect extends CommandBase {		//Digital input. Divided into four channels. In binary. Binary Coded Decimal.

	public AutoSelect(){
		
	}
	
	protected void initialize() {
		// TODO Auto-generated method stub
		
		

	}

	protected void execute() {
		// TODO Auto-generated method stub
		
		if (getDialState()==0){
			new KeyToTopAutoCommand();		//threePointShot;
		} else if (getDialState()==1){
			new KeyToMiddleAutoCommand();	//twoPointShot;
		} else if (getDialState()==2){
		//	new driveThreeShot;				//HighGoalAuto + Driving up.
		} else if (getDialState()==3){
		//	new driveTwoShot;				//MiddleGoalAuto + Driving up.
		} else if (getDialState()==4){
		//	new keyPlusBridge;				//KeyToTopAutoCommand + Drive away.
		} else if (getDialState()==5){
		//	new shootPlusBackLoad;			//KeyToTopAutoCommand + Loading pause.
		} else if (getDialState()==6){
		//	new shootPlusFrontLoad;			//KeyToTopAutoCommand + LiftRunning.
		} else if (getDialState()==7){
		//	new shootKinect;				//	>Kinect
		} else{
		//	new threePointShot;				//OShit. Some error. So handy-dandy backup autonomous!! KeyToTopAutoCommand.
		}
		
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
	
	protected int getDialState(){
		
		int holder = 0;
		if(oi.ioDigital1.get()==true){
			holder=+1;
		} else if (oi.ioDigital14.get()==true){
			holder=+2;
		} else if (oi.ioDigital15.get()==true){
			holder=+4;
		} else if (oi.ioDigital16.get()==true){
			holder=+8;
		}
		
		return holder;
	}

}
