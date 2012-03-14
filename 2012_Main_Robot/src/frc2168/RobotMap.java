package frc2168;

import edu.wpi.first.wpilibj.DriverStationLCD;


/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	
	///////////////////////////SYSTEM CONSTANTS////////////////////////////
	//DriverStation LCD
	public static DriverStationLCD driverstation;

	//JOYSTICK MAPS	
	public static final int driverJoystick=1; //USB Port 1
	public static final int auxJoystick=2; //USB Port 2
	
	/////////////////////////////CRIO MAP//////////////////////////////
	///////////////////////Analog Card - Slot 1////////////////////////
	//Analog IO 1-8
	public static final int ballDetector = 1; //AIO 1, Analog Card
	
	///////////////////////Digital Card - Slot 2///////////////////////
	//Digital IO 1-14
	public static final int leftDriveTrainEncoder_A=1; //DIO 1, Digital Card
	public static final int leftDriveTrainEncoder_B=2; //DIO 2, Digital Card
	public static final int rightDriveTrainEncoder_A=3; //DIO 3, Digital Card
	public static final int rightDriveTrainEncoder_B=4; //DIO 4, Digital Card
	public static final int shooterWheelEncoderID_A = 5; //DIO 5, Digital Card
	public static final int shooterWheelEncoderID_B = 6; //DIO 6, Digital Card
	public static final int compressorSwitch = 7; //DIO 7, Digital Card
	
	//PWM 1-10
	public static final int lift1Victor = 1; //PWM 1, Digital Card
	public static final int lift2Victor = 2; //PWM 2, Digital Card
	
	//Relay 1-8
	public static final int compressorRelay = 1; //Relay Port 1, Digital Card
	
	
	///////////////////////Solenoid Card - Slot 3///////////////////////
	//Solenoid 1-8
	public static final int hoodSolenoidPortFwd = 1; //Port 1, Solenoid Card
	public static final int hoodSolenoidPortReverse = 2; //Port 2, Solenoid Card
	public static final int backFlapSolenoidClose = 3; //Port 3, Solenoid Card
	public static final int backFlapSolenoidOpen = 4; //Port 4, Solenoid Card
	public static final int shiftForwardChannel = 5; //Port 5, Solenoid Card
	public static final int shiftReverseChannel = 6; //Port 6 , Solenoid Card
	public static final int bridgeArmSolenoidReverseChannel = 7; //Port 7, Solenoid Card
	public static final int bridgeArmSolenoidForwardChannel = 8; //Port 8, Solenoid Card
	

	///////////////////////////SUBSYSTEM CONSTANTS///////////////////////////////
	///////////////////////////////DRIVETRAIN////////////////////////////////////	
	/*
	  The drivetrain uses four CAN motors. The image below represents a top view of the Chassis
	  so that the location of each motor can be identified. This image is to be used as a key
	  to determine the values of the CAN motor ids.
	  ____________ 
	  |			  |
	  |    FWD    |
	  |           |
	L1|           |R1
	  |           |
	L2|           |R2
	  |           |
	  |    AFT    |
	  |___________| 
	 */

	//Creating Static CAN IDs for DriveTrain Motors
	public static final int leftmotor1 = 13, rightmotor1 = 2;
	public static final int leftmotor2 = 17, rightmotor2 = 10;
	
	//Motor Invert Direction 
	public static final boolean invertRight = false;	//if true invert signal to right motors
	public static final boolean invertLeft = true;		//if true invert signal to left motors
	
	//Falcon Claw Brake Modifier
	public static final double mod = 0.125;	// Low minimum/modifier for the "Falcon Claw Function" or James' "Super Secret" Idea
	
	//Wheel Radius
	public static final int driveWheelRadius=2;//Colson wheel radius in inches
	
	//average encoder
	public static final int driveAvgEncoderVal = 10;
	
	//DriveTraincEncoder Parameters
	public static final int driveEncoderPulsePerRot=250;
	public static final double driveEencoderDistPerTick=(Math.PI*2*driveWheelRadius)/driveEncoderPulsePerRot;
	public static final int driveEncoderMinRate=10; 
	public static final int driveEncoderMinPeriod=10;
	public static final boolean leftDriveTrainEncoderReverse=false;
	public static final boolean rightDriveTrainEncoderReverse=false;
	
	//drivetrain controller steady state determination
	public static final int drivetrainArraySize = 50;
	public static final double drivetrainPercent = 0.1;
	
	//period to run PID loops
	public static final long driveTrainPIDPeriod =40;//40ms loop
	
	//PID Gains for Left Side & Right Side
	//Bandwidth =
	//Phase Margin = 
	public static final double driveTrainP = 0.00574562908722711;
	public static final double driveTrainI = 0.000308064641742337; 
	public static final double driveTrainD = -0.000130778888124088;
	
	
	////////////////////////////HOOD////////////////////////////////////////
	//Creating Static CAN IDs for Shooter Motors
	public static final int shooterWheelCANID = 11, shooterWheel2CANID = 12; 
	
	//Constant value for shooter wheel
	public static final double AUTO_KEY_TO_TOP = 840.0;
	
	public static final double MIDDLE_GOAL_SIDE = 550.0; //hood down
	public static final double HIGH_GOAL_SIDE = 730.0; //hood up

	public static final double LOW_GOAL_FORWARD = 430.0;
	public static final double MIDDLE_GOAL_FORWARD = 540.0; //hood up
	public static final double HIGH_GOAL_FORWARD = 650.0; //hood down
	
	
	//Hood Wheel Radius in inches
	public static final int shooterWheelRadius=3;
	
	//HoodEncoder Parameters
	public static final int shooterEncoderPulsePerRot=250;
	public static final double shooterEncoderDistPerTick=(Math.PI*2*shooterWheelRadius)/shooterEncoderPulsePerRot;
	public static final int shooterEncoderMinRate=10; 
	public static final int shooterEncoderMinPeriod=10;
	public static final boolean shooterEncoderReverse=true;
	
	//average encoder
	public static final int hoodAvgEncoderVal = 20;
	
	//hood controller steady state determination
	public static final int hoodArraySize = 60;
	public static final double hoodPercent = 0.05;
	
	public static final double shooterError = 5; //inches per second error
	
	//period to run PID loops
	public static final long shooterPIDPeriod =40;//40ms loop
	
	//PID Gains for shooter wheel
	//Bandwidth =
	//Phase Margin = 
	public static final double shooterP = 0.000974562908722711;
	public static final double shooterI = 0.000108064641742337; 
	public static final double shooterD = -0.000130778888124088;
	

	///////////////////////////////BRIDGE ARM/////////////////////////////////////
	
	//no other parameters
	
	///////////////////////////////Elevator////////////////////////////////////
	//ball detected
	public static final double ballPresentVoltage = 1.6;
	
	//Constant to drive elevator
	public static final double liftVoltage = -0.20;

	////////////////////////////////////////////////////////////////////////////
	
	/////////////////////CAMERA//////////////////
	public static final double[] centerDistances = {//distance to top (or bottom)
			16353,
			7093.43007549888,
			4351.77391665054,
			3076.91250755165,
			2351.46533882611,
			1887.66611521681,
			1567.66874044617,
			1334.67031864157,
			1158.07107085183,
			1019.9935764644,
			909.325250955527,
			818.811691688318,
			743.525208119007,
			680.006640488002,
			625.759525922541,
			578.939068007568,
			538.153893224924,
			502.335728217793,
			470.651392111149,
			442.441944114734,
			417.179718377181,
			394.437417204091,
			373.865503167576,
			355.175410016024,
			338.126902690675,
			322.518441458016,
			308.17975171287,
			294.966034071825,
			282.753408742143,
			271.435298795871,
			260.919534884395,
			251.126019500164,
			241.984829013635,
			233.434661007062,
			225.421556039892,
			217.897839083821,
			210.821237974315,
			204.1541454092,
			197.862998047114,
			191.917751669152,
			186.291435565974,
			180.959772593704,
			175.900853922367,
			171.094859542445,
			166.523817220407,
			162.171393894723,
			158.022714550478,
			154.064204456999,
			150.283451340554,
			146.669084625532,
			143.210669337746,
			139.898612642409,
			136.724081302589,
			133.678928603917,
			130.755629507814,
			127.947222976562,
			125.247260565313,
			122.649760503965,
			120.149166599659,
			117.74031138205,
			115.418382991062,
			113.178895372968,
			111.017661407047,
			108.930768633448,
			106.9145572944,
			104.965600436603,
			103.080685853526,
			101.256799672927,
			99.4911114180937,
			97.7809603913122,
			96.1238432456117,
			94.5174026260254,
			92.9594167749707,
			91.4477900080056,
			89.980543976464,
			88.5558096424748,
			87.1718198997949,
			85.8269027808828,
			84.5194751968144,
			83.2480371621166,
			82.0111664614386,
			80.8075137192894,
			79.6357978378949,
			78.4948017716396,
			77.383368609597,
			76.3003979403688,
			75.2448424758812,
			74.21570491296,
			73.2120350134604,
			72.2329268854715,
			71.2775164496972,
			70.3449790765244,
			69.4345273805724,
			68.5454091606639,
			67.6769054742021,
			66.8283288358765,
			65.9990215314727,
			65.1883540383328,
			64.395723544712,
			63.6205525609147,
			62.8622876156678,
			62.1203980317187,
			61.3943747751187,
			60.6837293730956,
			59.98799289581,
			59.3067149976605,
			58.6394630141293,
			57.9858211104684,
			57.3453894788017,
			56.7177835804755,
			56.1026334307223,
			55.4995829229195,
			54.9082891899198,
			54.3284220001124,
			53.7596631860417,
			53.2017061035628,
			52.6542551196544,
			52.1170251271418,
			51.5897410847012,
			51.0721375806294,
			50.5639584189648,
			50.0649562266401,
			49.5748920804372,
			49.093535152593,
			48.6206623739836,
			48.156058113881,
			47.6995138753441,
			47.2508280053637,
			46.8098054189392,
			46.376257336314,
			45.9500010326484,
			45.5308595994496,
			45.1186617171235,
			44.7132414380512,
			44.3144379796276,
			43.9220955267357,
			43.5360630431606,
			43.1561940914766,
			42.7823466609683,
			42.4143830031737,
			42.0521694746581,
			41.6955763866546,
			41.3444778612228,
			40.9987516936027,
			40.6582792204538,
			40.32294519369,
			39.9926376596369,
			39.6672478432516,
			39.3466700371606,
			39.0308014952853,
			38.7195423308346,
			38.4127954184605,
			38.1104663003773,
			37.8124630962623,
			37.5186964167606,
			37.2290792804276,
			36.9435270339527,
			36.6619572755131,
			36.3842897811176,
			36.1104464338046,
			35.840351155567,
			35.5739298418831,
			35.3111102987382,
			35.0518221820275,
			34.795996939236,
			34.5435677532974,
			34.294469488538,
			34.048638638615,
			33.8060132763659,
			33.5665330054871,
			33.3301389139646,
			33.096773529184,
			32.8663807746493,
			32.638905928245,
			32.4142955819761,
			32.1924976031273,
			31.9734610967833,
			31.7571363696543,
			31.543474895155,
			31.3324292796866,
			31.1239532300746,
			30.9180015221151,
			30.714529970188,
			30.5134953978938,
			30.3148556096749,
			30.118569363383,
			29.9245963437564,
			29.7328971367718,
			29.5434332048387,
			29.3561668628019,
			29.1710612547248,
			28.9880803314215,
			28.807188828711,
			28.6283522463675,
			28.4515368277385,
			28.2767095400097,
			28.1038380550896,
			27.9328907310935,
			27.7638365944049,
			27.5966453222925,
			27.4312872260638,
			27.2677332347361,
			27.105954879206,
			26.9459242769006,
			26.7876141168927,
			26.6309976454643,
			26.4760486521025,
			26.3227414559134,
			26.171050892438,
			26.0209523008593,
			25.8724215115835,
			25.7254348341861,
			25.5799690457077,
			25.43600137929,
			25.2935095131389,
			25.1524715598052,
			25.0128660557708,
			24.8746719513318,
			24.7378686007683,
			24.6024357527904,
			24.4683535412533,
			24.3356024761313,
			24.2041634347427,
			24.0740176532185,
			23.9451467182048,
			23.8175325587946,
			23.6911574386786,
			23.5660039485105,
			23.4420549984787,
			23.319293811078,
			23.1977039140761,
			23.0772691336675,
			22.95797358781,
			22.8398016797375,
			22.7227380916445,
			22.606767778536,
			22.4918759622387,
			22.3780481255684,
			22.2652700066483,
			22.1535275933745,
			22.042807118024,
			21.9330950520002,
			21.8243781007139,
			21.7166431985926,
			21.6098775042178,
			21.504068395584,
			21.3992034654768,
			21.2952705169672,
			21.1922575590171,
			21.0901528021948,
			20.988944654496,
			20.8886217172675,
			20.789172781231,
			20.6905868226039,
			20.592852999315
	};
	
	public static final double[] middleDistances = {
			449.9943,
			441.5846,
			433.2709,
			425.0532,
			416.9315,
			408.9058,
			400.9761,
			393.1424,
			385.4047,
			377.763,
			370.2173,
			362.7676,
			355.4139,
			348.1562,
			340.9945,
			333.9288,
			326.9591,
			320.0854,
			313.3077,
			306.626,
			300.0403,
			293.5506,
			287.1569,
			280.8592,
			274.6575,
			268.5518,
			262.5421,
			256.6284,
			250.8107,
			245.089,
			239.4633,
			233.9336,
			228.4999,
			223.1622,
			217.9205,
			212.7748,
			207.7251,
			202.7714,
			197.9137,
			193.152,
			188.4863,
			183.9166,
			179.4429,
			175.0652,
			170.7835,
			166.5978,
			162.5081,
			158.5144,
			154.6167,
			150.815,
			147.1093,
			143.4996,
			139.9859,
			136.5682,
			133.2465,
			130.0208,
			126.8911,
			123.8574,
			120.9197,
			118.078,
			115.3323,
			112.6826,
			110.1289,
			107.6712,
			105.3095,
			103.0438,
			100.8741,
			98.8004000000001,
			96.8227,
			94.9410000000002,
			93.1553000000001,
			91.4656000000001,
			89.8719000000001,
			88.3742,
			86.9725000000001,
			85.6668,
			84.4571000000001,
			83.3434,
			82.3257000000001,
			81.404,
			80.5783,
			79.8486000000001,
			79.2149000000001,
			78.6772000000001,
			78.2355000000001,
			77.8898000000001,
			77.6401000000001,
			77.4864,
			77.4287000000001,
			77.467,
			77.6013000000001,
			77.8316,
			78.1579000000001,
			78.5802,
			79.0985000000001,
			79.7128000000001,
			80.4231,
			81.2294000000001,
			82.1317000000001,
			83.1300000000001,
			84.2243000000001,
			85.4146000000001,
			86.7009000000001,
			88.0832000000002,
			89.5615000000001,
			91.1358,
			92.8061000000001,
			94.5724,
			96.4347000000001,
			98.3930000000001,
			100.4473,
			102.5976,
			104.8439,
			107.1862,
			109.6245,
			112.1588,
			114.7891,
			117.5154,
			120.3377,
			123.256,
			126.2703,
			129.3806,
			132.5869,
			135.8892,
			139.2875,
			142.7818,
			146.3721,
			150.0584,
			153.8407,
			157.719,
			161.6933,
			165.7636,
			169.9299,
			174.1922,
			178.5505,
			183.0048,
			187.5551,
			192.2014,
			196.9437,
			201.782,
			206.7163,
			211.7466,
			216.8729,
			222.0952,
			227.4135,
			232.8278,
			238.3381,
			243.9444,
			249.6467,
			255.445,
			261.3393,
			267.3296,
			273.4159,
			279.5982,
			285.8765,
			292.2508,
			298.7211,
			305.2874,
			311.9497,
			318.708,
			325.5623,
			332.5126,
			339.5589,
			346.7012,
			353.9395,
			361.2738,
			368.7041,
			376.2304,
			383.8527,
			391.571,
			399.3853,
			407.2956,
			415.3019,
			423.4042,
			431.6025,
			439.8968,
			448.2871,
			456.7734,
			465.3557,
			474.034,
			482.8083,
			491.6786,
			500.6449,
			509.7072,
			518.8655,
			528.1198,
			537.4701,
			546.9164,
			556.4587,
			566.097,
			575.8313,
			585.6616,
			595.5879,
			605.6102,
			615.7285,
			625.9428,
			636.2531,
			646.6594,
			657.1617,
			667.76,
			678.4543,
			689.2446,
			700.1309,
			711.1132,
			722.1915,
			733.3658,
			744.6361,
			756.0024,
			767.4647,
			779.023,
			790.6773,
			802.4276,
			814.2739,
			826.2162,
			838.2545,
			850.3888,
			862.6191,
			874.9454,
			887.3677,
			899.886,
			912.5003,
			925.2106,
			938.0169,
			950.9192,
			963.9175,
			977.0118,
			990.2021,
			1003.4884,
			1016.8707,
			1030.349,
			1043.9233,
			1057.5936,
			1071.3599,
			1085.2222,
			1099.1805,
			1113.2348,
			1127.3851,
			1141.6314,
			1155.9737,
			1170.412,
			1184.9463,
			1199.5766,
			1214.3029,
			1229.1252,
			1244.0435,
			1259.0578,
			1274.1681,
			1289.3744,
			1304.6767,
			1320.075,
			1335.5693,
			1351.1596,
			1366.8459,
			1382.6282,
			1398.5065
	};

	public final static int kTop = 0;
	public final static int kBottom = 1;
	public final static int kLeft = 2;
	public final static int kRight = 3;
	
	public static final double slowCameraAlignSpeed = 0.3;
	public static final int visionAngleThreshold = 4;
}
