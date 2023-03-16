public class NBody {
    /** Return  radius from file "fileName" */
    public static double readRadius(String fileName) {
        In file = new In(fileName);
        int n = file.readInt();
        double radius = file.readDouble();
        return radius;
    }

    /** Return an array of Body read from file "fileName" */
    public static Body[] readBodies(String fileName) {
        In file = new In(fileName);
        int n = file.readInt();
        Body[] bodies = new Body[n];
        double radius = file.readDouble();
        for (int i = 0; i < n; i++) {
            double xxPos = file.readDouble();
            double yyPos = file.readDouble();
            double xxVel = file.readDouble();
            double yyVel = file.readDouble();
            double mass = file.readDouble();
            String imgFileName = file.readString();
            bodies[i] = new Body(xxPos, yyPos, xxVel, yyVel, mass, imgFileName);
        }
        return bodies;
    }

    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        Body[] bodies = readBodies(filename);
        String backGroundImage = "images/starfield.jpg";
        double radius = readRadius(filename);

        StdDraw.setScale(-radius, radius);
        StdDraw.enableDoubleBuffering();

        double time = 0;
        for (; time <= T; time += dt) {
            double[] xForces = new double[bodies.length];
            double[] yForces = new double[bodies.length];
            for (int i = 0; i < bodies.length; i++) {
                xForces[i] = bodies[i].calcNetForceExertedByX(bodies);
                yForces[i] = bodies[i].calcNetForceExertedByY(bodies);
            }
            for (int i = 0; i < bodies.length; i++) {
                bodies[i].update(dt, xForces[i], yForces[i]);
            }
            StdDraw.picture(0, 0, "./images/starfield.jpg");
            for (int i = 0; i < bodies.length; i++) {
                bodies[i].draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
        }

    }

}
