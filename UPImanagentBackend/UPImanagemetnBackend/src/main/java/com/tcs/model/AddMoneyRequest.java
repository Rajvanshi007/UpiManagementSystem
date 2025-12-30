package com.tcs.model;

public class AddMoneyRequest {
    private Long accountId;
    private double amount;
    private CardDetails card;
	public Long getAccountId() {
		return accountId;
	}
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public CardDetails getCard() {
		return card;
	}
	public void setCard(CardDetails card) {
		this.card = card;
	}

    // getters & setters
}
