package com.cmc.zenefitserver.domain.user.domain;

import lombok.Getter;

import java.util.Arrays;
import java.util.function.Predicate;

@Getter
public enum Character {

    NO("노관심", "혜택을 잘 챙겨서 친구들을 앞서봐요!", 100, sumPolicyCount -> sumPolicyCount >= 0 && sumPolicyCount <= 3),
    NEW("뉴비", "아직은 상위 60%지만 더 노력해봐요!", 60, sumPolicyCount -> sumPolicyCount >= 4 && sumPolicyCount <= 9),
    SAVE("저금왕", "또래 친구들 중 상위 30%로 똑부러지는 편이네요", 30, sumPolicyCount -> sumPolicyCount >= 10 && sumPolicyCount <= 12),
    SMART("똑똑이", "또래 친구들 중 상위 5%로 앞서 가고 있어요", 5, sumPolicyCount -> sumPolicyCount >= 13);


    private final String name;
    private final String description;
    private final Predicate<Integer> expression;
    private final int percent;

    Character(String name, String description, int percent, Predicate<Integer> expression) {
        this.name = name;
        this.description = description;
        this.percent = percent;
        this.expression = expression;
    }

    public static Character getCharacter(int sumPolicyCount) {
        return Arrays.stream(Character.values())
                .filter(character -> character.expression.test(sumPolicyCount))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("정책의 총 갯수가 음수 값이 올 수 없습니다."));
    }
}
