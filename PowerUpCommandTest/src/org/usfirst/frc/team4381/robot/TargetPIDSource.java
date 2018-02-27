package org.usfirst.frc.team4381.robot;


import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;

public class TargetPIDSource implements PIDSource {

	NetworkTableEntry blobsEntry = NetworkTableInstance.getDefault().getTable("table4381").getEntry("BLOBS");
	public TargetPIDSource() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void setPIDSourceType(PIDSourceType pidSource) {
		// TODO Auto-generated method stub
	}

	@Override
	public PIDSourceType getPIDSourceType() {
		// TODO Auto-generated method stub
		return PIDSourceType.kDisplacement;
	}

	@Override
	public double pidGet() {
		// TODO Auto-generated method stub
		return blobsEntry.getDoubleArray(new double[] {0.0})[0];
		//return blobs[0];
	}

}
