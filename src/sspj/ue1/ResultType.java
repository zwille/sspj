
package sspj.ue1;

/**
 *
 * @author ccwelich
 */
public class ResultType{
	private final int id;
	private final String name;


	public ResultType(int id, String name) {
		this.id = id;
		this.name = name;
	}

	@Override
	public String toString() {
		return "ResultType{" + "id=" + id + ", name=" + name + '}';
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}


}
