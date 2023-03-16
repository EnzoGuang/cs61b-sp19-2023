public class Body {
    public double xxPos; //Its current x position
    public double yyPos; //Its current y position
    public double xxVel; //Its current velocity in the x direction
    public double yyVel; //Its current velocity in the y direction
    public double mass; //Its mass
    public String imgFileName;
    public static final double G = 6.67e-11; //constant G Force

    public Body(double xP, double yP, double xV, double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    /** Take in a Body object and copy all it's value to a new Body */
    public Body(Body b) {
        xxPos = b.xxPos;
        yyPos = b.yyPos;
        xxVel = b.xxVel;
        yyVel = b.yyVel;
        mass = b.mass;
        imgFileName = b.imgFileName;
    }

    /** Return a double equal to the distance between the supplied Body and this Body*/
    public double calcDistance(Body compareBody) {
        double disXX = xxPos - compareBody.xxPos;
        double disYY = yyPos - compareBody.yyPos;
        return Math.sqrt(Math.pow(disXX, 2) + Math.pow(disYY, 2));
    }

    /** Return the force exerted on this Body by the given Body */
    public double calcForceExertedBy(Body compareBody) {
        double r = this.calcDistance(compareBody);
        double f = G * this.mass * compareBody.mass / Math.pow(r, 2);
        return f;
    }

    /** Return the force exerted in X direction */
    public double calcForceExertedByX(Body compareBody) {
        double f = calcForceExertedBy(compareBody);
        double r = calcDistance(compareBody);
        double dx = compareBody.xxPos - this.xxPos;
        double fx = f * dx * 1.0 / r;
        return fx;
    }

    /** Return the force exerted in Y direction */
    public double calcForceExertedByY(Body compareBody) {
        double f = calcForceExertedBy(compareBody);
        double r = calcDistance((compareBody));
        double dy = compareBody.yyPos - this.yyPos;
        double fy = f * dy * 1.0 / r;
        return fy;
    }

    /* Takes in an array of Bodys and calculate the net X force exerted by
     * all Bodies in that array on this Body.
     */
    public double calcNetForceExertedByX(Body[] compareBody) {
        double sumNetForceByX = 0;
        for (Body temp: compareBody) {
            if (temp.equals(this)) {
                continue;
            }
            sumNetForceByX += this.calcForceExertedByX(temp);
        }
        return sumNetForceByX;
    }

    /** Return the Y force exerted by all bodies in that array on this Body */
    public double calcNetForceExertedByY(Body[] compareBody) {
        double sumNetForceByY = 0;
        for (Body temp: compareBody) {
            if (temp.equals(this)) {
                continue;
            }
            sumNetForceByY += this.calcForceExertedByY(temp);
        }
        return sumNetForceByY;
    }

    public void update(double dt, double fNetX, double fNetY) {
        double aNetX = fNetX / mass;
        double aNetY = fNetY / mass;
        xxVel = xxVel + dt * aNetX;
        yyVel = yyVel + dt * aNetY;
        xxPos = xxPos + dt * xxVel;
        yyPos = yyPos + dt * yyVel;
    }

    /** Draw a Body at appropriate position */
    public void draw() {
        String imageToDraw = "./images/" + imgFileName;
        StdDraw.picture(xxPos, yyPos, imageToDraw);
        //System.out.println("print " + imageToDraw);
    }
}
