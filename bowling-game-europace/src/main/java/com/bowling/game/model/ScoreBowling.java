package com.bowling.game.model;

public class ScoreBowling {
	
	private int result;
	private int totalFrameScores[];
	
	public ScoreBowling() {}
	
	public ScoreBowling(int result, int[] totalFrameScores) {
		this.result = result;
		this.totalFrameScores = totalFrameScores;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public int[] getTotalFrameScores() {
		return totalFrameScores;
	}

	public void setTotalFrameScores(int[] totalFrameScores) {
		this.totalFrameScores = totalFrameScores;
	}
	
	
	
	
}
