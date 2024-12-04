package com.spring.snapshot.core.member.infrastructure;

import com.spring.snapshot.core.member.domain.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.NaturalId;

@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity(name = "\"member\"")
@DynamicInsert
@DynamicUpdate
class MemberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NaturalId
    @Column(length = 100)
    private String email;

    private String password;

    @Column(unique = true, length = 30)
    private String nickname;

    private Long lastLoginAt;

    private String imageUrl;

    @Column(nullable = false)
    private Long createdAt;

    @Column(nullable = false)
    private Long updatedAt;

    static MemberEntity from(Member member) {
        return MemberEntity.builder()
            .id(member.id())
            .email(member.email())
            .password(member.password())
            .nickname(member.nickname())
            .lastLoginAt(member.lastLoginAt())
            .imageUrl(member.imageUrl())
            .createdAt(member.createdAt())
            .updatedAt(member.createdAt())
            .build();
    }

    Member toMember() {
        return Member.builder()
            .id(id)
            .email(email)
            .password(password)
            .nickname(nickname)
            .lastLoginAt(lastLoginAt)
            .imageUrl(imageUrl)
            .createdAt(createdAt)
            .updatedAt(updatedAt)
            .build();
    }

}
