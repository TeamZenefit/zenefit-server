package com.cmc.zenefitserver.domain.userpolicy.domain;

import com.cmc.zenefitserver.domain.policy.domain.Policy;
import com.cmc.zenefitserver.domain.user.domain.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserPolicy {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "policy_id")
    private Policy policy;

    @Enumerated(EnumType.STRING)
    private UserPolicyType userPolicyType;

    @Builder
    public UserPolicy(User user, Policy policy,UserPolicyType userPolicyType){
        this.user = user;
        this.policy = policy;
        this.userPolicyType = userPolicyType;
    }

    public void updateUserPolicy(User user, Policy policy){
        updateUser(user);
        updatePolicy(policy);
    }
    public void updateUser(User user){
        this.user = user;
    }

    public void updatePolicy(Policy policy){
        this.policy = policy;
    }

    public void updatePolicyType(UserPolicyType userPolicyType){
        this.userPolicyType = userPolicyType;
    }


}
