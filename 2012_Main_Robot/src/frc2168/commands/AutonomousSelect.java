package frc2168.commands;

import frc2168.*;

public class AutonomousSelect extends CommandBase {		//Digital input. Divided into four channels. In binary. Binary Coded Decimal.

	public AutonomousSelect(){
		
	}
	
	protected void initialize() {
		// TODO Auto-generated method stub
		
		

	}

	protected void execute() {
		// TODO Auto-generated method stub
		
		if (getDialState()==0){
			new AutonomousShootFromKey(oi.ioDigital10.get());	//shoot from key, target read form button box switch
		}
		else if (getDialState()==1){
			new AutonomousDriveAndShoot(oi.ioDigital10.get());//target read form button box switch + Driving up.
		}
		else if (getDialState()==2){
			new KeyPlusBridge();				//KeyToTopAutoCommand + Drive away.
		}
		else if (getDialState()==3){
			new ShootPlusBackLoad();			//KeyToTopAutoCommand + Loading pause.
		}
		else if (getDialState()==4){
			new ShootPlusFrontLoad();			//KeyToTopAutoCommand + LiftRunning.
		}
		else if (getDialState()==5){
			new ShootKinect();				//	>Kinect
		}
		else{
			new AutonomousShootFromKey(oi.ioDigital10.get());				//OShit. Some unforeseen error. So handy-dandy backup autonomous, just in case! KeyToTopAutoCommand.
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
		if(oi.ioDigital1.get()){
			holder += 1;
		} else if (oi.ioDigital14.get()){
			holder += 2;
		} else if (oi.ioDigital15.get()){
			holder += 4;
		} else if (oi.ioDigital16.get()){
			holder += 8;
		}
		
		return holder;
	}

}
