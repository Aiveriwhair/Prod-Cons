package prodcons.threads;

public class Consumer extends Thread  {
    int id;
    String msg;

    public Consumer(int id, String msg){
        this.id= id ;
        this.msg = msg;
        this.start();
    }

    public void run(){
        


    }





    public long getId(){
        return id;
    }



}
