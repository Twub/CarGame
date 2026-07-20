package GameObjects;

import FileUtilities.ImagePaths;
import FileUtilities.ImageUtility;

import java.awt.*;

public abstract class Particles {

    private int width;
    private int height;
    private int xPosition;
    private int yPosition;
    private int speed;
    private int damage;
    private int diameter;
    private Color particleColor;
    private double radius;
    private Image particleImage;

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getX() {
        return xPosition;
    }

    public int getY() {
        return yPosition;
    }

    public int getSpeed() {
        return speed;
    }

    public int getDamage() {
        return damage;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setX(int xPosition) {
        this.xPosition = xPosition;
    }

    public void setY(int yPosition) {
        this.yPosition = yPosition;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void setDiameter(int diameter){
        this.diameter = diameter;
    }

    public int getDiameter(){
        return diameter;
    }

    public Color generateRandomColor(){
        int red = (int) (Math.random()*255);
        int green = (int) (Math.random()*255);
        int blue = (int) (Math.random()*255);

        return new Color(red, green, blue);
    }

    public Color getParticleColor() {
        return particleColor;
    }

    public void setParticleColor(Color particleColor) {
        this.particleColor = particleColor;
    }

    public void updatePosition(){
        int updatedPosition = getY() - getSpeed();
        setY(updatedPosition);
    }

    public double getRadius(){
        return radius;
    }

    public void setRadius(double radius){
        this.radius = radius;
    }

    public boolean collidesWith(ObsticleParticle other) {
        double dx = xPosition - other.getX();
        double dy = yPosition - other.getY();

        double distanceSquared = dx * dx + dy * dy;
        double radiusSum = radius + other.getRadius();

        return distanceSquared <= radiusSum * radiusSum;
    }

    public Image getParticleImage() {
        return particleImage;
    }

    public void setParticleImage(ImagePaths imagePaths) {
        particleImage = ImageUtility.loadImage(imagePaths);
    }

    public void setParticleImage(ImagePaths imagePaths, int width, int height) {
        particleImage = ImageUtility.resizeLoadImage(imagePaths, width, height);
    }
}
