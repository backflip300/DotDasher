package gui;

public abstract class Object {

	protected double x;
	protected double y;
	protected double radius;
	protected double velocity;
	protected double direction;

	public Object(double x, double y, double radius) {
		this.x = x;
		this.y = y;
		this.radius = radius;
		direction = 0;
	}
	public double getX() {
		return x;
	}
	public double getY() {
		return y;
	}
	public double getRadius() {
		return radius;
	}
}
