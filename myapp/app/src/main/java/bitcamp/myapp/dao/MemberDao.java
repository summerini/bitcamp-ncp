package bitcamp.myapp.dao;

import java.util.Arrays;
import bitcamp.myapp.vo.Member;

public class MemberDao {
  private static final int SIZE = 100;

  private int count;
  private Member[] members = new Member[SIZE];

  public void insert(Member member) {
    this.members[count++] = member;
  }

  public Member[] findAll() {
    // 배열의 값 복제
    //    Board[] arr = new Board[this.count];
    //    for (int i = 0; i < this.count; i++) {
    //      arr[i] = this.boards[i];
    //    }
    //    return arr;

    //위와 같다!
    return Arrays.copyOf(members, count);
  }

  public Member findByNo(int no) {
    for (int i = 0; i < this.count; i++) {
      if (this.members[i].getNo() == no) {
        return this.members[i];
      }
    }
    return null;
  }

  public void update(Member member) {
    this.members[this.indexOf(member)] = member;
  }

  public void delete(Member member) {
    for (int i = this.indexOf(member) + 1; i < this.count; i++) {
      this.members[i - 1] = this.members[i];
    }
    this.members[--this.count] = null;
  }

  private int indexOf(Member m) {
    for (int i = 0; i < this.count; i++) {
      if (this.members[i].getNo() == m.getNo()) {
        return i;
      }
    }
    return -1;
  }
}
