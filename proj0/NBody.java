
public class NBody {
  /*
   * read the radius of universe
   */
  public static double readRadius(String filename) {
    if (filename.equals(0)) {
      System.out.println("Please supply a file as a command line argument.");
    }
    In in = new In(filename);
    in.readInt();
    double radius = in.readDouble();
    in.close();
    return radius;
  }
  public static int numb(String filename) {
    In in = new In(filename);
    return in.readInt();
  }


  public static Body[] readBodies(String filename) {
    if (filename.equals(0)) {
      System.out.println ("Please supply a file as a command line argument.");
    }
    In in = new In(filename);
    int nbPlanet = in.readInt();
    double radius = in.readDouble();
    Body[] planet = new Body[nbPlanet];
    for (int i = 0; i < nbPlanet; i +=1) {
      double xp = in.readDouble();
      double yp = in.readDouble();
      double vx = in.readDouble();
      double vy = in.readDouble();
      double m = in.readDouble();
      String img = in.readString();
      planet[i] = new Body(xp, yp, vx, vy, m, img);
    }
    return planet;
  }

  public static void main(String[] args) {
    double T = Double.parseDouble(args[0]);
    double dt = Double.parseDouble(args[1]);
    String filename = args[2];
    double r = readRadius(filename);
    Body[] planets = readBodies(filename);
    int nub = numb(filename);


    String imageToDraw = "images/starfield.jpg";
    // set the universe scale
    StdDraw.setXscale(-r, r);
    StdDraw.setYscale(-r, r);
    StdDraw.enableDoubleBuffering();

    int t = 0;
    while(t <= T) {

      double[] xForce = new double[nub];
      double[] yForce = new double[nub];

      for(int i = 0; i < nub; i += 1) {
        xForce[i] = planets[i].calcNetForceExertedByX(planets);
        yForce[i] = planets[i].calcNetForceExertedByY(planets);
      }
      for (int i = 0; i < nub; i += 1) {
        planets[i].update(dt, xForce[i], yForce[i]);
      }

      //background
      StdDraw.picture(0, 0, imageToDraw);

      for(Body b : planets) {
        b.draw();
      }

      StdDraw.show();
      StdDraw.pause(10);

      t += dt;
    }

    StdOut.printf("%d\n", nub);
    StdOut.printf("%.2e\n", r);
    for (int i = 0; i < nub; i++) {
      StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
      planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
      planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
    }
  }

}
