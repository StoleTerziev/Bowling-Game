package com.bowling.game.model;

public class Frame {
	
	private int firstRoll;
	
	private int secondRoll;
	
	private int thirdRoll;//refers to the last frame --> a player who has a spare or a strike
	
	private Boolean isScored;

	public Frame() {
		firstRoll = 0;
		secondRoll = 0;
		thirdRoll = 0;
		isScored = false;
	}

	public int getFirstRoll() {
		return firstRoll;
	}

	public void setFirstRoll(int firstRoll) {
		this.firstRoll = firstRoll;
	}

	public int getSecondRoll() {
		return secondRoll;
	}

	public void setSecondRoll(int secondRoll) {
		this.secondRoll = secondRoll;
	}

	public int getThirdRoll() {
		return thirdRoll;
	}

	public void setThirdRoll(int thirdRoll) {
		this.thirdRoll = thirdRoll;
	}

	public Boolean getIsScored() {
		return isScored;
	}

	public void setIsScored(Boolean isScored) {
		this.isScored = isScored;
	}

	public Boolean isStrike() {
		return firstRoll == 10;
	}
	
	public Boolean isSpare() {
		return firstRoll + secondRoll == 10;
	}
	
	
	
	
	
}
