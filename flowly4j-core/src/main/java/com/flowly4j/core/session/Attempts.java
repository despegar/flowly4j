package com.flowly4j.core.session;

import io.vavr.control.Option;

import java.time.Instant;
import java.util.Objects;

public class Attempts {

    private final Integer quantity;

    private final Instant firstAttempt;

    private final Option<Instant> nextRetry;

    public Attempts(Integer quantity, Instant firstAttempt, Option<Instant> nextRetry) {
        this.quantity = quantity;
        this.firstAttempt = firstAttempt;
        this.nextRetry = nextRetry;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Instant getFirstAttempt() {
        return firstAttempt;
    }

    public Option<Instant> getNextRetry() {
        return nextRetry;
    }

    public Attempts newAttempt() {
        return new Attempts(quantity + 1, firstAttempt, nextRetry);
    }

    public Attempts stopRetrying() {
        return new Attempts(quantity, firstAttempt, Option.none());
    }

    public Attempts withNextRetry(Instant nextRetry) {
        return new Attempts(quantity, firstAttempt, Option.of(nextRetry));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Attempts attempts = (Attempts) o;
        return Objects.equals(quantity, attempts.quantity) &&
                Objects.equals(firstAttempt, attempts.firstAttempt) &&
                Objects.equals(nextRetry, attempts.nextRetry);
    }

    @Override
    public int hashCode() {
        return Objects.hash(quantity, firstAttempt, nextRetry);
    }

    @Override
    public String toString() {
        return "Attempts{" +
                "quantity=" + quantity +
                ", firstAttempt=" + firstAttempt +
                ", nextRetry=" + nextRetry +
                '}';
    }

}
