package bitcamp.myapp;

public class App {

  public static void main(String[] args) {
    goMainMenu();
    System.out.println("종료되었습니다");

    // 프로그램이 사용한 자원 해제하기
    Prompt.close();
  } // main()

  private static void goMainMenu() {
    // 일반 학생 목록을 저장할 메모리를 준비한다.
    BoardHandler generalMemberHandler = new BoardHandler("일반학생");

    // 국비지원 학생 목록을 저장할 메모리를 준비한다.
    BoardHandler supportedMemberHandler = new BoardHandler("국비지원학생");

    // 기업 위탁 교육생 목록을 저장할 메모리를 준비한다.
    BoardHandler companyMemberHandler = new BoardHandler("위탁교육생");


    while (true) {
      System.out.println("1. 게시글관리");
      System.out.println("9. 종료");
      int menuNo = Prompt.inputInt("메뉴> ");

      if (menuNo == 1) {
        generalMemberHandler.service();
      } else if (menuNo == 9) {
        break;
      } else {
        System.out.println("잘못된 메뉴 번호 입니다.");
      }
    }
  }

} // class App









