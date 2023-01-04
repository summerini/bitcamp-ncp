package com.eomcs.oop.ex02.test3;

import com.eomcs.oop.ex02.test3.voi.Score;

public class ScoreTest {

  public static void main(String[] args) {

    Score s1 = new Score("홍길동", 100, 100, 100);
    printScore(s1);

    Score s2 = new Score("임꺽정", 90, 90, 90);
    printScore(s2);

    Score s3 = new Score("유관순", 80, 80, 80);
    printScore(s3);
  }

  static void printScore(Score s) {
    System.out.printf("%s: %d, %d, %d, %d, %.1f\n", s.name, s.kor, s.eng, s.math, s.sum, s.aver);
  }
}
