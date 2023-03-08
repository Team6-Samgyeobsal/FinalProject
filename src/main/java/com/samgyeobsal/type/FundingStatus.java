package com.samgyeobsal.type;

public enum FundingStatus {
    PREPARING, // 정보 기입 중
    PARTICIPATE, // 제출 누르고 경연에 참여
    FUNDING, // 펀딩 경연 중
    STORE, // 펀딩 승리 시 스토어로 넘어감
    FAIL, // 펀딩 경연 실패
    END // 스토어 기간 종료 시
}
