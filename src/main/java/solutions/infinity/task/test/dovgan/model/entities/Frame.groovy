package main.java.solutions.infinity.task.test.dovgan.model.entities

import main.java.solutions.infinity.task.test.dovgan.model.utils.Constants;

class Frame {
	def roll1
	def roll2
	def extraRoll
	def isLastFrame

	Frame(pins, isLastFrame){
		this.roll1 = pins
		this.isLastFrame = isLastFrame
		this.roll2 = Constants.NOPINS
		this.extraRoll = Constants.NOPINS
	}
	
	def isStrike(){
			if (roll1 == 10){
				return true
			}
		return false
	}
	
	def isSpare(){
		if (roll1 != Constants.NOPINS  && roll2 != Constants.NOPINS ){
			return roll1 + roll2 == 10
		}else {
			return false
		}
	}
	
	def displayValue(){
		if(isLastFrame){
			if (isStrike()){
				return new StringBuffer().append("X")
										 .append((roll2==10) ? "X":roll2)
										 .append((extraRoll==10) ? "X": 
											 (extraRoll != Constants.NOPINS && roll2 + extraRoll == 10) ? "/":extraRoll).toString()
			}else if (isSpare()){
				return new StringBuffer().append(roll1)
										 .append("/")
										 .append((extraRoll==10) ? "X": extraRoll).toString()
			}
		}
		if(isStrike()){
			return "X"
		}
		if(isSpare()){
			return roll1 + "/"
		}
		return new StringBuffer().append(roll1 )
								 .append("-" + roll2).toString()
	}
	
}
