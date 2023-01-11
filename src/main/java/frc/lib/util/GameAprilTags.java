package frc.lib.util;

import edu.wpi.first.math.geometry.Pose2d;

import edu.wpi.first.math.geometry.Rotation2d;
public class GameAprilTags {
    Pose2d[] targets = new Pose2d[8];

    public GameAprilTags () {
        targets[0] = new Pose2d(15.38, 1.204, new Rotation2d());
        targets[1] = new Pose2d(15.38, 2.601, new Rotation2d());
        targets[2] = new Pose2d(15.38, 3.99, new Rotation2d());
        targets[3] = new Pose2d();
        targets[4] = new Pose2d();
        targets[5] = new Pose2d(1.03, 1.204, new Rotation2d(Math.PI));
        targets[6] = new Pose2d(1.03, 2.601, new Rotation2d(Math.PI));
        targets[7] = new Pose2d(1.03, 3.99, new Rotation2d(Math.PI));
    }

    public Pose2d getPose(int id){
        return targets[id - 1];
    }
}
