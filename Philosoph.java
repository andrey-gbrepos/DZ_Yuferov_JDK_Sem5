
public class Philosoph extends Thread{
    Integer numPhilosoph;
    Integer countEating;
    EatResurs eatResurs;
    
    public Philosoph(Integer numPhilosoph, EatResurs eatResurs) {
        this.numPhilosoph = numPhilosoph;
        this.eatResurs = eatResurs;
        this.countEating = 0;
    }
    @Override
    public void run(){
       while (countEating < 3) {
            int firstFork = getNumPhilosoph();
            int secondFork = (getNumPhilosoph() + 1) - 5*(getNumPhilosoph()/4);
            if (eatResurs.getEatResurs(firstFork, secondFork)) {
                try {
                    eatingProcess(firstFork, secondFork);
                    thinkingProcess();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }
           eatResurs.getCountDownLatch().countDown();
       }
    }

    public Integer getNumPhilosoph() {
        return numPhilosoph;
    }

    public void eatingProcess(int firstFork, int secondFork) throws InterruptedException{
        System.out.println("Философ " + getNumPhilosoph() + " кушает вилками "
                            + firstFork + " и " + secondFork);
        countEating++;
        sleep(3000);
        System.out.println("Философ " + getNumPhilosoph() + " кладет вилки "
                + firstFork + " и " + secondFork + " - переходит к размышлению.");
        eatResurs.returnEatResurs(firstFork, secondFork);
    }

    public void thinkingProcess() throws InterruptedException{
        System.out.println("Философ " + getNumPhilosoph() + " размышляет");
            sleep(3000);
    }
    @Override
    public String toString() {
        return String.format("%s", "Философ №" + numPhilosoph);
    }
}
