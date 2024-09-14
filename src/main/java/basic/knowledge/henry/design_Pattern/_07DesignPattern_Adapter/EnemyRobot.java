package basic.knowledge.henry.design_Pattern._07DesignPattern_Adapter;


import java.util.Random;

//adaptee
public class EnemyRobot {
    Random generator = new Random();
    public void smashWithHands(){
        int attackDamage = generator.nextInt(10) + 1;
        System.out.println("Enemy Robot Causes " + attackDamage + " Damage With Its Hands");
    }

    public void walkForward(){
        int movement = generator.nextInt(5) + 1;
        System.out.println("Enemy Robot Walks Forward " + movement + " Spaces With Its Hands");
    }

    public void reactToHuman(String driverName){
        System.out.println("Enemy Robot Tramps on " + driverName);
    }

}
