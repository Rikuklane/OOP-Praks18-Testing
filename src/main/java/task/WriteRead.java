package task;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Set;
import java.util.TreeSet;

public class WriteRead {

    public byte[] readStoredValues() throws IOException {
        Path path = Path.of("todo.bin");
        if (!Files.isRegularFile(path))
            return null;
        return Files.readAllBytes(path);
    }

    public void writeValuesToStore(byte[] values) throws IOException {
        Files.write(Path.of("todo.bin"), values);
    }

    public byte[] toBytes(Set<String> todos) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);
        dos.writeInt(todos.size());
        for (String todo : todos) {
            dos.writeUTF(todo);
        }
        return baos.toByteArray();
    }

    public Set<String> fromBytes(byte[] bytes) throws IOException {
        Set<String> todos = new TreeSet<>();
        if (bytes != null) {
            DataInputStream dis = new DataInputStream(new ByteArrayInputStream(bytes));
            int items = dis.readInt();
            for (int i = 0; i < items; i++) {
                todos.add(dis.readUTF());
            }
        }
        return todos;
    }
}
