package org.usfirst.frc.team4381.robot.commands;

import org.usfirst.frc.team4381.robot.PIDOutputImpl;
import org.usfirst.frc.team4381.robot.Robot;
import org.usfirst.frc.team4381.robot.TargetPIDSource;

import edu.wpi.first.wpilibj.PIDController;

import edu.wpi.first.wpilibj.command.Command;


/**
 *
 */
public class AutonRotateToTarget extends Command {
	
	 static final double kP = 0.03; 
	 static final double kI = 0.01; 
	 static final double kD = 0.01; 
	 static final double kF = 0.00;
	 

	 PIDOutputImpl turnPIDOutput = new PIDOutputImpl();
	
	private PIDController turnController;


    public AutonRotateToTarget() {

    	requires(Robot.driveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	turnController = new PIDController(kP, kI, kD, new TargetPIDSource(), turnPIDOutput);
    	turnController.setInputRange(0, 640);
    	turnController.setOutputRange(-1, 1);
    	turnController.setSetpoint(320);
    	turnController.setAbsoluteTolerance(5);
    	turnController.enable();
    	turnController.setName("Commands", "TurnController");

    }
    

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.driveTrain.arcadeDrive(0, turnPIDOutput.getValue());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return turnController.onTarget();
//        return turnPIDOutput.getValue() == 0 && turnPIDOutput.getHasValue();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveTrain.arcadeDrive(0, 0);   
    	turnController.disable();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }


}
