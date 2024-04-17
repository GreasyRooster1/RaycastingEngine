import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static processing.core.PApplet.dist;

public class Ray {
    public float x1,y1;
    public float dir,mag;

    public Ray(float _x1, float _y1, float _dir, float _mag){
        x1=_x1;
        y1=_y1;
        dir=_dir;
        mag=_mag;
    }

    public void update(){
        checkCollision();
        draw();
    }

    public void draw(){
        Main.app.stroke(255,255,0);
        Main.app.strokeWeight(1);
        Main.app.line(x1,y1, (float) (x1+cos(dir)*mag), (float) (y1+sin(dir)*mag));
    }
    public void checkCollision(){
        float closestCollisionDistance=Main.maxViewDistance;
        for(Wall wall: Main.app.walls) {
            CollisionResult result = Util.lineLine(x1, y1, (float) (x1 + cos(dir) * closestCollisionDistance), (float) (y1 + sin(dir) * closestCollisionDistance), wall.x1,wall.y1,wall.x2,wall.y2);
            if(result.collided){
                mag = dist(x1,y1,result.intersectionX,result.intersectionY);
                closestCollisionDistance=mag;
            }
        }
        mag = closestCollisionDistance;
    }
}
