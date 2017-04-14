package main.java.solutions.infinity.task.test.dovgan.model.entities;

import main.java.solutions.infinity.task.test.dovgan.model.utils.Constants;

import java.util.ArrayList;
import java.util.Collections;

public class Game {
	private ArrayList<Frame> frameList = new ArrayList<Frame>();
	private Frame currentFrame = null;
	private int currentFrameNumber = 0;
	private boolean isGameFinished = false;
	private int totalScore = 0;

	public void addRoll(Integer pins){
		if ( isGameFinished ){
			return;
		}

		if ( currentFrame == null ){
			currentFrameNumber++;
			currentFrame = new Frame(pins, (currentFrameNumber == 10));
			frameList.add(currentFrame);
			if (currentFrame.isStrike() && currentFrameNumber != 10){
				currentFrame.setRoll2(0);
				currentFrame = null;
			}
		}else if ( !currentFrame.isLastFrame() ){
			currentFrame.setRoll2(pins);
			currentFrame = null;
		}else if (currentFrame.getRoll2() == Constants.NOPINS.value()){
			currentFrame.setRoll2(pins);
			if (!currentFrame.isSpare() && !currentFrame.isStrike() ){
				isGameFinished = true;
			}
		}else{
			currentFrame.setExtraRoll(pins);
			isGameFinished = true;
		}		
	}

	public ArrayList<String> calculateResultsByFrames(){
		ArrayList<String> resultsByFrame = new ArrayList<String>();
		Integer score = 0;
		for (int frameNum = 0; frameNum < frameList.size(); frameNum++){
			Frame frame = frameList.get(frameNum);
			Integer frameResult;
			if (frame.isStrike()){
				frameResult = getStrikeBonus(frameNum);
			}else if (frame.isSpare()){
				frameResult = getSpareBonus(frameNum);
			}else{
				frameResult = getSumOfPinsInFrame(frameNum);
			}
			if (frameResult == Constants.NORESULTS.value()){
				resultsByFrame.add(Constants.NORESULTS.toString());
				continue;
			}
			score += frameResult;
			resultsByFrame.add(score.toString());
		}
		totalScore = score;
		resultsByFrame.addAll(Collections.nCopies(10 - resultsByFrame.size(), Constants.NOPINS.toString()));
		return resultsByFrame;
	}
	
	private Integer getStrikeBonus(int frameNum){
		if (frameList.get(frameNum).isLastFrame() && Constants.NOPINS.value() != frameList.get(frameNum).getRoll2()
			&& Constants.NOPINS.value() != frameList.get(frameNum).getExtraRoll() )	{
			return 10 + frameList.get(frameNum).getRoll2() + frameList.get(frameNum).getExtraRoll();
		}
			
		if (isFrameCreated(frameNum+1) && Constants.NOPINS.value() != frameList.get(frameNum+1).getRoll1()
			&& Constants.NOPINS.value() != frameList.get(frameNum+1).getRoll2()){
			if (frameList.get(frameNum + 1).isStrike() && !frameList.get(frameNum + 1).isLastFrame()){
				if (isFrameCreated(frameNum+2) && Constants.NOPINS.value() != frameList.get(frameNum+2).getRoll1() ){
					return 10 + frameList.get(frameNum + 1).getRoll1() + frameList.get(frameNum + 2).getRoll1();
				}
			}else {
				return 10 + frameList.get(frameNum + 1).getRoll1() + frameList.get(frameNum + 1).getRoll2();
			}
		}
		return Constants.NORESULTS.value();
	}

	private Integer getSpareBonus(int frameNum){
		if (frameList.get(frameNum).isLastFrame()
				&& Constants.NOPINS.value() != frameList.get(frameNum).getExtraRoll()){
			return frameList.get(frameNum).getRoll1() + frameList.get(frameNum).getRoll2()
					+ frameList.get(frameNum).getExtraRoll();
		}
		
		if (isFrameCreated(frameNum+1)){
			return frameList.get(frameNum).getRoll1() + frameList.get(frameNum).getRoll2()
					+ frameList.get(frameNum + 1).getRoll1();
		}
		
		return Constants.NORESULTS.value();
	}
	
	private boolean isFrameCreated(int frameNum){
		return frameNum <= (frameList.size() - 1);
	}

	private Integer getSumOfPinsInFrame(int frameNum){
		if (Constants.NOPINS.value() != frameList.get(frameNum).getRoll1()
				&& Constants.NOPINS.value() != frameList.get(frameNum).getRoll2() ){
			return frameList.get(frameNum).getRoll1() + frameList.get(frameNum).getRoll2();
		}
		return Constants.NORESULTS.value();
	}
	
	public ArrayList<String> getFramesToDisplay(){
		ArrayList<String> results = new ArrayList<String>();
		for(Frame frame : frameList){
			results.add(frame.displayValue());
		}
		results.addAll(Collections.nCopies(10 - results.size(), Constants.NOPINS.toString()));
		return results;
	}
	
	public boolean isValidNewPins(Integer pins){

		if (currentFrame == null && pins <= 10){
			return true;
		}else if (currentFrame == null){
			return false;
		}
		
		if (currentFrame != null && currentFrame.isLastFrame()){
			if (Constants.NOPINS.value() == currentFrame.getRoll2() &&
						((currentFrame.isStrike() && pins <= 10 ) 
							|| (currentFrame.getRoll1() + pins) <= 10)  ){
				return true;
			} else if (Constants.NOPINS.value() == currentFrame.getRoll2()){
				return false;
			}
			if (Constants.NOPINS.value() == currentFrame.getExtraRoll() 
					&& Constants.NOPINS.value() != currentFrame.getRoll2() 
					&& (((currentFrame.getRoll2() == 10 || currentFrame.isSpare()) && pins <= 10 )
							|| (currentFrame.getRoll2() + pins) <= 10)  ){
				return true;
			} else if (Constants.NOPINS.value() == currentFrame.getExtraRoll()){
				return false;
			}
		}
		
		if (currentFrame != null){
			if ((currentFrame.getRoll1() + pins) <= 10 ){
				return true;
			}
		}
		return false;
	}

	public boolean isGameFinished() {
		return isGameFinished;
	}

	public int getTotalScore() {
		return totalScore;
	}
}
