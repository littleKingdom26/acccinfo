package info.team23h.acc.util;

public class AccumulatedSkillEvaluator {
	private double scoreSum = 0;
	private double weightSum = 0;
	private final double eventPeroid = 10;

	public void pushData(int eventID,
						 int rank,
						 int participantNo) {
		assert (eventID >= 0 && eventID < 10);
		assert (rank > 0 && rank <= participantNo);
		assert (participantNo > 0 && participantNo < 10000);
		// final double weight = eventPeroid-eventID;
		final double weight = ((eventPeroid - eventID) / eventPeroid + (Math.cos(eventID / eventPeroid * Math.PI) + 1) / 2) / 2;
		rank--;
		participantNo--;
		weightSum += weight;
		scoreSum += weight * rank / participantNo;

	}

	public double getScore() {
		return (scoreSum / weightSum) * 100.0;
	}
}