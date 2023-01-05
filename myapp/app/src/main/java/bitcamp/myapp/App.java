package bitcamp.myapp;

public class App {

  public static void main(String[] args) {
    while (true) {
      System.out.println("1. 회원관리");
      System.out.println("9. 종료");

      int mumuNo = Prompt.inputInt("메뉴> ");
      if (mumuNo == 1) {
        goMenu();
      } else if (mumuNo == 9) {
        System.out.println("바이바이");
        break;
      } else {
        System.out.println("잘못된 메뉴입니다");
      }
    }
    Prompt.close();
  }
  static void goMenu() {
    while (true) {
      System.out.println("[회원관리]");
      System.out.println("1. 등록");
      System.out.println("2. 목록");
      System.out.println("3. 조회");
      System.out.println("4. 변경");
      System.out.println("5. 삭제");
      System.out.println("0. 이전");

      int mumuNo2 = Prompt.inputInt("[회원관리]");
      if (mumuNo2 == 1) {
        MemberHandler.inputMembers();
      } else if (mumuNo2 == 2) {
        MemberHandler.printMembers();
      } else if (mumuNo2 == 0) {
        break;
      } else {
        System.out.println("잘못된 메뉴입니다");
      }
    }
  }
} // class App
