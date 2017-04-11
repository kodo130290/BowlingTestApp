package main.java.solutions.infinity.task.test.dovgan.model.entities

import main.java.solutions.infinity.task.test.dovgan.model.utils.Constants;

class Game {
	public ArrayList<Frame> frameList = new ArrayList<Frame>()
	def Frame currentFrame = null
	def currentFrameNumber = 0
	def isGameFinished = false
	def totalScore
	
	def addRoll(pins){
		if ( isGameFinished ){
			return
		}	
		if ( currentFrame == null ){
			currentFrameNumber++
			currentFrame = new Frame(pins, (currentFrameNumber == 10))
			frameList.add(currentFrame)
			if (currentFrame.isStrike() && currentFrameNumber != 10){
				currentFrame.roll2 = 0
				currentFrame = null
			}
		}else if ( !currentFrame.isLastFrame ){
			currentFrame.roll2 = pins
			currentFrame = null
		}else if (currentFrame.roll2 == Constants.NOPINS){
			currentFrame.roll2 = pins
			if (!currentFrame.isSpare() && !currentFrame.isStrike() ){
				isGameFinished = true
			}
		}else{
			currentFrame.extraRoll = pins
			isGameFinished = true
		}		
	}
	
	def calculateResultsByFrames(){
		ArrayList<String> resultsByFrame = new ArrayList<String>()
		def score = 0
		for (def frameNum = 0; frameNum < frameList.size(); frameNum++){
			Frame frame = frameList.get(frameNum)
			def frameResult
			if (frame.isStrike()){
				frameResult = getStrikeBonus(frameNum)			
			}else if (frame.isSpare()){
				frameResult = getSpareBonus(frameNum)
			}else{
				frameResult = getSumOfPinsInFrame(frameNum)
			}
			if (frameResult == Constants.NORESULTS){
				resultsByFrame.add(frameResult)
				continue
			}
			score += frameResult
			resultsByFrame.add(score)
		}
		totalScore = score
		resultsByFrame.addAll(Collections.nCopies(10 - resultsByFrame.size(), Constants.NOPINS))
		return resultsByFrame;
	}
	
	def getStrikeBonus(frameNum){
		if (frameList.get(frameNum).isLastFrame && frameList.get(frameNum).roll2 != Constants.NOPINS 
			&&  frameList.get(frameNum).extraRoll != Constants.NOPINS )	{
			return 10 + frameList.get(frameNum).roll2 + frameList.get(frameNum).extraRoll
		}
			
		if (isFrameCreated(frameNum+1) && frameList.get(frameNum + 1).roll1 != Constants.NOPINS  
			&& frameList.get(frameNum + 1).roll2 != Constants.NOPINS ){
			if (frameList.get(frameNum + 1).isStrike() && !frameList.get(frameNum + 1).isLastFrame){
				if (isFrameCreated(frameNum+2) && frameList.get(frameNum + 2).roll1 != Constants.NOPINS  ){
					return 10 + frameList.get(frameNum + 1).roll1 + frameList.get(frameNum + 2).roll1
				}
			}else {
				return 10 + frameList.get(frameNum + 1).roll1 + frameList.get(frameNum + 1).roll2
			}
		}
		return Constants.NORESULTS
	}
	
	def getSpareBonus(frameNum){
		if (frameList.get(frameNum).isLastFrame && frameList.get(frameNum).extraRoll != Constants.NOPINS ){
			return frameList.get(frameNum).roll1 + frameList.get(frameNum).roll2 + frameList.get(frameNum).extraRoll
		}
		
		if (isFrameCreated(frameNum+1)){
			return frameList.get(frameNum).roll1 + frameList.get(frameNum).roll2 + frameList.get(frameNum + 1).roll1
		}
		
		return Constants.NORESULTS
	}
	
	def isFrameCreated(frameNum){
		return frameNum <= (frameList.size() - 1)
	}
	
	def getSumOfPinsInFrame(frameNum){
		if (frameList.get(frameNum).roll1 != Constants.NOPINS  && frameList.get(frameNum).roll2 != Constants.NOPINS ){
			return frameList.get(frameNum).roll1 + frameList.get(frameNum).roll2
		}
		return Constants.NORESULTS
	}
	
	def getFramesToDisplay(){
		ArrayList<String> results = new ArrayList<String>()
		for(frame in frameList){
			results.add(frame.displayValue())
		}
		results.addAll(Collections.nCopies(10 - results.size(), Constants.NOPINS))
		return results
	}
	
	def isValidNewPins(pins){

		if (currentFrame == null && pins <= 10){
			return true
		}else if (currentFrame == null){
			return false
		}
		
		if (currentFrame != null && currentFrame.isLastFrame){
			if (currentFrame.roll2 == Constants.NOPINS && 
						((currentFrame.isStrike() && pins <= 10 ) 
							|| (currentFrame.roll1 + pins) <= 10)  ){
				return true
			} else if (currentFrame.roll2 == Constants.NOPINS){
				return false
			}
			if (currentFrame.extraRoll == Constants.NOPINS && 
						(((currentFrame.roll2 == 10 || currentFrame.isSpare()) && pins <= 10 ) 
							|| (currentFrame.roll2 + pins) <= 10)  ){
				return true
			} else if (currentFrame.extraRoll == Constants.NOPINS){
				return false
			}
		}
		
		if (currentFrame != null){
			if ((currentFrame.roll1 + pins) <= 10 ){
				return true
			}
			return false
		}	
	}
}
