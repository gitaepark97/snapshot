package com.spring.snapshot.core.member.web;

import com.spring.snapshot.core.member.application.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/members")
class MemberController {

    private final MemberService memberService;

    @GetMapping("/my")
    MyMemberResponse getMyMember(@AuthenticationPrincipal Long memberId) {
        return MyMemberResponse.from(memberService.getMember(memberId));
    }

    @PutMapping("/my")
    MyMemberResponse updateMyMember(
        @AuthenticationPrincipal Long memberId,
        @RequestBody @Valid UpdateMemberRequest request
    ) {
        return MyMemberResponse.from(memberService.updateMember(memberId, request.nickname(), request.imageUrl()));
    }

    @GetMapping("/{memberId}")
    MemberResponse getMember(@PathVariable Long memberId) {
        return MemberResponse.from(memberService.getMember(memberId));
    }

}
