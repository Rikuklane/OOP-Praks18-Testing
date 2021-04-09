package task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.*;

public class TodoAppTest {

  // TODO add your tests here

    @Test
    void itemIsInListAfterBeingAdded() throws IOException {
        var todoApp = new TodoApp(new MockWriteRead());
        todoApp.addTodoItem("todo");
        assertTrue(todoApp.getTodoList().contains("todo"));
    }

    @Test
    void itemNotInListAfterBeingRemoved() throws IOException {
        var todoApp = new TodoApp(new MockWriteRead());
        todoApp.addTodoItem("todo");
        todoApp.removeTodoItem("todo");
        assertFalse(todoApp.getTodoList().contains("todo"));
    }

    @Test
    void addingDuplicateItemDoesNotChangeStoredData() throws IOException {
        var todoApp = new TodoApp(new MockWriteRead());
        todoApp.addTodoItem("todo");
        todoApp.addTodoItem("todo");
        assertTrue(todoApp.getTodoList().size() == 1);
    }


}

class MockWriteRead extends WriteRead{

    private byte[] todo;

    @Override
    public byte[] readStoredValues() throws IOException {
        return todo;
    }

    @Override
    public void writeValuesToStore(byte[] values) throws IOException {
        todo = values;
    }
}
