package common;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class VariableEvaluatorTest {

	@Test
	public void detectsEmptyDataStructures() {
		assertTrue(VariableEvaluator.isEmpty(null));
		assertTrue(VariableEvaluator.isEmpty(""));
		assertTrue(VariableEvaluator.isEmpty(new ArrayList<>()));
		assertTrue(VariableEvaluator.isEmpty(new HashMap<>()));
	}

	@Test
	public void detectsNotEmptyDataStructures() {
		assertFalse(VariableEvaluator.isEmpty(new Object()));
		assertFalse(VariableEvaluator.isEmpty("a"));
		assertFalse(VariableEvaluator.isEmpty(Arrays.asList(new Object())));

		Map<String, Object> map = new HashMap<>();
		map.put("a", new Object());
		assertFalse(VariableEvaluator.isEmpty(map));
	}
}