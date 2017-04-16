package main.java.solutions.infinity.task.test.dovgan.model.utils;

public enum Constants {
	NOPINS (null){
		@Override
		 public String toString() {
			return "";
		}
	},
	
	NORESULTS (null) {
		@Override
		 public String toString() {
			return "";
		}
	};
	
	private Integer value;

    private Constants(Integer value) {
            this.value = value;
    }
    
    public Integer value(){
    	return value;
    }
    
    public static final String GAME_CONTROLLER= "bowling/gameController";
    public static final String RESULT_MESSAGE_NON_FINISHED = "Results: ";
    public static final String RESULT_MESSAGE_FINISHED_CAME = "Final results: ";
    public static final String WARN_MESSAGE_PINS_NUMBER = "Please set correct number of pins!!!";
    public static final String ATTR_RESULTS_BY_FRAMES = "resultsByFrames";
    public static final String ATTR_FRAMES_TO_DISPLAY = "framesToDisplay";
    public static final String ATTR_RESULT_MESSAGE = "resultMessage";
    public static final String ATTR_WARNING = "warn";
    public static final String ATTR_NUMBER_OF_PINS = "pins";
    public static final String ATTR_TEXT_FIELD_HIDDEN = "isTextFieldHidden";
    public static final String ATTR_GAME_ID = "gameId";
	
}
