public class NBody{
  
	public static double readRadius(String path) {
		In in = new In(path);
		int num = in.readInt();
		double radius = in.readDouble();
		return radius;	
  }

  public static Planet[] readPlanets(String path){
		In in = new In(path);
		int num = in.readInt();
		double radius = in.readDouble();
		Planet[] p = new Planet[num];
		for (int i = 0; i < num; i++){
			double xxPos = in.readDouble();
			double yyPos = in.readDouble();
			double xxVel = in.readDouble();
			double yyVel = in.readDouble();
			double mass = in.readDouble();
			String imgFileName = in.readString();
			p[i] = new Planet(xxPos, yyPos, xxVel, yyVel, mass, imgFileName);
		}
		return p;
  } 

  public static void main(String[] args){
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];
		double r = readRadius(filename);
		Planet[] planets = readPlanets(filename);

		//StdDraw.setScale(-r,r);
		StdDraw.setXscale(-r, r);
		StdDraw.setYscale(-r, r);
		StdDraw.enableDoubleBuffering();
		
		double t = 0;
		while(t <= T) {
			Double[] xForce = new Double[planets.length];
			Double[] yForce = new Double[planets.length];

			for(int i = 0; i < planets.length; i++) {
				xForce[i] = planets[i].calcNetForceExertedByX(planets);
				yForce[i] = planets[i].calcNetForceExertedByY(planets);
			}
			//update
			for(int i = 0; i < planets.length; i++) {
				planets[i].update(dt, xForce[i],yForce[i]);
			}
			//backgroud picture
			StdDraw.picture(0, 0, "images/starfield.jpg");
		  //draw all planets
			for (Planet p : planets) {
				p.draw();
			}

			StdDraw.show();
			StdDraw.pause(10);
			t += dt;
		}

		StdOut.printf("%d\n", planets.length);
		StdOut.printf("%.2e\n", radius);
		for (int i = 0; i < planets.length; i++) {
    	StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                  planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                  planets[i].yyVel, planets[i].mass, planets[i].imgFileName);   
		}
  }
}
