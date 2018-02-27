package org.usfirst.frc.team2018.robot;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class RotateForTime implements ICommand {

	private long _timeToDriveInMilleseconds = 0;
	private DifferentialDrive _differentialDrive = null;
	boolean _firstExecution = true;
	private long _startTimeInMilleseconds = 0;
	private double _leftSpeed = .5;
	private double _rightSpeed = -.5;
	
	public RotateForTime(long timeInMilliseconds, boolean rotateClockwise, DifferentialDrive differentialDrive) 
	{
		_timeToDriveInMilleseconds = timeInMilliseconds;
		_differentialDrive = differentialDrive;
		
		if (!rotateClockwise)
		{
			_leftSpeed = -.5;
			_rightSpeed = .5;
		}
	}

	public void execute() 
	{
		if ( _firstExecution )
		{
			_startTimeInMilleseconds = System.currentTimeMillis();
			_firstExecution = false;
			
		}
		
		_differentialDrive.tankDrive(_leftSpeed, _rightSpeed);
	}

	public boolean isFinsihed() {
		
		return System.currentTimeMillis() > _startTimeInMilleseconds + _timeToDriveInMilleseconds;
	}
	
	public void end()
	{
		_differentialDrive.stopMotor();
	}

}
