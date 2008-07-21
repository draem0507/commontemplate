package org.commontemplate.standard.operator.string;

/**
 * 名称对
 *
 * @author liangfei0201@163.com
 *
 */
public class NamePair {

	private final String leftName;

	private final String rightName;

	public NamePair(String leftName, String rightName) {
		super();
		this.leftName = leftName;
		this.rightName = rightName;
	}

	public String getLeftName() {
		return leftName;
	}

	public String getRightName() {
		return rightName;
	}

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((leftName == null) ? 0 : leftName.hashCode());
		result = prime * result
				+ ((rightName == null) ? 0 : rightName.hashCode());
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final NamePair other = (NamePair) obj;
		if (leftName == null) {
			if (other.leftName != null)
				return false;
		} else if (!leftName.equals(other.leftName))
			return false;
		if (rightName == null) {
			if (other.rightName != null)
				return false;
		} else if (!rightName.equals(other.rightName))
			return false;
		return true;
	}

}
