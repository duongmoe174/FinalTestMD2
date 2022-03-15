package storage;

import model.PhoneBook;

import java.io.*;
import java.util.ArrayList;

public class PhoneBookFromCSV implements IPhoneBookData {
    @Override
    public ArrayList<PhoneBook> readFile() {
        File file = new File("phonebook.csv");
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            Object result = objectInputStream.readObject();
            ArrayList<PhoneBook> phoneBookList = (ArrayList<PhoneBook>) result;
            objectInputStream.close();
            fileInputStream.close();
            return phoneBookList;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Override
    public void writeFile(ArrayList<PhoneBook> phoneBookList) throws IOException {
        FileOutputStream fileOutputStream = null;
        ObjectOutputStream objectOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream("phonebook.csv");
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(phoneBookList);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            objectOutputStream.close();
            fileOutputStream.close();
        }
    }
}
