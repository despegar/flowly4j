package com.flowly4j.jpa;

import com.flowly4j.core.session.Attempts;
import io.vavr.control.Option;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.NoArgsConstructor;

import java.time.Instant;

@NoArgsConstructor
@Embeddable
public class AttemptsWrapper {

    @Column(name = "ATTEMPS_QUANTITY")
    private Integer quantity;

    @Column(name = "ATTEMPS_FIRST_ATTEMPT")
    private Instant firstAttempt;

    @Column(name = "ATTEMPS_NEXT_RETRY")
    private Instant nextRetry;

    public AttemptsWrapper(Attempts attempts) {
        this.quantity = attempts.getQuantity();
        this.firstAttempt = attempts.getFirstAttempt();
        this.nextRetry = attempts.getNextRetry().getOrNull();
    }

    public Attempts toAttempts() {
        return new Attempts(quantity, firstAttempt, Option.of(nextRetry));
    }

}

