package util.structures;

public interface Queue<T> extends Collection<T>{
    T poll();
    T peek();
}
