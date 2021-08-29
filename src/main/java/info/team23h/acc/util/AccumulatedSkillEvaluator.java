package info.team23h.acc.util;

public class AccumulatedSkillEvaluator {
	private double scoreSum = 0;
	private double weightSum = 0;
	private final double eventPeroid = 10;
	private final boolean[] participated = {false, false, false, false, false, false, false, false, false, false,false};


	public void pushData(int eventID,
						 int rank,
						 int participantNo) {
		assert (eventID >= 1 && eventID < 12);
		assert (rank > 0 && rank <= participantNo);
		assert (participantNo > 0 && participantNo < 10000);

		if(eventID == 0){
			participantNo = 60;
		}
		participated[eventID] = true;

		//        final double weight = eventPeroid-eventID;
		final double weight = ((eventPeroid - eventID) / eventPeroid + (Math.cos(eventID / eventPeroid * Math.PI) + 1) / 2) / 2;

		rank--;
		participantNo--;
		weightSum += weight;
		scoreSum += weight * rank / participantNo;


	}

	public double getScore() {
		int penalty = 0;
		if(!participated[0]){
			for(int i = 1; i < eventPeroid && !participated[i]; i++){
				penalty++;
			}
		}
		final double v = scoreSum / weightSum;
		double finalScore;
		if(!Double.isNaN(v)){
			finalScore = (scoreSum / weightSum) * 100.0;
		}else{
			finalScore = 0;
		}
		finalScore = (100 - (100 - finalScore) * (9 - penalty) / 9);
		return Math.max(0, Math.min(finalScore, 100));
	}
}