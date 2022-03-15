package storage;

import model.PhoneBook;

import java.io.IOException;
import java.util.ArrayList;

public interface IPhoneBookData {
    ArrayList<PhoneBook> readFile();
void writeFile (ArrayList<PhoneBook> phoneBookList) throws IOException;
}
