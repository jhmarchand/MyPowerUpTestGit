package org.usfirst.frc.team4381.robot.commands;


import org.usfirst.frc.team4381.robot.Robot;

/**
 *
 */
public class AutonRotateByAngle extends AutonRotateToAngle {
	

	public AutonRotateByAngle(double angle) {
		super(( Robot.driveTrain.getHeading() + angle) % 360);

	}
}
