package frc.robot.auto;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;

public class AutoCommands {
    public static Command waitForTagCommand() {
        return Commands.waitSeconds(1);
    }
}
