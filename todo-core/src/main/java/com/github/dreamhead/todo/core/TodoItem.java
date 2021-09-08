package com.github.dreamhead.todo.core;

public class TodoItem {
    private final String content;
    private boolean done;
    private long index;

    public TodoItem(final String content) {
        this.content = content;
    }

    public String getContent() {
       return content;
    }


    public void markDone() {
        this.done = true;
    }

    public boolean isDone() {
        return done;
    }

    public long getIndex() {
       return index;
    }

    public void assignIndex(long index) {
        this.index = index;
    }
}
