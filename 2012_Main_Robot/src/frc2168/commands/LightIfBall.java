package frc2168.commands;

import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Command;
import frc2168.RobotMap;

public class LightIfBall extends Command {
	
	AnalogChannel ballLight = new AnalogChannel(RobotMap.ballLight);
	Relay backLight= new Relay(RobotMap.backLight);
	int LIGHT_CYCLES = 25; // Cycles to wait before turning off light
	int prevState; 
	
	public LightIfBall(){
		//System.out.println("LightIfBall Class Started");
	}

	protected void initialize() {
		// TODO Auto-generated method stub
		
		prevState = LIGHT_CYCLES;
	}

	protected void execute() {
		
		if(ballLight.getVoltage() >= RobotMap.ballPresentVoltage
				|| prevState<LIGHT_CYCLES && prevState>0){
			backLight.set(Relay.Value.kForward);
			//System.out.println("Set kForward");
			prevState--;
		} else {
			backLight.set(Relay.Value.kOff);
			//System.out.println("Set kOff");
			prevState=LIGHT_CYCLES;
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

}
