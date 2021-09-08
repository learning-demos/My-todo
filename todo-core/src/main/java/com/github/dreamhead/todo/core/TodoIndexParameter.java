package com.github.dreamhead.todo.core;

public class TodoIndexParameter {
    private final int index;

    public TodoIndexParameter(int index) {

        this.index = index;
    }

    public static TodoIndexParameter of(int index) {
       if(index <=0){
           throw  new IllegalArgumentException("Todo index should be greater than 0");
       }

       return   new TodoIndexParameter(index);
    }

    public long getIndex() {
       return index;
    }
}
