import java.util.ArrayList;
public class node
{
    boolean visited, inStak; // для Тарьяна
    int prev, next; // для Флёри
    ArrayList<Integer> ErgodicСlass = new ArrayList<>(); // для Косарайю
    public node(boolean visited, boolean inStak) // Конструктор класса с двумя параметрами для алгоритма Тарьяна
    {
        this.inStak=inStak;
        this.visited=visited;
    }
    public node(int prev, int next) // Конструктор класса с двумя параметрами для алгоритма Флери
    {
        this.next=next;
        this.prev=prev;
    }
    public node(){} // Пустой конструктор
}