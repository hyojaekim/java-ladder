package ladder.domain;

import ladder.view.PlayerException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 사다리 가로 줄 하나를 만드는 클래스
 * <br> LadderLine ladderLine = new LadderLine(row)
 * <br> ladderLine.getNextPosition(index)
 *
 * @author heebg, hyojaekim
 * @version 1.0 2019-05-16
 */
public class LadderLine {
    private final int playerCount;
    private List<Point> lineStates;

    /**
     * 생성자. 가로 길이를 입력한다.
     *
     * @param playerCount 가로 길이
     */
    public LadderLine(int playerCount) {
        this.playerCount = PlayerException.playersMinCount(playerCount);
        this.lineStates = setLineStates();
    }

    /**
     * 해당 라인을 실행 한 이후 인덱스를 반환
     *
     * @param index 실행할 인덱스
     * @return index
     */
    public int getNextPosition(int index) {
        if (index != 0 && lineStates.get(index).getNowPoint()) {
            return -1;
        }
        if (index != lineStates.size() - 1 && lineStates.get(index + 1).getNowPoint()) {
            return +1;
        }
        return 0;
    }

    private List<Point> setLineStates() {
        List<Point> lineStates = new ArrayList<>();
        lineStates.add(new Point());
        for (int i = 1; i < playerCount; i++) {
            lineStates.add(new Point(lineStates.get(i - 1).getNowPoint()));
        }
        return lineStates;
    }

    boolean isMatchLineState(int i) {
        return this.lineStates.get(i).getNowPoint();
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Point point : lineStates) {
            stringBuilder.append(point.toString());
        }
        return stringBuilder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LadderLine line = (LadderLine) o;
        return playerCount == line.playerCount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(playerCount);
    }
}
