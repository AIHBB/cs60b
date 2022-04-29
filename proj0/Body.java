
public class Body {
  public static double G = 6.67e-11;
  public double xxPos;
  public double yyPos;
  public double xxVel;
  public double yyVel;
  public double mass;
  public String imgFileName;

  public Body(double xP, double yP, double xV, double yV, double m, String img) {

      xxPos = xP;
      yyPos = yP;
      xxVel = xV;
      yyVel = yV;
      mass = m;
      imgFileName = img;

    }
  public Body(Body b) {

    this.xxPos = b.xxPos;
    this.yyPos = b.yyPos;
    this.xxVel = b.xxVel;
    this.yyVel = b.yyVel;
    this.mass = b.mass;
    this.imgFileName = b.imgFileName;
  }

  public double calcDistance(Body b) {

    double disX = Math.pow(this.xxPos - b.xxPos, 2);
    double disY = Math.pow(this.yyPos - b.yyPos, 2);
    return Math.sqrt(disX + disY);
  }

  public double calcForceExertedBy(Body b) {

    return (G * this.mass * b.mass) / Math.pow(this.calcDistance(b), 2);

  }

  public double calcForceExertedByX(Body b) {

    return (calcForceExertedBy(b) * (b.xxPos - this.xxPos)) / calcDistance(b);

  }

  public double calcForceExertedByY(Body b) {

    return (calcForceExertedBy(b) * (b.yyPos - this.yyPos)) / calcDistance(b);

  }

  public double calcNetForceExertedByX(Body[] s) {
    double dis = 0;
    for (int i = 0; i < s.length; i += 1) {
      if ( this.equals(s[i])) {
        continue;
      }
      dis += this.calcForceExertedByX(s[i]);
    }

    return dis;
  }


  public double calcNetForceExertedByY(Body[] s) {
    double dis = 0;
    for (int i = 0; i < s.length; i += 1) {

      if ( this.equals(s[i])) {
        continue;
      }
      dis += this.calcForceExertedByY(s[i]);
    }

    return dis;
  }
  public void update(double dt, double fX, double fY) {
    xxVel += dt * (fX / mass);
    yyVel += dt * (fY / mass);
    xxPos += dt * xxVel;
    yyPos += dt * yyVel;
  }

  public void draw() {
    StdDraw.picture(xxPos, yyPos, "images/" + imgFileName);
  }
}
