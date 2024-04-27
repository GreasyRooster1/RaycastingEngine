package main.Util.ThreeDimensional;

import static processing.core.PApplet.sqrt;

public class Vec3 {
    public float x,y,z;
    public Vec3(float x, float y, float z){
        this.x = x;
        this.y = y;
        this.z = z;
    }
    public float dot(Vec3 v){
        return x*v.x+y*v.y+z*v.z;
    }

    public Vec3 normal() {
        float mag = mag();
        return new Vec3(x/mag, y/mag, z/mag);
    }

    public float magSqr(){
        return x*x+y*y+z*z;
    }
    public float mag(){
        return sqrt(x*x+y*y+z*z);
    }

    public Vec3 mult(float a) {
        return new Vec3(x*a,y*a,z*a);
    }
}
