package com.pzfomar.vergil.application.account.dto;

import lombok.Builder;
import lombok.Getter;

public class RegisterEmailDto {
    @Getter
    public static class Request {
        private String email;
        private String password;
        private String confirmPassword;
        private TermCondition termCondition;
        private Advertising advertising;
    }

    @Builder
    public static class Response {
    }

    @Getter
    public class TermCondition {
        private String id;
        private boolean confirm;
    }

    @Getter
    public class Advertising {
        private String id;
        private boolean confirm;
    }
}
