package ladder.domain;

import ladder.util.Const;
import ladder.util.Rule;

import java.util.*;

/**
 * 사다리 결과 값을 저장하는 클래스
 * <br> LadderResult radderResult = new RadderResult("pobi, crong, hi", "꽝, 1000, 2000", 5)
 * <br> radderResult.getResultOfName("pobi")
 * <br> radderResult.getResultOfName("all")
 *
 * @author heebg
 * @version 1.0 2019-05-16
 */
public class LadderResult {
    Ladder ladder;
    Map<String, String> result;

    /**
     * 생성자
     *
     * @param names
     * @param rewards
     * @param depth
     */
    public LadderResult(String names, String rewards, int depth) {
        names = Rule.ruleInputPlayerNames(names);
        rewards = Rule.ruleInputReward(rewards,names.split(",").length);
        depth = Rule.ruleLadderDepthRange(depth);

        this.ladder = new Ladder(setPlayers(names).size(),depth);
        this.result = setResult(setPlayers(names),Arrays.asList(rewards.split(",")),depth);
    }

    private Map<String, String> setResult(List<Player> players, List<String> rewards, int depth) {
        Map<String, String> result = new HashMap<>();
        List<Integer> initResult = ladder.getResult();
        for (int i = 0; i < players.size(); i++) {
            result.put(players.get(i).getName(),rewards.get(initResult.get(i)));
        }
        return result;
    }

    private List<Player> setPlayers(String playerNames) {
        List<Player> players = new ArrayList<>();
        List<String> names = Arrays.asList(playerNames.split(","));
        for (String name : names) {
            players.add(new Player(name));
        }
        return players;
    }

    /**
     * 이름의 결과값 반환
     * @param name
     * @return
     */
    public String getResultOfName(String name) {
        if (name.equals(Const.LADDERRESULT_GET_RESULT_ALL)) {
            return getResultOfAllName();
        }
        return result.get(name);
    }


    private String getResultOfAllName() {
        StringJoiner stringJoiner = new StringJoiner("\n");
        for (Map.Entry<String, String> entry : result.entrySet()) {
            stringJoiner.add(entry.getKey() + " : " + entry.getValue());
        }
        return stringJoiner.toString();
    }

    /**
     * 사다리 모양 출력
     *
     * @return
     */
    public String getLadderShape() {
        return ladder.getLadderShape();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LadderResult that = (LadderResult) o;
        return Objects.equals(ladder, that.ladder) &&
                Objects.equals(result, that.result);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ladder, result);
    }
}