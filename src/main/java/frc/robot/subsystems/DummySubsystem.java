package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;

public class DummySubsystem extends SubsystemBase {
    public enum DummyState {
        Off,
        Slow,
        Fast
    }

    private DummyState state = DummyState.Off;
    private TalonFX motor;

    public DummySubsystem() {
        // On Citrus, this is the amp rollers motor
        motor = new TalonFX(18, "canivore1");
    }

    public Command setState(DummyState _state) {
        return runOnce(() -> {
            state = _state;
        });
    }

    @Override
    public void periodic() {
        switch(state) {
            case Off:
                motor.set(0);
                break;
            case Slow:
                motor.set(0.1);
                break;
            case Fast:
                motor.set(0.3);
                break;
        }
    }
}
