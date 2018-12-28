package common;

import java.util.Collection;
import java.util.Map;

public class VariableEvaluator {

	public static boolean isEmpty(Object value) {
		if (value == null) {
			return true;
		} else {
			if (value instanceof String) {
				return ((String) value).trim().length() == 0;
			} else if (value instanceof Collection) {
				return ((Collection<?>) value).size() == 0;
			} else if (value instanceof Map) {
				return ((Map<?, ?>) value).isEmpty();
			} else {
				return false;
			}
		}
	}

}
