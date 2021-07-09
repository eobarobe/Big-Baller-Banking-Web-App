package com.revature.bigballerbank.util.structures;

public interface Queue<T> extends Collection<T>{
    T poll();
    T peek();
}
