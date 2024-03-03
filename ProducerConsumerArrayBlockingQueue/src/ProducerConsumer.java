import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;

public class ProducerConsumer {
    Random random = new Random();

    BlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(10);
    //Arrayblockingqueue senkron sorununu kendi hallediyor
    // constructoruna yazdık maksimum 10 adet değeri olsun diye.
    //Queue veri yapısıyla array oluşturduk. queue veri ypaısıyla array bloğudur.
    //ArrayBLokingQueue kendi içinde threadlerle uyumlu çalışan senkronize eden kodları vardır
    //ArrayBlockingQueue BlockingQueue ' yı implement eder
    //ArrayBlockingQueue'deki  final ReentrantLock lock; kodu threadlerle uyumlu çalışmasını sağlar

    public void produce(){
        while (true){
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            try {

                Integer value = random.nextInt(100); //0-100 arası random değer üretir Random sınıfı
                queue.put(value); // queue.put kendi içinde threadleri kullandığı için arrayblockingqueue 10 olmuşusa
                // consumer threadin değer almasını bekleyecek put methodu değer koyar sonra
                System.out.println("Producer Üretiyor : " + value );


                // put metodu kendi içinde threadleri kulllandığı için try-cacth girdik
                // o yüzden InterruptedException fırlatabilir diyor
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }


        }
    }
    public void consume(){
        while (true){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            try {
                Integer value = queue.take(); // kuyruğun başından değeri alıcak
                //queue take kuyruk boş olunca bekleyecek değer gelmesini o zamana kadar bloklanacak
                //arrayblockingqueue un adıda ordan geliyor threadleri birbirinden blokluyor.
                System.out.println("Consumer Tüketiyor : " + value);

                System.out.println("Queue Size : " + queue.size());;
                
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            //queue.take de kendi içinde threadlerle yazılmış exceptionu yakalamamız lazım.


        }
    }




}
