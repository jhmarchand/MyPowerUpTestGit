package org.usfirst.frc.team4381.robot;

import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;

public class LiftEncoderPIDSource implements PIDSource {

	PIDSourceType pidSourceType = PIDSourceType.kDisplacement;
	
	public LiftEncoderPIDSource() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void setPIDSourceType(PIDSourceType pidSource) {
		// TODO Auto-generated method stub
		pidSourceType = pidSource;

	}

	@Override
	public PIDSourceType getPIDSourceType() {
		// TODO Auto-generated method stub
		return pidSourceType;
	}

	@Override
	public double pidGet() {
		return Robot.lift.getEncoderPosition();
	}

}
