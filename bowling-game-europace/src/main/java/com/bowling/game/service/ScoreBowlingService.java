package com.bowling.game.service;


import com.bowling.game.model.Frame;
import com.bowling.game.model.ScoreBowling;

public interface ScoreBowlingService {
	
	public String newGame();
	
	public ScoreBowling getScore(String id);
	
	public Boolean deleteGame(String id);
	
	public Boolean submitScore(Frame frame, String id);
	
	
}
