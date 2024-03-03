public class Main {
    public static void main(String[] args){
ProducerConsumer pc = new ProducerConsumer();

Thread producer = new Thread(new Runnable() {
    @Override
    public void run() {

        pc.produce();
    }
});
Thread consumer = new Thread(new Runnable() {
    @Override
    public void run() {
   pc.consume(); // metodların constructoru olabilir o yüzden constructorunu yazdık. boşta olsa
    }
});

producer.start();
consumer.start();

        try {
            producer.join(); //bu thread ilk önce bitecek main threadinden önce demek
            consumer.join();
        } catch (InterruptedException e) {
            System.out.printf("Bir hata oluştu ve threadlerimiz kesildi.");
        }

    }
}
