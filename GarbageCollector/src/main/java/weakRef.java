import javax.xml.crypto.Data;
import java.util.WeakHashMap;

public class weakRef {
    public static void main(String[] args) {

        //new weakRef().demoWeakHashMap();
        new weakRef().demoWeakHashMap2();

    }
    public void demoWeakHashMap() {
        System.out.println(Runtime.getRuntime().freeMemory());
        WeakHashMap<Long, Long[]> map = new WeakHashMap<>();
        Long i = 0L;
        do {
            Long[] arr = new Long[1024 * 1024*1];
            Long key = i;
            map.put(key, arr);
            key = null;
            i++;
            System.out.printf("Memory: %d, size: %d%n", Runtime.getRuntime().freeMemory(),map.size());

            if (Runtime.getRuntime().freeMemory() < 16 * 1024 * 1024) { //Подобрать значение, чтобы вызвать исключение
                System.gc();
                try {
                    Thread.sleep(100); //Подобрать значение, чтобы вызвать исключение
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } while (!map.isEmpty());
    }
    public void demoWeakHashMap2() {
        System.out.println("---weak2---");
        //Удаление из WeakHashMap происходит, если удалить ключ, а после этого Garbage Collector удалит запись.
        //А он не хочет удалять, т.к. памяти достаточно
        WeakHashMap<Data, Long[]> map = new WeakHashMap<>();
        System.out.println(Runtime.getRuntime().freeMemory());
        Long[] arr1 = new Long[1024 * 1024 * 10];
        Data key1 = new Data("data1");
        System.out.println(Runtime.getRuntime().freeMemory());
        Long[] arr2 = new Long[1024 * 1024 * 10];
        Data key2 = new Data("data2");
        System.out.println(Runtime.getRuntime().freeMemory());

        map.put(key1, arr1);
        map.put(key2, arr2);

        System.out.println(map.containsKey(key1));
        System.out.println(map.containsKey(key1));

        key1 = null;
        System.out.println(Runtime.getRuntime().freeMemory());
        System.out.println(map.size());
        System.gc();
        try {
            Thread.sleep(200);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Runtime.getRuntime().freeMemory());
        do {
        }
        while (map.size() > 1);
        System.out.println(Runtime.getRuntime().freeMemory());
        System.out.println("The first record has been deleted");
        key2 = null;
        System.gc();
        long tic = System.currentTimeMillis();
        do {
        }
        while (map.size() > 0);
        long toc = System.currentTimeMillis();
        long duration = toc-tic;
        System.out.println("The second record has been deleted after "+duration+" ms");
        System.out.println(Runtime.getRuntime().freeMemory());
    }
    static class Data {
        String value;
        Data(String value) {
            this.value = value;
        }
    }
}
