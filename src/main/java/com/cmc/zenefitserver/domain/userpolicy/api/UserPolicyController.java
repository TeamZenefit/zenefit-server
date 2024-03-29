package com.cmc.zenefitserver.domain.userpolicy.api;

import com.cmc.zenefitserver.domain.user.domain.User;
import com.cmc.zenefitserver.domain.userpolicy.application.UserPolicyService;
import com.cmc.zenefitserver.domain.userpolicy.dto.response.ApplyPolicyListResponseDto;
import com.cmc.zenefitserver.domain.userpolicy.dto.response.InterestPolicyListResponseDto;
import com.cmc.zenefitserver.domain.userpolicy.dto.response.PolicySizeResponseDto;
import com.cmc.zenefitserver.global.annotation.AuthUser;
import com.cmc.zenefitserver.global.common.CommonResponse;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Api(tags = "4. user-policy", description = "interest policy or apply(benefit) policy API")
@RequestMapping("/user/policy")
@RestController
public class UserPolicyController {

    private final UserPolicyService userPolicyService;

    @PostMapping("{policyId}")
    @Operation(summary = "관심 정책 저장 API", description = "유저가 관심 정책을 등록할 때 사용합니다.")
    public CommonResponse<String> saveInterestPolicy(@AuthUser User user, @PathVariable Long policyId) {
        userPolicyService.saveInterestPolicy(user, policyId);
        return CommonResponse.success(null);
    }

    @PostMapping("/apply/{policyId}")
    @Operation(summary = "수혜 정책 저장 API", description = "유저가 정책을 신청할 때 사용합니다.")
    public CommonResponse<String> saveApplyPolicy(@AuthUser User user, @PathVariable Long policyId) {
        userPolicyService.saveApplyPolicy(user, policyId);
        return CommonResponse.success(null);
    }

//    @GetMapping
//    @Operation(summary = "관심 정책 리스트 조회 API",description = "유저의 관심 정책 리스트를 보여줄 때 사용합니다.")
//    public CommonResponse<List<InterestPolicyListResponseDto>> getInterestPolices(@AuthUser User user){
//        List<InterestPolicyListResponseDto> result= userPolicyService.getInterestPolicyList(user);
//        return CommonResponse.success(result);
//    }

//    @GetMapping("/apply")
//    @Operation(summary = "수혜(신청) 정책 리스트 조회 API",description = "유저의 수혜(신청) 정책 리스트를 보여줄 때 사용합니다.")
//    public CommonResponse<List<ApplyPolicyListResponseDto>> getApplyPolices(@AuthUser User user){
//        List<ApplyPolicyListResponseDto> result= userPolicyService.getApplyPolicyList(user);
//        return CommonResponse.success(result);
//    }

    @GetMapping
    @Operation(summary = "관심 정책 리스트 조회 API", description = "유저의 관심 정책 리스트를 보여줄 때 사용합니다.")
    public CommonResponse<Page<InterestPolicyListResponseDto>> getInterestPolicesByPaging(@AuthUser User user, @RequestParam int page, @RequestParam int size) {
        Page<InterestPolicyListResponseDto> result = userPolicyService.getUserPoliciesByInterestFlag(user, true, page, size);
        return CommonResponse.success(result);
    }

    @GetMapping("/apply")
    @Operation(summary = "수혜(신청) 정책 리스트 조회 API", description = "유저의 수혜(신청) 정책 리스트를 보여줄 때 사용합니다.")
    public CommonResponse<Page<ApplyPolicyListResponseDto>> getApplyPolicesByPaging(@AuthUser User user, @RequestParam int page, @RequestParam int size) {
        Page<ApplyPolicyListResponseDto> result = userPolicyService.getUserPoliciesByApplyFlag(user, true, page, size);
        return CommonResponse.success(result);
    }

    @DeleteMapping("{policyId}")
    @Operation(summary = "관심 정책 삭제 API", description = "유저가 관심 정책을 삭제할 때 사용합니다.")
    public CommonResponse<String> deleteInterestPolicy(@AuthUser User user, @PathVariable Long policyId) {
        userPolicyService.deleteInterestPolicy(user, policyId);
        return CommonResponse.success(null);
    }

    @DeleteMapping("/apply/{policyId}")
    @Operation(summary = "수혜(신청) 정책 삭제 API", description = "유저가 신청 정책을 삭제할 때 사용합니다.")
    public CommonResponse<String> deleteApplyPolicy(@AuthUser User user, @PathVariable Long policyId) {
        userPolicyService.deleteApplyPolicy(user, policyId);
        return CommonResponse.success(null);
    }

    @DeleteMapping("/all")
    @Operation(summary = "총 관심 정책 삭제 API", description = "해당 유저의 총 관심 정책을 삭제할 때 사용합니다.")
    public CommonResponse<String> deleteAllInterestPolicy(@AuthUser User user) {
        userPolicyService.deleteAllInterestPolicy(user);
        return CommonResponse.success(null);
    }

    @DeleteMapping("/apply/all")
    @Operation(summary = "총 수혜(신청) 정책 삭제 API", description = "해당 유저의 총 신청 정책을 삭제할 때 사용합니다.")
    public CommonResponse<String> deleteAllApplyPolicy(@AuthUser User user) {
        userPolicyService.deleteAllApplyPolicy(user);
        return CommonResponse.success(null);
    }

    @GetMapping("/size")
    @Operation(summary = "총 관심 정책의 크기 조회 API", description = "해당 유저가 관심 신청 정책 크기를 조회할 때 사용합니다.")
    public CommonResponse<PolicySizeResponseDto> getAllInterestPolicySize(@AuthUser User user) {
        PolicySizeResponseDto result = userPolicyService.getAllInterestPolicySize(user);
        return CommonResponse.success(result);
    }

    @GetMapping("/apply/size")
    @Operation(summary = "총 수혜(신청) 정책 크기 조회 API", description = "해당 유저가 수혜하는 정책 크기를 조회할 때 사용합니다.")
    public CommonResponse<PolicySizeResponseDto> getAllApplyPolicySize(@AuthUser User user) {
        PolicySizeResponseDto result = userPolicyService.getAllApplyPolicySize(user);
        return CommonResponse.success(result);
    }
}

