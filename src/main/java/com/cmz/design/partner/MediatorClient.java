package com.cmz.design.partner;

abstract class Colleague{
    private Mediator mediator;
    
    public Colleague(Mediator mediator){
        this.mediator = mediator;
    }
    
    public Mediator getMediator() {
        return mediator;
    }
    
    public abstract void action();
    
    public void change(){
        mediator.colleagueChanged(this);
    }
}
class Colleague1 extends Colleague{
    public Colleague1(Mediator m){
        super(m);
    }
    public void action(){
        System.out.println("this is an action from Colleague1");
    }
}
class Colleague2 extends Colleague{
    public Colleague2(Mediator m){
        super(m);
    }
    public void action(){
        System.out.println("this is an action from Colleague2");
    }
}
interface Mediator{
    public void colleagueChanged(Colleague c);
}
class ConcreteMediator implements Mediator{
    private Colleague1 col1;
    private Colleague2 col2;
    
    public void colleagueChanged(Colleague c) {
        col1.action();
        col2.action();
    }
    
    public void createConcreteMediator() {
        col1 = new Colleague1(this);
        col2 = new Colleague2(this);
    }
    
    private Colleague1 getCol1() {
        return col1;
    }
    
    public Colleague2 getCol2() {
        return col2;
    }
    
}

public class MediatorClient {
    public static void main(String[] args) {
        ConcreteMediator mediator = new ConcreteMediator();
        mediator.createConcreteMediator();
        Colleague1 col1 = new Colleague1(mediator);
//        Colleague2 col2 = new Colleague2(mediator);
        mediator.colleagueChanged(col1);
    }
}
