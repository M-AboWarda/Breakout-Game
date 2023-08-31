
public class Vec {
	private double x, y;
	public Vec(double x, double y) {
		this.x = x;
		this.y = y;
	}
	public Vec(int x, int y) {
		this.x = (double)x;
		this.y = (double)y;
	}
	public Vec(double angle) {
		this.x = Math.cos(angle);
		this.y = Math.sin(angle);
	}
	public Vec(Vec startV, Vec endV) {
		endV.sub(startV);
		this.x = endV.getX();
		this.y = endV.getY();
	}
	
	public double getX() {
		return x;
	}
	public double getY() {
		return y;
	}
	public void setX(double x) {
		this.x = x;
	}
	public void setY(double y) {
		this.y = y;
	}
	public double getAngle() {
		if(x > 0) {
			return Math.atan(y/x);
		}else if(x < 0) { 
			return Math.PI + Math.atan(y/x);
		}else if(y < 0) {
			return -Math.PI/2;
		}else {
			return Math.PI/2;
		}
	}
	public void setAngle(double ang) {
		double mag = getMag();
		Vec vector = new Vec(ang);
		vector.setMag(mag);
		this.x = vector.getX();
		this.y = vector.getY();
	}
	public double getMag() {
		return Math.sqrt(x * x + y * y);
	}
	public void setMag(int m) {
		double ang = getAngle();
		setX(m * Math.cos(ang));
		setY(m * Math.sin(ang));
	}
	public void setMag(double m) {
		double ang = getAngle();
		setX(m * Math.cos(ang));
		setY(m * Math.sin(ang));
	}
	
	public void add(Vec vector) {
		setX(getX() + vector.getX());
		setY(getY() + vector.getY());
	}
	public void sub(Vec vector) {
		setX(getX() - vector.getX());
		setY(getY() - vector.getY());
	}
	/*
	public Vec normalized() {
		return new Vec(getAngle());
	}*/
}
