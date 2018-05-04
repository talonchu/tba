package com.perficient.bcten.util;

public enum Status {
	WAITING(0), APPROVED(1), DISAPPROVED(2);
	private final int status;

	private Status(int status) {
		this.status = status;
	}

	public int getValue() {
		return status;
	}

	public static Status getGender(int value) {
		switch (value) {
		case 1:
			return APPROVED;
		case 2:
			return DISAPPROVED;
		default:
			return WAITING;

		}
	}

	public static void main(String args[]) {
		System.out.println(Status.APPROVED);
	}
}
