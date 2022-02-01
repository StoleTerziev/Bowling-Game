package com.bowling.game.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.bowling.game.model.BowlingGame;
import com.bowling.game.model.Frame;
import com.bowling.game.model.ScoreBowling;

@Service
public class ScoreBowlingServiceImpl implements ScoreBowlingService {

	private HashMap<String, BowlingGame> bowlingHashMap = new HashMap<>();

	@Override
	public String newGame() {
		// TODO Auto-generated method stub
		String gameId = UUID.randomUUID().toString();
		while (bowlingHashMap.containsKey(gameId))
			gameId = UUID.randomUUID().toString();

		bowlingHashMap.put(gameId, new BowlingGame());
		return gameId;
	}

	@Override
	public ScoreBowling getScore(String id) {
		// TODO Auto-generated method stub
		if (!bowlingHashMap.containsKey(id)) {
            return null;
        }
        BowlingGame bowlingGame = bowlingHashMap.get(id);
        int currentFrame = bowlingGame.getCurrFrame();
        int totalScore = 0;
        int frameScores[] = new int[10];
        Arrays.fill(frameScores, 0);
        for (int i = 1; i < currentFrame; i++) {
            Frame frame = bowlingGame.getFrame(i);
            int frameScore = bowlingGame.frameScore(i);
            if (frame.getIsScored()) {
                totalScore += frameScore;
                frameScores[i - 1] = totalScore;
            }
        }
        return new ScoreBowling(totalScore, frameScores);	}

	@Override
	public Boolean deleteGame(String id) {
		// TODO Auto-generated method stub
		if (!bowlingHashMap.containsKey(id)) {
			bowlingHashMap.remove(id);
			return true;
		}
		return false;
	}

	@Override
	public Boolean submitScore(Frame frame, String id) {
		// TODO Auto-generated method stub
		if (!bowlingHashMap.containsKey(id)) {
            return false;
        }
        BowlingGame bowlingGame = bowlingHashMap.get(id);
        int currentFrame = bowlingGame.getCurrFrame();
        bowlingGame.getFrames()[currentFrame] = frame;
        bowlingGame.setCurrFrame(currentFrame + 1);
        return true;

	}

}
