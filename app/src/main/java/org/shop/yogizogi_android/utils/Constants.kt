package org.shop.yogizogi_android.utils

enum class ActiveState {
    ACTIVE,
    INACTIVE
}

enum class SuccessState {
    SUCCESS,
    FAIL
}

enum class PassState {
    PASS,
    NOT_PASS
}

enum class ExistState {
    EXIST,
    NOT_EXIST
}

enum class Moods(val korean: String) {
    SOLO("혼자"),
    WITH_LOVER("애인과 함께"),
    WITH_FRIENDS("친구들과 함께"),
    WITH_PARENT("부모님과 함께"),
    WITH_CHILD("자녀와 함께"),
    WITH_COLLEAGUE("동료와 함께"),
    LIGHT_MEAL("가벼운 식사"),
    GOURMET_MEAL("고급 식사"),
    PAIRING_MEAL("매칭된 식사"),
    BUSINESS_MEETING("업무 회의"),
    GROUP_MEETING("그룹 회의"),
    ANNIVERSARY("기념일")
}