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
	

}
