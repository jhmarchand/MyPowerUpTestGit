package org.usfirst.frc.team4381.robot;

import edu.wpi.first.wpilibj.PIDOutput;

public class PIDOutputImpl implements PIDOutput {

	private double value = 0;
//	private boolean hasValue = false;
	
	public double getValue() {
		return value;
	}
	
/*	public boolean getHasValue() {
		return hasValue;
	}
	*/
	
	@Override
	public void pidWrite(double output) {
		// TODO Auto-generated method stub
		value = output;
//		hasValue = true;
	}

}
