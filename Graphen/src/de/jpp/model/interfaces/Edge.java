package de.jpp.model.interfaces;

import java.util.Optional;
import java.util.Objects;

public class Edge<N, A> {

    private final N start;
    private final N destination;
    private final Optional<A> annotation;



    public Edge(N start, N dest, Optional<A> annotation) {
        if(start == null){
            throw new NullPointerException("Startknoten darf nicht null sein");

        }
        if (dest==null){
            throw new NullPointerException("Endknoten darf nicht null sein");
        }
        if(annotation==null){
            throw new NullPointerException("Annonation darf nicht null sein");
        }
        this.start = start;
        this.destination =dest;
        this.annotation=annotation;
    }

    public N getStart() {
        return start;
    }

    public N getDestination() {
        return destination;
    }

    public Optional<A> getAnnotation() {
        return annotation;
    }

    @Override //toString Methode für die Ausgabe
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(start).append(" -> ").append(destination);
        if (annotation.isPresent()) {
            sb.append(" [").append(annotation.get()).append("]");
        }
        return sb.toString();
    }
    @Override
    public boolean equals(Object obj){
        if(this == obj)  return true;
        if (obj==null || getClass()!=obj.getClass())return false;

        Edge<?,?> edge =(Edge<?,?>) obj;
        return Objects.equals(start,edge.start)&& Objects.equals(destination, edge.destination) &&
                Objects.equals(annotation, edge.annotation);


    }
    @Override
    public int hashCode(){
        return Objects.hash(start,destination,annotation);

    }



}
