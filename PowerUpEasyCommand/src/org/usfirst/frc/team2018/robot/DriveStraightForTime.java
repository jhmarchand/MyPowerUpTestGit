package org.usfirst.frc.team2018.robot;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class DriveStraightForTime implements ICommand {

	private long _timeToDriveInMilleseconds = 0;
	private DifferentialDrive _differentialDrive = null;
	boolean _firstExecution = true;
	private long _startTimeInMilleseconds = 0;
	
	public DriveStraightForTime(long timeInMilliseconds, DifferentialDrive differentialDrive) 
	{
		_timeToDriveInMilleseconds = timeInMilliseconds;
		_differentialDrive = differentialDrive;
	}

	public void execute() 
	{
		if ( _firstExecution )
		{
			_startTimeInMilleseconds = System.currentTimeMillis();
			_firstExecution = false;
		}
		_differentialDrive.tankDrive(.5, .5);
	}

	public boolean isFinsihed() 
	{
		return System.currentTimeMillis() > _startTimeInMilleseconds + _timeToDriveInMilleseconds;
	}
	
	public void end()
	{
		_differentialDrive.stopMotor();
	}

}
