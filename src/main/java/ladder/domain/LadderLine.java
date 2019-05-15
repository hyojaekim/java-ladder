package ladder.domain;

import ladder.Const;
import ladder.util.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class LadderLine {
    private final int playerCount;
    private List<Boolean> lineStates;

    public LadderLine(int playerCount) {
        this.playerCount = checkCount(playerCount);
        this.lineStates = setLineStates();
    }

    private int checkCount(int playerCount) {
        if (playerCount < Const.MIN_PLAYER_COUNT) {
            throw new IllegalArgumentException(Const.EX_PLAYER_COUNT);
        }
        return playerCount;
    }

    private List<Boolean> setLineStates() {
        List<Boolean> lineStates = new ArrayList<>();
        lineStates.add(false);
        for (int i = 1; i < playerCount; i++) {
            lineStates.add(setNextState(lineStates.get(i - 1)));
        }
        return lineStates;
    }

    static boolean setNextState(boolean state) {
        if (state) {
            return false;
        }
        return Util.getRandomState();
    }

    private String getStateShape(boolean lineState) {
        if (lineState) {
            return Const.LINE_STATE_TRUE;
        }
        return Const.LINE_STATE_FALSE;
    }

    boolean isMatchLineState(int i) {
        return this.lineStates.get(i);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Boolean lineState : lineStates) {
            stringBuilder.append(getStateShape(lineState));
        }
        stringBuilder.append("\n");
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
