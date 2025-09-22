package de.jpp.model;

import javafx.beans.binding.ObjectExpression;

import java.util.Objects;

public class XYNode {

    private final String label;
    private final double x;
    private final double y;

    public XYNode(String label, double x, double y) {
        if(label==null){
            throw new NullPointerException("Label ist null");

        }
        this.label=label;
        this.x=x;
        this.y=y;
    }

    public String getLabel() {
        return label;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double euclidianDistTo(XYNode other) {
    if (other==null){
        throw new NullPointerException("Anderer Knoten ist null");

    }
    double deltaX= this.x - other.x;
    double deltay=this.y-other.y;
    return Math.sqrt(deltaX*deltaX+deltay*deltay);

    }
    @Override
    public String toString() {
        return String.format("%s(%.2f, %.2f)", label, x, y);
    }
    @Override
    public boolean equals(Object o){
        if (this == o){
            return true;
        }
        if(o == null || getClass()!= o.getClass()){
            return false;
        }

        XYNode xyNode = (XYNode) o;
        return Double.compare(xyNode.x,x)== 0 && Double.compare(xyNode.y,y)== 0 && Objects.equals(label, xyNode.label);
    }
    @Override
    public int hashCode(){
        return Objects.hash(label,x,y);
    }
}
