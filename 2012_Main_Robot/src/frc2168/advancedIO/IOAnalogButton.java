package frc2168.advancedIO;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStationEnhancedIO.EnhancedIOException;
import edu.wpi.first.wpilibj.buttons.Button;

/**
 * Create a button from an analog input channel on the DriverStation IO Module
 * 
 * @author James
 *
 */
public class IOAnalogButton extends Button {
	
    private double THRESHOLD;
    private int port;
    private boolean triggerGT;

    /**
     * Create a new button from an analog input on the Driverstation IO Module with default threshold
     * Button is considered pressed above the threshold
     * 
     * @param port the analog input port on the IO Module (1 - 8)
     */
    public IOAnalogButton(int port) {
        this(port, 0.5, true);
    }
    
    /**
     * Create a new button from an analog input on the Driverstation IO Module
     * Button is considered pressed when the %voltage exceeds the threshold
     * 
     * @param port the analog input port on the IO Module (1 - 8)
     * @param threshold the % voltage range which causes the button to trigger (0 - 1)
     */
    public IOAnalogButton(int port, double threshold) {
        this(port, threshold, true);
    }
    
    /**
     * Create a new button from an analog input on the Driverstation IO Module
     * 
     * @param port the analog input port on the IO Module (1 - 8)
     * @param threshold the % voltage range which causes the button press to trigger (0 - 1)
     * @param triggerGreaterThan is button considered pressed above or below the threshold? true = above 
     */
    public IOAnalogButton(int port, double threshold, boolean triggerGreaterThan) {
        this.port = port;
        setThreshold(threshold);
        triggerGT = triggerGreaterThan;
    }

    /**
     * Button is pressed when the analog value is greater/less than the defined threshold value (percentage turned)
     */
    public boolean get() {
    	double analogVal; 
    	
        try {
            analogVal = DriverStation.getInstance().getEnhancedIO().getAnalogInRatio(port);
        } catch (EnhancedIOException ex) {
            return false;
        }
        
        if(triggerGT){
        	return analogVal > THRESHOLD;	//Is the channel greater than the threshold?
        } else {
        	return analogVal < THRESHOLD;	//IS the channel less than the threshold?
        }
        
    }
    
    /**
     * Set the threshold value above which the button will be considered pressed
     * 
     * @param threshold the threshold value in percent turned (0 to 1)
     */
    public void setThreshold(double threshold) {
    	if(threshold > 1){			//don't allow invalid threshold values
    		threshold = 1;
    	} else if(threshold < 0){
    		threshold = 0;
    	}
    	
    	THRESHOLD = threshold;
    }
    
    /**
     * Get the threshold value
     * 
     * @return the threshold value in percent turned (0 to 1)
     */
    public double getThreshold(){
    	return THRESHOLD;
    }

}
