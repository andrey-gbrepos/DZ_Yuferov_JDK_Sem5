import java.util.Date;
public class Philosoph extends Thread{
    String name;
    Integer numPhilosoph;
    volatile Boolean status;
    Integer countEating;

    public Philosoph(String name, Integer numPhilosoph) {
        this.name = name;
        this.numPhilosoph = numPhilosoph;
        this.status = true;
        this.countEating = 0;
    }
    @Override
    public void run(){
       while(Process.sumEat < 15) {
           if (countEating < 4) {
               if (status) {
                   setNeighboursNegativeStatus(Process.philosophs);
                   countEating++;
                   Process.sumEat++;
                   setNeighboursPositiveStatus(Process.philosophs);
               }
           }
       }
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Integer getCountEating() {
        return countEating;
    }

    public void setCountEating(Integer countEating) {
        this.countEating = countEating;
    }

    public  void setNeighboursNegativeStatus(Philosoph[] philosophs){
        Integer leftNeighbour = (numPhilosoph == 0 )? philosophs.length - 1 :
                                                            numPhilosoph - 1;
        Integer rightNeighbour = (numPhilosoph == philosophs.length -1)? 0 :
                                                          numPhilosoph + 1;
        philosophs[leftNeighbour].setStatus(false);
        philosophs[rightNeighbour].setStatus(false);
        Date dt = new Date();
        System.out.println("Время: " + dt.getTime());
        for (int i = 0; i < philosophs.length; i++) {
            String status = philosophs[i].getStatus()? " кушает " : " размышляет ";
            System.out.println("Философ " + philosophs[i] + status);
        }
    }

    public  void setNeighboursPositiveStatus(Philosoph[] philosophs){
        Integer leftNeighbour = (numPhilosoph == 0 )? philosophs.length - 1 :
                numPhilosoph - 1;
        Integer rightNeighbour = (numPhilosoph == philosophs.length -1)? 0 :
                numPhilosoph + 1;
        philosophs[leftNeighbour].setStatus(true);
        philosophs[rightNeighbour].setStatus(true);
        setStatus(false);
    }

    @Override
    public String toString() {
        return String.format("%s", name);
    }
}
