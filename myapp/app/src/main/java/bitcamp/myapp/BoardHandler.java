package bitcamp.myapp;

public class BoardHandler {

  // 모든 인스턴스가 공유하는 데이터를 스태틱 필드로 만든다.
  // 특히 데이터를 조회하는 용으로 사용하는 final 변수는 스태틱 필드로 만들어야 한다.
  static final int SIZE = 100;

  int count;
  Board[] boards = new Board[SIZE];
  String title;

  // 인스턴스를 만들 때 프롬프트 제목을 반드시 입력하도록 강제한다
  BoardHandler(String title) {
    this.title = title;
  }

  void inputBoard() {
    Board m = new Board();

    m.no = Prompt.inputInt("게시글 번호? ");
    m.getdate = Prompt.inputString("작성일 ex)23-01-07 ");
    m.title = Prompt.inputString("게시글 제목? ");
    m.content = Prompt.inputString("게시글 내용? ");
    m.password = Prompt.inputString("게시글 암호? ");
    m.viewCount = 1;

    this.boards[count++] = m;
  }

  void printBoards() {
    System.out.println("게시글 번호\t게시글 제목\t작성일\t조회수\t");

    for (int i = 0; i < this.count; i++) {
      Board m = this.boards[i];
      System.out.printf("%8d\t%8s\t%4s\t%s\t\n",
          m.no, m.title, m.getdate, m.viewCount);
    }
  }

  void printBoard() {
    int board = Prompt.inputInt("게시글 번호? ");

    Board m = this.findByNo(board);

    if (m == null) {
      System.out.println("해당 번호의 게시글이 없습니다.");
      return;
    }

    System.out.printf("게시글 제목: %s\n", m.title);
    System.out.printf("게시글 내용: %s\n", m.content);
    System.out.printf("게시글 암호: %s\n", m.password);
    System.out.printf("작성일: %s\n", m.getdate);
    System.out.printf("조회수: %s\n", m.viewCount);
  }

  void modifyMember() {
    int memberNo = Prompt.inputInt("게시글 번호? ");

    Board old = this.findByNo(memberNo);

    if (old == null) {
      System.out.println("해당 게시글이 없습니다.");
      return;
    }

    // 변경할 데이터를 저장할 인스턴스 준비
    Board m = new Board();
    m.no = old.no;
    m.getdate = old.getdate;
    m.viewCount = old.viewCount;
    m.password = Prompt.inputString(String.format("게시글 암호(%s)? ", old.password));

    String str = Prompt.inputString("게시글 암호를 정말 변경하시겠습니까?(y/N) ");
    if (str.equalsIgnoreCase("Y")) {
      this.boards[this.indexOf(old)] = m;
      System.out.println("암호를 변경했습니다.");
    } else {
      System.out.println("게시글 암호 변경을 취소했습니다.");
    }
  }

  void deleteMember() {
    int memberNo = Prompt.inputInt("게시글 번호? ");

    Board m = this.findByNo(memberNo);

    if (m == null) {
      System.out.println("해당 게시글이 없습니다.");
      return;
    }

    String str = Prompt.inputString("게시글을 정말 삭제하시겠습니까?(y/N) ");
    if (!str.equalsIgnoreCase("Y")) {
      System.out.println("게시글 삭제를 취소했습니다.");
      return;
    }

    for (int i = this.indexOf(m) + 1; i < this.count; i++) {
      this.boards[i - 1] = this.boards[i];
    }
    this.boards[--this.count] = null; // 레퍼런스 카운트를 줄인다.

    System.out.println("게시글을 삭제했습니다.");

  }

  Board findByNo(int no) {
    for (int i = 0; i < this.count; i++) {
      if (this.boards[i].no == no) {
        return this.boards[i];
      }
    }
    return null;
  }

  int indexOf(Board m) {
    for (int i = 0; i < this.count; i++) {
      if (this.boards[i].no == m.no) {
        return i;
      }
    }
    return -1;
  }

  //int viewCount(Board m) {
  // UPDATE
  //}

  void service() {
    while (true) {
      System.out.printf("[%s]\n", this.title);
      System.out.println("1. 게시글 입력");
      System.out.println("2. 게시글 목록");
      System.out.println("3. 게시글 조회");
      System.out.println("4. 게시글 암호 변경");
      System.out.println("5. 게시글 삭제");
      System.out.println("0. 이전으로 돌아가기");
      int menuNo = Prompt.inputInt(String.format("%s> ", this.title));

      switch (menuNo) {
        case 0: return;
        case 1: this.inputBoard(); break;
        case 2: this.printBoards(); break;
        case 3: this.printBoard(); break;
        case 4: this.modifyMember(); break;
        case 5: this.deleteMember(); break;
        default:
          System.out.println("잘못된 메뉴 번호 입니다.");
      }
    }
  }
}
