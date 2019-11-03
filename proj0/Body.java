class Body{
    public double xxPos, yyPos, xxVel, yyVel, mass;
    public String imgFileName;
    static final double GRAVITY = 6.67e-11;

    public Body(double xP, double yP, double xV,
                double yV, double m, String img){
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }
    public Body(Body b){
        xxPos = b.xxPos;
        yyPos = b.yyPos;
        xxVel = b.xxVel;
        yyVel = b.yyVel;
        mass = b.mass;
        imgFileName = b.imgFileName;
    }
    public double calcDistance(Body b){
        return Math.sqrt(Math.pow((this.xxPos - b.xxPos), 2) + Math.pow((this.yyPos - b.yyPos),2));
    }

    public double calcForceExertedBy(Body b){
        return (GRAVITY * this.mass * b.mass) / Math.pow(calcDistance(b),2);
    }

    public double calcForceExertedByX(Body b){
        return (calcForceExertedBy(b) * (b.xxPos - this.xxPos)) / calcDistance(b);
    }

    public double calcForceExertedByY(Body b){
        return (calcForceExertedBy(b) * (b.yyPos - this.yyPos)) / calcDistance(b);
    }

    public double calcNetForceExertedByX(Body[] bodies) {
        double totalForce = 0;
        for (Body b : bodies){
            if (b.equals(this)) { continue; }
                totalForce += calcForceExertedByX(b);
        }
        return totalForce;
    }

    public double calcNetForceExertedByY(Body[] bodies) {
        double totalForce = 0;
        for (Body b : bodies){
            if (b.equals(this)) { continue; }
                totalForce += calcForceExertedByY(b);
        }
        return totalForce;
    }
    public void update(double td, double fx, double fy){
        double aX = fx / this.mass;
        double aY = fy / this.mass;

        // new velocities
        xxVel += td * aX;
        yyVel += td * aY;

        // new position
        xxPos += td * xxVel;
        yyPos += td * yyVel;
    }
    public void draw(double scale){
        // minor hack to centre all the planets on the renderer. 
        // They all rotate around the origin (bottom-left corner of screen) otherwise.
        // Not sure if that's what is intended.
        StdDraw.picture(xxPos + (scale * 0.5), yyPos + (scale * 0.5), "/images/" + imgFileName);
    }
}