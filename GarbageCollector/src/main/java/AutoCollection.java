public class AutoCollection {
    public static void main(String[] args) {
        //strong, soft,  weak, fantom
        System.out.println("--------Anonumis--------------");
      processAnonum();
        System.out.println("--------name---------");
        processName();
        processAnonum();

        System.out.println(Runtime.getRuntime().freeMemory());
    }
    public static void processAnonum (){
        System.out.println("_______anonim-----");
        new Thing().show();
        {
            new Thing().show();
            new Thing().show();
        }
        new Thing().show();
        new Thing().show();
    }
    public static void processName (){
        System.out.println("----name----");
        Thing thing1, thing2, thing3, thing4, thing5, thing6;
        thing1 = new Thing();
        thing1.show();
        thing2 = new Thing();
        thing2.show();
        thing3 = new Thing();
        thing3.show();
        thing4 = new Thing();
        thing4.show();
        thing5 = new Thing();
        thing5.show();
        thing6 = new Thing();
        thing6.show();
        System.out.println("--SHOW--------");
        thing1.show();
        thing2.show();
        thing3.show();
        thing4.show();
        thing5.show();
        thing6.show();
    }
}
class Thing{
    private static final String names[] = {
            "Stapler","Eraser","Pen", "Pencil", "Marker", "Calculator", "Glue", "Scissors","Notebook","Envelope"};
    private static int counter = 0;
    private static long heapFreeSize = Runtime.getRuntime().freeMemory();
    private long totalSize ;
    private int id;
    private String name;

    public Thing(){
        id = counter++;
        name = names[(int) Math.floor(Math.random()*names.length)];
        totalSize= Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
        heapFreeSize = Runtime.getRuntime().freeMemory();
    }


    @Override
    public String toString() {

        return "Thing{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", heapFreeSize=" + heapFreeSize +
                "  totalSize=" + totalSize +
                " free=" + Runtime.getRuntime().freeMemory() +
                " Total=" + Runtime.getRuntime().totalMemory() +
                '}';
    }

    public void show(){
        System.out.println(toString());
    }

    @Override
    protected void finalize()
    {
        --counter;
    }
}