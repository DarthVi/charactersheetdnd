package interfaces;

import java.io.IOException;

public interface CustomSerializable {
	public void writeToTextFile(String path) throws IOException;
	public void readFromTextFile(String path) throws IOException;
}
