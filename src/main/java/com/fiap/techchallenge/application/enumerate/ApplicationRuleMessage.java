package com.fiap.techchallenge.application.enumerate;

import lombok.Getter;

@Getter
public enum ApplicationRuleMessage {

    ORDER_NOT_FOUND_ERROR("Order with id: [%s] error"),
    PRODUCT_NOT_FOUND_MESSAGE("Product with id: [%s] error");

    private final String message;

    ApplicationRuleMessage(String message) {
        this.message = message;
    }
}
