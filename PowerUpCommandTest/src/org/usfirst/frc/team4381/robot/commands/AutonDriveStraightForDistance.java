package org.usfirst.frc.team4381.robot.commands;

import org.usfirst.frc.team4381.robot.AnglePIDSource;
import org.usfirst.frc.team4381.robot.DriveTrainEncoderPIDSource;
import org.usfirst.frc.team4381.robot.PIDOutputImpl;
import org.usfirst.frc.team4381.robot.Robot;
import org.usfirst.frc.team4381.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutonDriveStraightForDistance extends Command {
	
	double distanceToDrive;
	
	PIDOutputImpl rotationPIDOutput = new PIDOutputImpl();
	PIDOutputImpl speedPIDOutput = new PIDOutputImpl();
	
	PIDController anglePIDController = null;
	PIDController encoderPIDController = null;
	
	public AutonDriveStraightForDistance(double distanceToDriveinInches) {
	
		requires(Robot.driveTrain);
		distanceToDrive = distanceToDriveinInches;
	}
	
	// Called just before this Command runs the first time
	protected void initialize() {
		
		anglePIDController = createAnglePIDController();
		encoderPIDController = createEncoderPIDController();
	
	}
	
	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Robot.driveTrain.arcadeDrive(speedPIDOutput.getValue(), rotationPIDOutput.getValue());
	}
	
	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return encoderPIDController.onTarget() && encoderPIDController.isEnabled();
	}
	
	// Called once after isFinished returns true
	protected void end() {
		encoderPIDController.disable();
		anglePIDController.disable();
		Robot.driveTrain.arcadeDrive(0, 0);
	}
	
	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
	
	protected PIDController createEncoderPIDController(){
		
	   	PIDController newPIDController = new PIDController(.03, .01, .01 , new DriveTrainEncoderPIDSource(), speedPIDOutput);
	   	
	   	newPIDController.setOutputRange(-1, 1);
	   	newPIDController.setSetpoint(Robot.driveTrain.getAverageEncoderPosition() + DriveTrain.ConvertInchestoEncoderCount(distanceToDrive));
	   	newPIDController.setAbsoluteTolerance(100);
	   	newPIDController.enable();
	   	newPIDController.setName("PIDController", "EncoderController"); 
		   	
		return newPIDController;
   	}    
    
    protected PIDController createAnglePIDController(){
    	
    	AnglePIDSource anglePIDSource = new AnglePIDSource();
    	anglePIDSource.setPIDSourceType(PIDSourceType.kRate);
    	
    	PIDController newPIDController = new PIDController(.03, .01, .01 , anglePIDSource, rotationPIDOutput);
    	
    	newPIDController.setInputRange(0, 360);
    	newPIDController.setOutputRange(-1, 1);
    	newPIDController.setSetpoint(Robot.driveTrain.getHeading());
    	newPIDController.setAbsoluteTolerance(0.01);
    	newPIDController.setContinuous();
    	newPIDController.enable();
    	newPIDController.setName("PIDController", "AngleController"); 
    	
    	return newPIDController;
    }
}
