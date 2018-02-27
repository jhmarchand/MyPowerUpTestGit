package org.usfirst.frc.team4381.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonDriveASquare extends CommandGroup {

    public AutonDriveASquare(double squareSizeinInches) {
    	
    	addSequential(new AutonDriveStraightForDistance(squareSizeinInches));
    	addSequential(new AutonRotateByAngle(90));
    	addSequential(new AutonDriveStraightForDistance(squareSizeinInches));
    	addSequential(new AutonRotateByAngle(90));
    	addSequential(new AutonDriveStraightForDistance(squareSizeinInches));
    	addSequential(new AutonRotateByAngle(90));
    	addSequential(new AutonDriveStraightForDistance(squareSizeinInches));
    	addSequential(new AutonRotateByAngle(90));
    }
}
