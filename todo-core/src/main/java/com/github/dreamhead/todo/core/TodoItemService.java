package com.github.dreamhead.todo.core;

import java.util.Optional;
import java.util.stream.StreamSupport;

public class TodoItemService {
    private final TodoItemRepository repository;

    public TodoItemService(final TodoItemRepository repository) {
        this.repository = repository;
    }

    public TodoItem addTodoItem(final TodoParameter parameter) {
        if (parameter == null) {
            throw new IllegalArgumentException("Null or empty content is not allowed");
        }
        TodoItem item = new TodoItem(parameter.getContent());
        return this.repository.save(item);
    }

    public Optional<TodoItem> markTodoItemDone(TodoIndexParameter index) {
       Iterable<TodoItem> all = this.repository.findAll();
        final Optional<TodoItem> optionalItem = StreamSupport.stream(all.spliterator(), false)
                .filter(element -> element.getIndex() == index.getIndex())
                .findFirst();
        return optionalItem.flatMap(this::doMarkAsDone);

    }
    private Optional<TodoItem> doMarkAsDone(final TodoItem todoItem) {
        todoItem.markDone();
        return Optional.of(this.repository.save(todoItem));
    }

}
