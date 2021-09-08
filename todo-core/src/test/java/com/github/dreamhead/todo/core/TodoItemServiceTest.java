package com.github.dreamhead.todo.core;

import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.AdditionalAnswers.returnsFirstArg;

public class TodoItemServiceTest {
    private TodoItemRepository repository;
    private TodoItemService service;

    @BeforeEach
    public void setUp() {
        this.repository = Mockito.mock(TodoItemRepository.class);
        this.service = new TodoItemService(this.repository);
    }

    @Test
    void should_add_todo_item() {
        Mockito.when(repository.save(Mockito.any())).then(returnsFirstArg());
        TodoItem todoItem = service.addTodoItem(new TodoParameter("foo"));
        assertThat(todoItem.getContent()).isEqualTo("foo");
    }

    @Test
    void should_throw_exception_for_null_todo_item() {
        Mockito.when(repository.save(Mockito.any())).then(returnsFirstArg());
        TodoItemService todoItemService = new TodoItemService(repository);

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> todoItemService.addTodoItem(null));
    }

    @Test
    void should_mark_todo_item_as_done() {
        final TodoItem foo = new TodoItem("foo");
        foo.assignIndex(1);
        Mockito.when(repository.findAll()).thenReturn(ImmutableList.of(foo));
        Mockito.when(repository.save(Mockito.any())).then(returnsFirstArg());
        final Optional<TodoItem> todoItem = service.markTodoItemDone(TodoIndexParameter.of(1));
        assertThat(todoItem).isPresent();
        TodoItem actual = todoItem.get();
        assertThat(actual.isDone()).isTrue();
    }
}
