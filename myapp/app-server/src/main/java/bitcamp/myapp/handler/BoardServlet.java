package bitcamp.myapp.handler;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import com.google.gson.Gson;
import bitcamp.myapp.dao.BoardDao;
import bitcamp.myapp.vo.Board;

public class BoardServlet {

  private BoardDao boardDao;

  public BoardServlet(BoardDao boardDao) {
    this.boardDao = boardDao;
  }

  private void onInsert(DataInputStream in, DataOutputStream out) throws Exception {
    Board b = new Board();

    new Gson().fromJson(in.readUTF(), Board.class);
    this.boardDao.insert(b);
    out.writeUTF("200");
    out.writeUTF("success");
  }

  private void onFindAll(DataInputStream in, DataOutputStream out) throws Exception {
    out.writeUTF("200");

    System.out.println("번호\t제목\t작성일\t조회수");

    Board[] boards = this.boardDao.findAll();

    for (Board b : boards) {
      System.out.printf("%d\t%s\t%s\t%d\n",
          b.getNo(), b.getTitle(), b.getCreatedDate(), b.getViewCount());
    }
  }

  private void onFindByNo(DataInputStream in, DataOutputStream out) throws Exception {
    int boardNo = new Gson().fromJson(in.readUTF(), int.class);

    Board b = this.boardDao.findByNo(boardNo);

    if (b == null) {
      out.writeUTF("400");
      return;
    }
    out.writeUTF("200");
    out.writeUTF(new Gson().toJson(b));
  }

  private void onUpdate(DataInputStream in, DataOutputStream out) throws Exception {
    Board board = new Gson().fromJson(in.readUTF(), Board.class);

    Board old = this.boardDao.findByNo(board.getNo());

    if (old == null) {
      out.writeUTF("400");
      return;
    }
    this.boardDao.update(board);
    out.writeUTF("200");
    out.writeUTF("success");
  }

  private void onDelete(DataInputStream in, DataOutputStream out) throws Exception {
    Board board = new Gson().fromJson(in.readUTF(), Board.class);

    Board b = this.boardDao.findByNo(board.getNo());
    if (b == null) {
      out.writeUTF("400");
      return;
    }

    this.boardDao.delete(b);
    out.writeUTF("200");
    out.writeUTF("success");
  }

  public void service(DataInputStream in, DataOutputStream out) throws Exception {
    try {
      String action = in.readUTF();

      switch (action) {
        case "insert" : this.onInsert(in, out); break;
        case "findAll" : this.onFindAll(in, out); break;
        case "findByNo" : this.onFindByNo(in, out); break;
        case "update" : this.onUpdate(in, out); break;
        case "delete" : this.onDelete(in, out); break;
        default:
          System.out.println("잘못된 메뉴 번호 입니다.");
      }
    } catch (Exception e) {
      out.writeUTF("500");
    }
  }
}
