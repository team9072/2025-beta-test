package frc.robot.vision;

import java.util.ArrayList;

import org.photonvision.PhotonCamera;
import org.photonvision.PhotonPoseEstimator;
import org.photonvision.PhotonPoseEstimator.PoseStrategy;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Transform3d;
import edu.wpi.first.wpilibj.smartdashboard.Field2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.constants.VisionConstants;
import frc.robot.subsystems.CommandSwerveDrivetrain;

public class PhotonVision {
    private CommandSwerveDrivetrain m_drivetrain;
    private final Field2d m_field = new Field2d();
    private ArrayList<PhotonCamera> m_cameras = new ArrayList<PhotonCamera>();
    private ArrayList<PhotonPoseEstimator> m_poseEstimators = new ArrayList<PhotonPoseEstimator>();

    public PhotonVision(CommandSwerveDrivetrain drivetrain) {
        addCamera("BW1", VisionConstants.rearCamOffset);
        m_drivetrain = drivetrain;
        SmartDashboard.putData("RobotPose", m_field);
    }

    public void addCamera(String name, Transform3d offset) {        
        m_cameras.add(new PhotonCamera(name));
        m_poseEstimators.add(new PhotonPoseEstimator(VisionConstants.aprilTagLayout,
        PoseStrategy.MULTI_TAG_PNP_ON_COPROCESSOR, offset));
    }

    public void update() {
        if (m_poseEstimators.size() == 0) {
            return;
        }

        // TODO: use multiple estimators
        var poseEstimator = m_poseEstimators.get(0);
        var pose = poseEstimator.update(m_cameras.get(0).getLatestResult());

        if (pose.isPresent()) {
            System.out.println("pose");
            Pose2d estimatedPose = pose.get().estimatedPose.toPose2d();
            double timestamp = pose.get().timestampSeconds;

            m_drivetrain.addVisionMeasurement(estimatedPose, timestamp);
            m_field.setRobotPose(estimatedPose);
        } else {
            if (!m_cameras.get(0).isConnected()) {
                System.out.println("disconnected");
                return;
            }

            System.out.println("notag");
            m_field.setRobotPose(new Pose2d());
        }
    }
}
