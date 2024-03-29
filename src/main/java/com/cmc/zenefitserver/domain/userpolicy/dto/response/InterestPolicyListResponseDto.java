package com.cmc.zenefitserver.domain.userpolicy.dto.response;

import com.cmc.zenefitserver.domain.policy.domain.Policy;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@ToString
@Getter
@NoArgsConstructor
@ApiModel(description = "관심 정책 리스트 정보 조회 API response")
public class InterestPolicyListResponseDto {

    @ApiModelProperty(notes = "정책 ID", example = "12")
    private Long policyId;

    @ApiModelProperty(notes = "정책 이름", example = "청년도약계좌")
    private String policyName;

    @ApiModelProperty(notes = "정책 소개", example = "월 70만원을 5년 납입하면 약 5,000만원을 적립할 수 있는 청년도약계좌")
    private String policyIntroduction;

    @ApiModelProperty(notes = "정책 로고", example = "기관 로고 url")
    private String policyLogo;

    @ApiModelProperty(notes = "정책 신청 마감일", example = "2023-12-30")
    private LocalDate applyEndDate;

    @Builder
    public InterestPolicyListResponseDto(Long policyId, String policyName, String policyIntroduction, String policyLogo, LocalDate applyEndDate) {
        this.policyId = policyId;
        this.policyName = policyName;
        this.policyIntroduction = policyIntroduction;
        this.policyLogo = policyLogo;
        this.applyEndDate = applyEndDate;
    }

    public static InterestPolicyListResponseDto of(Policy policy) {
        return InterestPolicyListResponseDto.builder()
                .policyId(policy.getId())
                .policyName(policy.getPolicyName())
                .policyLogo(policy.getPolicyLogo())
                .policyIntroduction(policy.getPolicyIntroduction())
                .applyEndDate(policy.getApplyEndDate())
                .build();
    }
}
