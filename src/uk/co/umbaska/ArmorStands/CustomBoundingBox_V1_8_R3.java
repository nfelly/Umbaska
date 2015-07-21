package uk.co.umbaska.ArmorStands;


import net.minecraft.server.v1_8_R3.AxisAlignedBB;
import net.minecraft.server.v1_8_R3.MovingObjectPosition;
import net.minecraft.server.v1_8_R3.Vec3D;

public class CustomBoundingBox_V1_8_R3 extends AxisAlignedBB {
    public CustomBoundingBox_V1_8_R3(Double d1, Double d2, Double d3, Double d4, Double d5, Double d6) {
        super(d1, d2, d3, d4, d5, d6);
    }

    public double a() {
        return 0.0D;
    }

    public double a(AxisAlignedBB arg0, double arg1) {
        return 0.0D;
    }
    public AxisAlignedBB a(AxisAlignedBB arg0) {
        return this;
    }

    public AxisAlignedBB a(double arg0, double arg1, double arg2) {
        return this;
    }

    public MovingObjectPosition a(Vec3D arg0, Vec3D arg1) {
        return super.a(arg0, arg1);
    }

    public boolean a(Vec3D arg0) {
        return false;
    }

    public double b(AxisAlignedBB arg0, double arg1){
        return 0.0D;
    }

    public boolean b(AxisAlignedBB arg0) {
        return false;
    }

    public double c(AxisAlignedBB arg0, double arg1) {
        return 0.0D;
    }

    public AxisAlignedBB c(double arg0, double arg1, double arg2) {
        return this;
    }

    public AxisAlignedBB grow(double arg0, double arg1, double arg2) {
        return this;
    }

    public AxisAlignedBB shrink(double arg0, double arg1, double arg2) {
        return this;
    }
}