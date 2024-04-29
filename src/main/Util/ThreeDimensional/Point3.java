package main.Util.ThreeDimensional;

public class Point3 {
    public float x,y,z;
    public Point3(float x, float y, float z){
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vec3 sub(Point3 origin) {
        return new Vec3(x - origin.x, y - origin.y,z - origin.z);
    }
}
