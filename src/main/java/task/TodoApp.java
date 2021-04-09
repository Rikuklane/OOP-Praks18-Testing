package task;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;

public class TodoApp {

  private WriteRead writeRead;

  public TodoApp(WriteRead writeRead) {
    this.writeRead = writeRead;
  }

  public Set<String> getTodoList() throws IOException {
    return Collections.unmodifiableSet(writeRead.fromBytes(writeRead.readStoredValues()));
  }

  public void addTodoItem(String item) throws IOException {
    Set<String> todos = writeRead.fromBytes(writeRead.readStoredValues());
    if (todos.add(item)) {
      writeRead.writeValuesToStore(writeRead.toBytes(todos));
    }
  }

  public void removeTodoItem(String item) throws IOException {
    Set<String> todos = writeRead.fromBytes(writeRead.readStoredValues());
    if (todos.remove(item)) {
      writeRead.writeValuesToStore(writeRead.toBytes(todos));
    }
  }
}
