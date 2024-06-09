import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.CountDownLatch;


public class EatResurs extends Thread{

    public  Boolean[] forks;
    private final Random random = new Random();
    private final Integer countEating = 0;
    private final CountDownLatch countDownLatch;
    private final Philosoph[] philosophs;

    public EatResurs(){
        philosophs = new Philosoph[5];
        countDownLatch = new CountDownLatch(5);
    }

    public void setStartCondition(){
        forks = new Boolean[5];
        Arrays.fill(forks, true);
        for (int i = 0; i < philosophs.length; i++) {
            philosophs[i] = new Philosoph(i, this);
        }
    }

    @Override
    public void run() {
        setStartCondition();
        for (int i = 0; i < philosophs.length ; i++) {
            philosophs[i].start();
        }
    }

    public synchronized boolean getEatResurs(int firstFork, int secondFork){
        if (forks[firstFork] && forks[secondFork]){
            forks[firstFork] = false;
            forks[secondFork] = false;
            return true;
        }
        return false;
        }

    public  void returnEatResurs(int firstFork, int secondFork){
        forks[firstFork] = true;
        forks[secondFork] = true;
    }

    public CountDownLatch getCountDownLatch() {
        return countDownLatch;
    }
}
