package bitcamp.myapp;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class Prompt {
  static Scanner scanner = new Scanner(System.in);

  static String inputString(String title) {
    System.out.print(title);
    return scanner.nextLine();
  }

  static int inputInt(String title) {
    return Integer.parseInt(inputString(title));
  }

  static String getDate() {
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date date = new Date(System.currentTimeMillis());
    return (formatter.format(date));
  }

  // Prompt 클래스를 다 사용한 후에 자원을 해제시킬 수 있는 메서드를 추가한다.
  static void close() {
    scanner.close();
  }
}
