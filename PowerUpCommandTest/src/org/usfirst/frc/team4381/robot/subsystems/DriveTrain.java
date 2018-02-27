package org.usfirst.frc.team4381.robot.subsystems;

import org.usfirst.frc.team4381.robot.commands.TeleOpDrive;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 *
 */
public class DriveTrain extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	private WPI_TalonSRX leftFrontTalon = new WPI_TalonSRX(1);
	private WPI_TalonSRX rightFrontTalon = new WPI_TalonSRX(2);
	private WPI_TalonSRX leftBackTalon = new WPI_TalonSRX(3);
	private WPI_TalonSRX rightBackTalon = new WPI_TalonSRX(4);
	
	private SpeedControllerGroup leftSpeedControlerGroup = new SpeedControllerGroup(leftFrontTalon, leftBackTalon);
	private SpeedControllerGroup rightSpeedControlerGroup = new SpeedControllerGroup(rightFrontTalon, rightBackTalon);
	
	private DifferentialDrive differentialDrive = new DifferentialDrive(leftSpeedControlerGroup, rightSpeedControlerGroup);
	
	private ADXRS450_Gyro gyro = new ADXRS450_Gyro(SPI.Port.kOnboardCS0);
	
	public DriveTrain() {
		super("DriveTrain");
		
		leftBackTalon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10);
		rightBackTalon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10);
		
		zeroHeading();
		resetEncoders();
	}
	
    public void initDefaultCommand() {

    	setDefaultCommand(new TeleOpDrive());
    }
    
    public void arcadeDrive(double speed, double rotation)
    {
    	differentialDrive.arcadeDrive(speed, rotation);
    }
    
    public void tankDrive(double leftSpeed, double rightSpeed)
    {
    	differentialDrive.tankDrive(leftSpeed, rightSpeed);
    }
    
    public void resetEncoders()
    {
    	leftBackTalon.setSelectedSensorPosition(0, 0, 0);    	
    	rightBackTalon.setSelectedSensorPosition(0, 0, 0);    	
    	
    }
    
    public double getAverageEncoderPosition()
    {
    	// probably not an issue but in reality, this needs to be corrected so that reverse from the start is possible.
    	return ( Math.abs(leftBackTalon.getSelectedSensorPosition(0)) +  Math.abs(rightBackTalon.getSelectedSensorPosition(0))) / 2;
    }
    
    public double getHeading()
    {
    	double angle = gyro.getAngle() % 360;
    	return angle < 0 ? 360 + angle : angle;
    }
    
    public void zeroHeading()
    {
    	gyro.reset();;
    }
    
    public static double ConvertInchestoEncoderCount(double inches)
    {
    	return inches * 508;
    }
    
    public void enableCoastMode(boolean enable)
    {
    	NeutralMode neutralMode = NeutralMode.Brake;
     	if ( enable )
    		neutralMode = NeutralMode.Coast;
    	
    	rightFrontTalon.setNeutralMode(neutralMode);
    	rightBackTalon.setNeutralMode(neutralMode);
    	leftFrontTalon.setNeutralMode(neutralMode);
    	leftBackTalon.setNeutralMode(neutralMode);
    }
    
}

