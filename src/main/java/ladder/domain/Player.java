package ladder.domain;

import ladder.validator.PlayerValidate;

import java.util.List;
import java.util.Objects;

/**
 * 참여자 클래스
 * <br> Player player = new Player("heebg")
 *
 * @author heebg, hyojaekim
 * @version 1.0 2019-05-15
 */
public class Player {
    private final String name;
    private int position;

    /**
     * 생성자
     *
     * @param name 참여자 이름
     * @throws IllegalArgumentException 이름 길이가 rule과 다를 때 발생
     */
    public Player(String name) {
        PlayerValidate.playerNameOverLength(name);
        this.name = name;
    }

    /**
     * 생성자
     *
     * @param name     참여자 이름
     * @param position 참여자 위치
     * @throws IllegalArgumentException 이름 길이가 rule과 다를 때 발생
     */
    public Player(String name, int position) {
        PlayerValidate.playerNameOverLength(name);
        this.name = name;
        this.position = position;
    }

    public void moveLeft() {
        position--;
    }

    public void moveRight() {
        position++;
    }

    public Item findItem(List<Item> items) {
        return items.get(position);
    }

    public int getPosition() {
        return position;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(name, player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
