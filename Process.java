public class Process {
    public static Philosoph[] philosophs = new Philosoph[5];
    public volatile static Integer sumEat = 0;

    public static void fullArray(Philosoph[] philosophs){
        for (int i = 0; i < 5; i++) {
            String name = "philosoph" + i;
            philosophs[i] = new Philosoph(name, i);
        }
    }

    public static void runProcess(){

        fullArray(philosophs);

        philosophs[0].start();
        philosophs[1].start();
        philosophs[2].start();
        philosophs[3].start();
        philosophs[4].start();
    }

}
