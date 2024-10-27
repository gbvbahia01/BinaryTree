package br.com.gbvbahia.collection;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BinaryTreeValueImpl implements BinaryTreeValue<BinaryTreeValueImpl> {

	private static final long serialVersionUID = 1L;
	
	private Integer someValue;
	
	@Override
	public int compareTo(BinaryTreeValueImpl o) {
		if (someValue == null) {
			return -1;
		}
		
		if (o.someValue == null) {
			return 1;
		}
		
		return someValue.compareTo(o.someValue);
	}

	@Override
	public String toString() {
		return "(" + someValue + ")";
	}
	
}
