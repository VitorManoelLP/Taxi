package com.taxi.app.application.usecase;

import java.io.Serializable;

public interface Payload extends Serializable {
    String getTopic();
}
