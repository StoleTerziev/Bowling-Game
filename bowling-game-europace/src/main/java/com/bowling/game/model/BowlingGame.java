package com.bowling.game.model;

public class BowlingGame {

	private int currFrame;

	private Frame[] frames;

	public BowlingGame() {
		currFrame = 1;
		frames = new Frame[11];
	}

	public int getCurrFrame() {
		return currFrame;
	}

	public void setCurrFrame(int currFrame) {
		this.currFrame = currFrame;
	}

	public Frame[] getFrames() {
		return frames;
	}

	public void setFrames(Frame[] frames) {
		this.frames = frames;
	}

	public Frame getFrame(int i) {
		return frames[i];
	}

	private int spareBonus(int frameNo) {
		if (frameNo == 10) {
			Frame lastFrame = frames[frameNo];
			return lastFrame.getThirdRoll();
		}
		Frame nextFrame = frames[frameNo + 1];
		return nextFrame == null ? -1 : nextFrame.getFirstRoll();
	}

	private int strikeBonus(int frameNo) {
        if (frameNo == 10) {
            Frame tenthFrame = frames[frameNo];
            return tenthFrame.getSecondRoll() + tenthFrame.getThirdRoll();
        }
        Frame nextFrame = frames[frameNo + 1];
        if (nextFrame == null) {
            return -1;
        }
        if (nextFrame.isStrike()) {
            if (frameNo == 9) {
                return 10 + nextFrame.getSecondRoll(); // next frame is the last frame which is special
            }
            Frame nextNextFrame = frames[frameNo + 2];
            if(nextNextFrame==null)
            	return -1;
            else 
            	return 10 + nextNextFrame.getFirstRoll();
            
        }
        return nextFrame.getFirstRoll() + nextFrame.getSecondRoll();
    }

	public int frameScore(int frameNo) {
		Frame frame = frames[frameNo];
        if (frame == null) {
            return 0;
        }
        if (frame.isStrike()) {
            if (strikeBonus(frameNo) == -1) { // Waiting for next frame(s) to be scored
                frame.setIsScored(false);
                return 0;
            }
            frame.setIsScored(true);
            return 10 + strikeBonus(frameNo);
        }
        if (frame.isSpare()) {
            if (spareBonus(frameNo) == -1) { // waiting for next frame to be scored
                frame.setIsScored(false);
                return 0;
            }
            frame.setIsScored(true);
            return 10 + spareBonus(frameNo);
        }
        frame.setIsScored(true);
        return frame.getFirstRoll() + frame.getSecondRoll();

	}
}
