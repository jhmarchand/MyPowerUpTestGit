package org.usfirst.frc.team2018.robot;

public interface ICommand {
	void execute();
	boolean isFinsihed();
	void end();
}
