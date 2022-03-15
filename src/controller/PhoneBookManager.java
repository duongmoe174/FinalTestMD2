package controller;

import model.PhoneBook;
import storage.IPhoneBookData;
import storage.PhoneBookFromCSV;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneBookManager {
    private static final String PHONE_REGEX = "^[(]\\d{2,}[)][-][(][0]\\d{9,}[)]$";
    private static final String EMAIL_REGEX = "^[A-Za-z0-9]+[A-Za-z0-9]*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)$";
    public static IPhoneBookData phoneBookData = new PhoneBookFromCSV();
    public static ArrayList<PhoneBook> phoneBookList = phoneBookData.readFile();


    public static void addNewPhoneBook(PhoneBook phoneBook) {
        phoneBookList.add(phoneBook);
        try {
            phoneBookData.writeFile(phoneBookList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int getPhoneBookByPhone(String phone) {
        for (int i = 0; i < phoneBookList.size(); i++) {
            if (phoneBookList.get(i).getPhone().equals(phone)) {
                return i;
            }
        }
        return -1;
    }

    public static void editBookByIndex(int index, PhoneBook phoneBook) {
        phoneBookList.set(index, phoneBook);
        try {
            phoneBookData.writeFile(phoneBookList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void deletePhoneBook  (int index) {
        phoneBookList.remove(index);
        try {
            phoneBookData.writeFile(phoneBookList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean validatePhone (String regex) {
        Pattern pattern = Pattern.compile(PHONE_REGEX);
        Matcher matcher = pattern.matcher(regex);
        return matcher.matches();
    }

    public static boolean validateEmail (String regex) {
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(regex);
        return matcher.matches();
    }
}
