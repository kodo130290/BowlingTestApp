package main.java.solutions.infinity.task.test.dovgan.model.entities;

import main.java.solutions.infinity.task.test.dovgan.model.utils.Constants;

public class Frame {

	private Integer roll1;
	private Integer roll2;
	private Integer extraRoll;
	private boolean isLastFrame;

	public Frame(Integer pins, boolean isLastFrame){
		this.roll1 = pins;
		this.isLastFrame = isLastFrame;
		this.roll2 = Constants.NOPINS.value();
		this.extraRoll = Constants.NOPINS.value();
	}
	
	public boolean isStrike(){
			if (roll1 == 10){
				return true;
			}
		return false;
	}

	public boolean isSpare(){
		if (Constants.NOPINS.value() != roll1  && Constants.NOPINS.value() != roll2 ){
			return roll1 + roll2 == 10;
		}else {
			return false;
		}
	}

	public String displayValue(){
		if(isLastFrame){
			if (isStrike()){
				return new StringBuffer().append("X")
										 .append((Constants.NOPINS.value() == roll2) ? Constants.NOPINS.toString() : (roll2==10) ? "X":roll2)
										 .append((Constants.NOPINS.value() == extraRoll ) ? Constants.NOPINS.toString() : 
											 (extraRoll==10) ? "X":( extraRoll != 0 && roll2 + extraRoll == 10) ? "/":extraRoll).toString();
			}else if (isSpare()){
				return new StringBuffer().append(roll1)
										 .append("/")
										 .append((Constants.NOPINS.value() == extraRoll ) ? Constants.NOPINS.toString() : 
											 (extraRoll==10) ? "X": extraRoll).toString();
			}
		}
		if(isStrike()){
			return "X";
		}
		if(isSpare()){
			return roll1 + "/";
		}
		return new StringBuffer().append(roll1 )
								 .append("-")
								 .append((roll2 == Constants.NOPINS.value()) ? Constants.NOPINS.toString() : roll2).toString();
	}

	public Integer getRoll1() {
		return roll1;
	}

	public Integer getRoll2() {
		return roll2;
	}

	public void setRoll2(Integer roll2) {
		this.roll2 = roll2;
	}

	public Integer getExtraRoll() {
		return extraRoll;
	}

	public void setExtraRoll(Integer extraRoll) {
		this.extraRoll = extraRoll;
	}

	public boolean isLastFrame() {
		return isLastFrame;
	}


}
