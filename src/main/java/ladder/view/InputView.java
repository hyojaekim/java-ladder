package ladder.view;

import ladder.domain.LadderResult;
import ladder.validator.LadderDepthValidate;
import ladder.validator.PlayerValidate;
import ladder.validator.RewardValidate;
import ladder.validator.WantPlayerValidate;

import java.util.*;

public class InputView {
    public static final String EX_LINE_COUNT = "사다리 높이는 1 이상 숫자만 가능 합니다.";
    private static final String INPUT_NAMES = "참여할 사람 이름을 입력하세요. (이름은 쉼표(,)로 구분하세요) (all 제외)";
    private static final String INPUT_DEPTH = "최대 사다리 높이는 몇 개인가요?";
    private static final String INPUT_REWARDS = "실행 결과를 입력하세요. (결과는 쉼표(,)로 구분하세요)";
    private static final String INPUT_WANT_NAME = "결과를 보고 싶은 사람은?";
    private static final Scanner SCANNER = new Scanner(System.in);

    public static String playerNames() {
        try {
            String playerNames = inputPlayerNames();
            PlayerValidate.playerNames(playerNames);
            return playerNames;
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            return playerNames();
        }
    }

    private static String inputPlayerNames() {
        System.out.println(INPUT_NAMES);
        return SCANNER.nextLine();
    }

    public static String rewards(String names) {
        try {
            String rewards = inputRewards();
            RewardValidate.reward(rewards, names.split(",").length);
            return rewards;
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            return rewards(names);
        }
    }

    private static String inputRewards() {
        System.out.println(INPUT_REWARDS);
        return SCANNER.nextLine();
    }

    public static int ladderDepth() {
        try {
            int ladderDepth = inputLadderDepth();
            LadderDepthValidate.ladderMinDepth(ladderDepth);
            return ladderDepth;
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            return ladderDepth();
        }
    }

    private static int inputLadderDepth() {
        try {
            System.out.println(INPUT_DEPTH);
            return Integer.parseInt(SCANNER.nextLine());
        } catch (NumberFormatException e) {
            System.out.println(EX_LINE_COUNT);
            return inputLadderDepth();
        }
    }

    public static String wantName(LadderResult ladderResult) {
        try {
            String resultOfName = ladderResult.getResultOfName(inputWantName());
            WantPlayerValidate.wantName(resultOfName);
            return resultOfName;
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            return wantName(ladderResult);
        }
    }

    private static String inputWantName() {
        System.out.println(INPUT_WANT_NAME);
        return SCANNER.nextLine();
    }
}
