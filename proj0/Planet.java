public class Planet {
  public double xxPos;
  public double yyPos;
  public double xxVel;
  public double yyVel;
  public double mass;
  public String imgFileName;
  private static final double G = 6.67e-11;

  public Planet(double xP, double yP, double xV, double yV, double m, String img){
    imgFileName = img;
    mass = m;
    xxPos = xP;
    yyPos = yP;
    xxVel = xV;
    yyVel = yV;
  }

  public Planet(Planet p){
    xxPos = p.xxPos;
    yyPos = p.yyPos;
    xxVel = p.xxVel;
    yyVel = p.yyVel;
    mass = p.mass;
    imgFileName = p.imgFileName;
  }

  public double calcDistance(Planet p){
    return Math.sqrt((p.xxPos-xxPos)*(p.xxPos-xxPos) + (p.yyPos-yyPos)*(p.yyPos-yyPos));
  }

  public double calcForceExertedBy(Planet p){
    double r = calcDistance(p);
    return G * p.mass * mass / (r * r);
  }

  public double calcForceExertedByX(Planet p){
    return calcForceExertedBy(p) * (p.xxPos - xxPos) / (calcDistance(p));
  }

  public double calcForceExertedByY(Planet p) {
    return calcForceExertedBy(p) * (p.yyPos - yyPos) / (calcDistance(p));
  }

  public double calcNetForceExertedByX(Planet[] allPlanets){
    double total = 0;
    for(Planet p : allPlanets){
      if (this.equals(p) == false){
        total += calcForceExertedByX(p);
      }
    }
    return total;
  }

  public double calcNetForceExertedByY(Planet[] allPlanets) {
    double total = 0;
    for (Planet p : allPlanets) {
      if (this.equals(p) == false) {
        total += calcForceExertedByY(p);
      }
    }
    return total;
  }
 
  public void update(double dt, double fX, double fY){
    double ax = fX / mass;
    double ay = fY / mass;
    xxVel += ax * dt;
    yyVel += ay * dt;
    xxPos += xxVel * dt;
    yyPos += yyVel * dt;
  }  
  
  public void draw(){
    StdDraw.picture(xxPos, yyPos, "images/" + imgFileName);
  }  

}

