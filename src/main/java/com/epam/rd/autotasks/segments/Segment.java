package com.epam.training.student_veronika_tarasova.segments.src.main.java.com.epam.rd.autotasks.segments;

 public class Segment {

    Point start;
    Point end;

    public Segment(Point start, Point end) throws IllegalArgumentException{

        if (start == null && end == null) {
            throw new IllegalArgumentException();
        }
        if (start.getX() == end.getX() && start.getY() == end.getY()) {
            throw new IllegalArgumentException();
        }
        if(start.getX() < end.getX()) {
            this.start = new Point(start.getX(), start.getY());
            this.end = new Point(end.getX(), end.getY());
        }
        else{
            this.start = new Point(end.getX(), end.getY());
            this.end = new Point(start.getX(), start.getY());
        }
    }

    public double length() {
        double length = Math.sqrt(Math.pow((this.end.getX() - this.start.getX()), 2.0) + Math.pow((this.end.getY() - this.start.getY()), 2.0));
        return length;
    }

    public Point middle() {
        double x = ((this.start.getX() + this.end.getX()) / 2);
        double y = ((this.start.getY() + this.end.getY()) / 2);
        Point middle = new Point(x, y);
        return middle;
    }

    public Point intersection(Segment another) {
        if(another == null){
            throw new IllegalArgumentException();
        }
        if(this.start.getX() < another.start.getX() && this.end.getX() < another.start.getX()){
            return null;
        }
        if(another.start.getX() < this.start.getX() && another.end.getX() < this.start.getX()){
            return null;
        }
        double A1 = (this.start.getY() - this.end.getY()) / (this.start.getX() - this.end.getX());
        double A2 = (another.start.getY() - another.end.getY()) / (another.start.getX() - another.end.getX());
        if(A1 == A2){
            return null;
        }
        double b1 = this.start.getY() - A1 * this.start.getX();
        double b2 = another.start.getY() - A2 * another.start.getX();

        double Xa  =  (b2 - b1)/(A1-A2);
        double Ya = A2 * Xa + b2;
        if ((Xa < Math.max(this.start.getX(), another.start.getX())) || (Xa > Math.min( this.end.getX(), another.end.getX()))) {
            return null;
        }
        return new Point(Xa, Ya);
    }
}
