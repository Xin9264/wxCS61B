public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    public static final double G = 6.67E-11;
    public Planet(double xP, double yP, double xV, double yV, double m, String img){
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public  Planet(Planet p){
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }

    public double calcDistance(Planet p){
        double distance_2 = Math.pow((this.xxPos - p.xxPos), 2) + Math.pow((this.yyPos - p.yyPos), 2);
        return Math.pow(distance_2, 0.5);
    }

    public double calcForceExertedBy(Planet p){
        return G * p.mass * this.mass / Math.pow(this.calcDistance(p), 2);
    }

    public double calcForceExertedByX(Planet p){
        return this.calcForceExertedBy(p) * (p.xxPos - this.xxPos) / this.calcDistance(p);
    }

    public double calcForceExertedByY(Planet p){
        return this.calcForceExertedBy(p) * (p.yyPos - this.yyPos) / this.calcDistance(p);
    }

    public double calcNetForceExertedByX(Planet[] allPlants){
        int len = allPlants.length;
        int i = 0;
        double NetForceX = 0;
        for (i = 0; i < len; i++){
            if (allPlants[i].equals(this)){
                NetForceX += 0;
            }else {
                NetForceX += this.calcForceExertedByX(allPlants[i]);
            }
        }
        return NetForceX;
    }

    public double calcNetForceExertedByY(Planet[] allPlants){
        int len = allPlants.length;
        int i = 0;
        double NetForceY = 0;
        for (i = 0; i < len; i++){
            if (allPlants[i].equals(this)){
                NetForceY += 0;
            }else {
                NetForceY += this.calcForceExertedByY(allPlants[i]);
            }
        }
        return NetForceY;
    }

    public void update(double dt, double Fx, double Fy){
        double ax = 0;
        double ay = 0;
        ax = Fx / this.mass;
        ay = Fy / this.mass;
        this.xxVel += ax * dt;
        this.yyVel += ay * dt;
        this.xxPos += this.xxVel * dt;
        this.yyPos += this.yyVel * dt;

    }

    public void draw(){
        StdDraw.picture(this.xxPos, this.yyPos, "./images/"+this.imgFileName);
    }
}
