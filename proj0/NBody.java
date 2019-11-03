class NBody {
    static double readRadius(String file){
        In in = new In(file);
        in.readInt();
		return in.readDouble();
    } 
    static Body[] readBodies(String file){
        In in = new In(file);
        int planetAmount = in.readInt();

        in.readDouble(); // skips to the next line
        Body[] bodies = new Body[planetAmount];

        for (int i = 0; i < planetAmount; i++){
            double posx = in.readDouble();
            double posy = in.readDouble();
            double velx = in.readDouble();
            double vely = in.readDouble();
            double mass = in.readDouble();
            String img =  in.readString();
            bodies[i] = new Body(posx, posy, velx, vely, mass, img);
        }
        return bodies;
    }

    public static void main(String[] args){
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double time = 0;
        int renderSpeed = 5;

        double scale = readRadius(filename);
        Body[] bodies = readBodies(filename);

        StdDraw.enableDoubleBuffering();
        StdDraw.setScale(0, scale);

        while (time < T){
            // draw bg
            StdDraw.picture(0.5 * scale, 0.5 * scale, "/images/starfield.jpg");
            // calc forces
            double[] xForces = new double[bodies.length];
            double[] yForces = new double[bodies.length];

            StdDraw.setScale(0, scale);

            for (int i = 0; i < bodies.length; i++){
                xForces[i] = bodies[i].calcNetForceExertedByX(bodies);
                yForces[i] = bodies[i].calcNetForceExertedByY(bodies);
            }
            for (int i = 0; i < bodies.length; i++){
                bodies[i].update(dt, xForces[i], yForces[i]);
                bodies[i].draw(scale);
            }
            StdDraw.pause(renderSpeed);
            StdDraw.show();
            time += dt;
        }
    }
}