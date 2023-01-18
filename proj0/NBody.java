

public class NBody {

    public static double readRadius(String img){
        In in = new In(img);
        int num = in.readInt();
        double r = in.readDouble();
        return r;
    }

    public static Planet[] readPlanets(String img){
        In in = new In(img);
        int len = in.readInt();
        double r = in.readDouble();
        Planet[] p = new Planet[len];
        for (int i = 0; i < len; i++){
            double xPos = in.readDouble();
            double yPos = in.readDouble();
            double xVel = in.readDouble();
            double yVel = in.readDouble();
            double m = in.readDouble();
            String  name = in.readString();
            p[i] = new Planet(xPos, yPos, xVel, yVel, m, name);
        }
        return p;
    }

    public static void main(String[] args){
        double T, dt;
        T = Double.parseDouble(args[0]);
        dt = Double.parseDouble(args[1]);
        String fileName = args[2];
        double radius = readRadius(fileName);
        StdDraw.setScale(-radius, radius);
        StdDraw.clear();
        StdDraw.picture(0, 0, "./images/starfield.jpg");
        Planet[] p = readPlanets(fileName);
        for (Planet planet : p){
            planet.draw();
        }
        StdDraw.enableDoubleBuffering();
        while (T > 0){
            T -= dt;
            double[] xForces, yForces;
            int len = p.length;
            xForces = new double[len];
            yForces = new double[len];
            for (int i = 0; i < len; i++){
                xForces[i] = p[i].calcNetForceExertedByX(p);
                yForces[i] = p[i].calcNetForceExertedByY(p);
                p[i].update(dt, xForces[i], yForces[i]);
            }
            StdDraw.clear();
            StdDraw.picture(0, 0, "./images/starfield.jpg");
            for (Planet planet : p){
                planet.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
        }

        StdOut.printf("%d\n", p.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < p.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    p[i].xxPos, p[i].yyPos, p[i].xxVel,
                    p[i].yyVel, p[i].mass, p[i].imgFileName);
        }

    }

}
