package sspj.ue1;

/**
 *
 * @author ccwelich
 */
public class Result implements Comparable<Result> {
	
	private final Match parent;
	private final ResultType type;
	private final int orderId;
	private final int points2;
	private final int points1;
	private final String name;

	public Result(
		Match parent,
		ResultType type,
		int orderId,
		int points1,
		int points2,
		String name) {
		this.parent = parent;
		this.type = type;
		this.orderId = orderId;
		this.points2 = points2;
		this.points1 = points1;
		this.name = name;
	}



	@Override
	public String toString() {
		return "Result{" + "id=" + parent.getId() + "." + orderId +", type=" + type  + ", points2=" + points2 + ", points1=" + points1 + ", name=" + name + '}';
	}

	@Override
	public int compareTo(Result o) {
		int rc = parent.compareTo(o.parent);
		return (rc==0) 
			? orderId - o.orderId
			: rc;
	}
	
	
	public Match getParent() {
		return parent;
	}

	
	
	public ResultType getType() {
		return type;
	}

	public int getOrderId() {
		return orderId;
	}

	public int getPoints2() {
		return points2;
	}

	public int getPoints1() {
		return points1;
	}

	public String getName() {
		return name;
	}
}
