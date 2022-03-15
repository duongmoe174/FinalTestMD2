package view;

import controller.PhoneBookManager;
import model.PhoneBook;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Client {
    private static List<PhoneBook> phoneBookListClient = PhoneBookManager.phoneBookList;

    public static void main(String[] args) {
        int choiceMain = -1;
        while (choiceMain != 0) {
            Scanner inputChoiceMain = new Scanner(System.in);
            System.out.println("--- Chương trình quản lý danh bạ ---");
            System.out.println("Chọn chức năng theo số (để tiếp tục)");
            System.out.println("1. Xem danh sách");
            System.out.println("2. Thêm mới");
            System.out.println("3. Cập nhật");
            System.out.println("4. Xóa");
            System.out.println("5. Tìm kiếm");
            System.out.println("6. Đọc từ file");
            System.out.println("7. Ghi vào file");
            System.out.println("8.  Thoát");
            choiceMain = inputChoiceMain.nextInt();
            switch (choiceMain) {
                case 1:
                    showAllPhoneBook();
                    break;
                case 2:
                    addNewPhoneBook();
                    break;
                case 3:
                    editPhoneBookByPhone();
                    break;
                case 4:
                    removePhoneBookByPhone();
                    break;
                case 5:
                    searchPhoneBookByPhone();
                    break;
                case 6:
                    break;
                case 7:
                    try {
                        PhoneBookManager.phoneBookData.writeFile((ArrayList<PhoneBook>) phoneBookListClient);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                case 8:
                    System.exit(0);
                default:
                    System.err.println("Hãy chọn 1 mục!");
            }
        }

    }

    public static void addNewPhoneBook() {
        PhoneBook phoneBook = createNewPhoneBook();
        PhoneBookManager.addNewPhoneBook(phoneBook);
    }

    public static void showAllPhoneBook() {
        for (PhoneBook e : phoneBookListClient
        ) {
            System.out.println(e);
        }
    }

    public static PhoneBook createNewPhoneBook() {
        System.out.println("Nhập số điện thoại: ");
        Scanner inputPhoneNumber = new Scanner(System.in);
        String phone = inputPhoneNumber.nextLine();

        System.out.println("Nhập nhóm danh bạ: ");
        Scanner inputGroup = new Scanner(System.in);
        String group = inputGroup.nextLine();

        System.out.println("Nhập tên danh bạ: ");
        Scanner inputName = new Scanner(System.in);
        String name = inputName.nextLine();

        System.out.println("Nhập giới tính: ");
        Scanner inputGender = new Scanner(System.in);
        String gender = inputGender.nextLine();

        System.out.println("Nhập địa chỉ: ");
        Scanner inputAddrees = new Scanner(System.in);
        String address = inputAddrees.nextLine();

        System.out.println("Nhập ngày sinh: ");
        Scanner inputDay = new Scanner(System.in);
        int day = inputDay.nextInt();

        System.out.println("Nhập tháng sinh: ");
        Scanner inputMonth = new Scanner(System.in);
        int month = inputMonth.nextInt();

        System.out.println("Nhập năm sinh: ");
        Scanner inputYear = new Scanner(System.in);
        int year = inputYear.nextInt();

        LocalDate birthDay = LocalDate.of(year, month, day);

        System.out.println("Nhập Email: ");
        Scanner inputEmail = new Scanner(System.in);
        String email = inputEmail.nextLine();

        return new PhoneBook(phone, group, name, gender, address, birthDay, email);
    }

    public static void editPhoneBookByPhone() {
        System.out.println("Nhập số điện thoại cần sửa:");
        while (true) {
            Scanner inputPhone = new Scanner(System.in);
            String phone = inputPhone.nextLine();
            int check = -1;
            if (PhoneBookManager.getPhoneBookByPhone(phone) == check) {
                System.err.println("Số điện thoại không có trong danh bạ");
            } else {
                int index = PhoneBookManager.getPhoneBookByPhone(phone);
                System.out.println("Sửa nhóm: ");
                Scanner inputGroup = new Scanner(System.in);
                String group = inputGroup.nextLine();

                System.out.println("Sửa tên: ");
                Scanner inputName = new Scanner(System.in);
                String name = inputName.nextLine();

                System.out.println("Sửa giới tính: ");
                Scanner inputGender = new Scanner(System.in);
                String gender = inputGender.nextLine();

                System.out.println("Sửa địa chỉ: ");
                Scanner inputAddress = new Scanner(System.in);
                String address = inputAddress.nextLine();

                System.out.println("Sửa ngày sinh: ");
                Scanner inputDay = new Scanner(System.in);
                int day = inputDay.nextInt();

                System.out.println("Sửa tháng sinh: ");
                Scanner inputMonth = new Scanner(System.in);
                int month = inputMonth.nextInt();

                System.out.println("Sửa năm sinh: ");
                Scanner inputYear = new Scanner(System.in);
                int year = inputYear.nextInt();

                LocalDate brithDay = LocalDate.of(year, month, day);

                System.out.println("Sửa email: ");
                Scanner inputEmail = new Scanner(System.in);
                String email = inputEmail.nextLine();

                PhoneBook phoneBook = new PhoneBook(phone, group, name, gender, address, brithDay, email);
                PhoneBookManager.editBookByIndex(index, phoneBook);
                break;
            }
        }
    }

    public static void removePhoneBookByPhone() {
        System.out.println("Nhập số điện thoại cần xóa:");
        Scanner inputPhone = new Scanner(System.in);
        String phone = inputPhone.nextLine();
        int check = -1;
        if (PhoneBookManager.getPhoneBookByPhone(phone) == check) {
            System.err.println("Số điện thoại không có trong danh bạ");
        } else {
            int index = PhoneBookManager.getPhoneBookByPhone(phone);
            Scanner inputChoice = new Scanner(System.in);
            System.out.println("Nhấn 'Y' để xóa");
            String str = inputChoice.nextLine();
            if (str.equals("Y")) {
                PhoneBookManager.deletePhoneBook(index);
            } else {
                System.exit(0);
            }
        }
    }

    public static void searchPhoneBookByPhone() {
        System.out.println("Nhập số điện thoại cần tìm");
        while (true) {
            Scanner input = new Scanner(System.in);
            String phone = input.nextLine();
            int index = PhoneBookManager.getPhoneBookByPhone(phone);
            if (index == -1) {
                System.err.println("Không tìm thấy! vui lòng nhập lại");
            } else {
                System.out.println(phoneBookListClient.get(index));
                break;
            }
        }
    }
}
