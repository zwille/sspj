
package sspj.ue1;

/**
 *
 * @author ccwelich
 */
public abstract class Entity<T extends Entity> implements Comparable<T> {
	protected final int id;

	public Entity(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	@Override
	public int compareTo(T o) {
		return id - o.id;
	}

	@Override
	public int hashCode() {
		return id;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Entity<T> other = (Entity<T>) obj;
		return (this.id == other.id);
	}

}
