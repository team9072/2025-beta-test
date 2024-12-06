package frc.robot.constants;

import edu.wpi.first.apriltag.AprilTagFieldLayout;
import edu.wpi.first.apriltag.AprilTagFields;
import edu.wpi.first.math.geometry.Rotation3d;
import edu.wpi.first.math.geometry.Transform3d;
import edu.wpi.first.math.geometry.Translation3d;
import edu.wpi.first.math.util.Units;

public class VisionConstants {
    public static final AprilTagFieldLayout aprilTagLayout = AprilTagFields.k2024Crescendo.loadAprilTagLayoutField();

    public static final Transform3d rearCamOffset = new Transform3d(
        new Translation3d(Units.inchesToMeters(-6), Units.inchesToMeters(-5.5), -Units.inchesToMeters(-11)),
        new Rotation3d(0, Units.degreesToRadians(-35.5), Math.PI));
}
