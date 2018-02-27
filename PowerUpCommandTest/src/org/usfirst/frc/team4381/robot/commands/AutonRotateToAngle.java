package org.usfirst.frc.team4381.robot.commands;

import org.usfirst.frc.team4381.robot.AnglePIDSource;
import org.usfirst.frc.team4381.robot.PIDOutputImpl;
import org.usfirst.frc.team4381.robot.Robot;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutonRotateToAngle extends Command {
	
	double targetAngle;
	
	PIDOutputImpl rotationPIDOutput = new PIDOutputImpl();
	
	PIDController anglePIDController = null;
	
	public AutonRotateToAngle(double angle) {
	
		requires(Robot.driveTrain);
		targetAngle = angle;
	}
	
	// Called just before this Command runs the first time
	protected void initialize() {
		
		anglePIDController = createAnglePIDController();
	
	}
	
	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Robot.driveTrain.tankDrive(rotationPIDOutput.getValue() * -1 , rotationPIDOutput.getValue());
	}
	
	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return anglePIDController.onTarget() && anglePIDController.isEnabled();
	}
	
	// Called once after isFinished returns true
	protected void end() {
		
		anglePIDController.disable();
		Robot.driveTrain.arcadeDrive(0, 0);
	}
	
	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
	

    
    protected PIDController createAnglePIDController(){
    	
    	AnglePIDSource anglePIDSource = new AnglePIDSource();
    	anglePIDSource.setPIDSourceType(PIDSourceType.kDisplacement);
    	
    	PIDController newPIDController = new PIDController(.03, .01, .01 , anglePIDSource, rotationPIDOutput);
    	
    	newPIDController.setInputRange(0, 360);
    	newPIDController.setOutputRange(-1, 1);
    	newPIDController.setSetpoint(targetAngle);
    	newPIDController.setAbsoluteTolerance(0.1);
    	newPIDController.setContinuous();
    	newPIDController.enable();
    	newPIDController.setName("PIDController", "AngleController"); 
    	
    	return newPIDController;
    }
}
