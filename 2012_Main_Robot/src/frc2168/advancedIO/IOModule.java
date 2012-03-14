package frc2168.advancedIO;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStationEnhancedIO;
import edu.wpi.first.wpilibj.DriverStationEnhancedIO.EnhancedIOException;
import edu.wpi.first.wpilibj.Module;
import edu.wpi.first.wpilibj.communication.ModulePresence.ModuleType;

/**
 * Class to interface with the Cypress Touch IO Module 
 * 
 * Note: Driver Station must be placed in Enhanced IO mode
 *  > From the I/O tab, click "Configure", Select "Enhanced", Click "OK"
 * 
 * Board Pinout (looking top down):
 *                   J2      J3                      Notes
 *          VDDIO V+ []      []  VDDIO V+             Configurable via J1 and J4
 *        P0_0 AI 1  []      []  P0_1  AI 2           14-bit, 0 - 3.3V
 *        P0_2 AI 3  []      []  P0_3  AI 4           14-bit, 0 - 3.3V
 *        P0_4 AI 5  []      []  P0_5  AI 6           14-bit, 0 - 3.3V
 *        P0_6 AI 7  []      []  P0_7  AI 8           14-bit, 0 - 3.3V
 *  P4_4  Digital 1  []      []  P4_5  Digital 2      PWM Generators
 *  P4_6  Digital 3  []      []  P4_7  Digital 4      PWM Generators
 *  P6_0  Digital 5  []      []  P6_1  Digital 6      Quadrature Encoders
 *  P6_2  Digital 7  []      []  P6_3  Digital 8      Quadrature Encoders
 *  P6_4  Digital 9  []      []  P6_5  Digital 10     Quadrature Encoders
 *  P6_6  Digital 11 []      []  P6_7  Digital 12
 *  P12_2 Digital 13 []      []  P12_3 Digital 14     High current sink (25mA) output
 *  P2_6  Digital 15 []      []  P2_7  Digital 16     Analog comparator
 *               GND []      []  GND
 *               
 *               
 *                                                      
 *                    [1 2 3]    J1                   | J1 and J4 set up voltage on VDDIO pins (above)  
 *                                                    |   V+ output at 3.3V: Jump pins 2 and 3 on J1 and J4
 *     [1 2 3]    -------------  J4                   |   V+ output at 5V:   Jump pins 1 and 2 on J1 and J4
 *                ( + )   ( - )  9v Battery
 *  
 *  
 *  All digital lines are configurable:
 *   - Open-drain input
 *   - 5k pull-up input
 *   - 5k pull-down input
 *   - Active drive output
 *     - 4mA current source
 *     - 8mA current sink
 *  
 * @author James
 *
 */

public class IOModule {
		private static DriverStationEnhancedIO module;
		private final double HIGH_AN_VOLTAGE = 3.3;
		
		/**
		 * Initialize the IOModule
		 */
		public IOModule(){
			module = DriverStation.getInstance().getEnhancedIO();
			
			try {
				//Configure digital inputs w/ pull ups
				module.setDigitalConfig(1, DriverStationEnhancedIO.tDigitalConfig.kInputPullUp);
				module.setDigitalConfig(2, DriverStationEnhancedIO.tDigitalConfig.kInputPullUp);
				module.setDigitalConfig(3, DriverStationEnhancedIO.tDigitalConfig.kInputPullUp);
				module.setDigitalConfig(4, DriverStationEnhancedIO.tDigitalConfig.kInputPullUp);
				module.setDigitalConfig(5, DriverStationEnhancedIO.tDigitalConfig.kInputPullUp);
				module.setDigitalConfig(6, DriverStationEnhancedIO.tDigitalConfig.kInputPullUp);
				module.setDigitalConfig(7, DriverStationEnhancedIO.tDigitalConfig.kInputPullUp);
				module.setDigitalConfig(8, DriverStationEnhancedIO.tDigitalConfig.kInputPullUp);
				module.setDigitalConfig(9, DriverStationEnhancedIO.tDigitalConfig.kInputPullUp);
				module.setDigitalConfig(10, DriverStationEnhancedIO.tDigitalConfig.kInputPullUp);
				module.setDigitalConfig(11, DriverStationEnhancedIO.tDigitalConfig.kInputPullUp);
				module.setDigitalConfig(12, DriverStationEnhancedIO.tDigitalConfig.kInputPullUp);
				module.setDigitalConfig(13, DriverStationEnhancedIO.tDigitalConfig.kInputPullUp);
				module.setDigitalConfig(14, DriverStationEnhancedIO.tDigitalConfig.kInputPullUp);
				module.setDigitalConfig(15, DriverStationEnhancedIO.tDigitalConfig.kInputPullUp);
				module.setDigitalConfig(16, DriverStationEnhancedIO.tDigitalConfig.kInputPullUp);
			} catch (EnhancedIOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		/**
		 * Get the voltage on a particular analog channel
		 * 
		 * @param channel the analog channel to get the voltage of (Value.AI_#)
		 * @return the voltage of the requested channel. Returns 0 if there was an issue
		 */
		public double getAnalog(int channel){
			return getNormalizedAnalog(channel) * 3.3;
		}
		
		/**
		 * Get the voltage on a particular channel normalized over its full range (0 to 1.0).
		 * Negative voltages and voltages above 3.3 will be clipped
		 * 
		 * @param channel the analog channel to get the voltage of (Value.AI_#)
		 * @return the normalized voltage of the requested channel. Returns 0 if there was an issue
		 */
		public double getNormalizedAnalog(int channel){
			double val;
			
			try {
				val = module.getAnalogInRatio(channel);
			} catch (EnhancedIOException e) {
				System.out.println("Error getting the value for analog channel " + channel);
				e.printStackTrace();
				return 0;
			}
			
			if (val < 0){
				val = 0;			//Clip voltages below zero
			} else if(val > 1){
				val = 1;			//Clip voltages above 3.3v
			}
			
			return val;
		}
		
		public double getTimeDelay(int channel, double maxValue, double minValue){
			return ((maxValue-minValue)*getNormalizedAnalog(channel)+minValue);
		}
}
