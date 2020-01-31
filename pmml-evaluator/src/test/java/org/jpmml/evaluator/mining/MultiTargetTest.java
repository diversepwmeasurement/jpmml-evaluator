/*
 * Copyright (c) 2020 Villu Ruusmann
 *
 * This file is part of JPMML-Evaluator
 *
 * JPMML-Evaluator is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * JPMML-Evaluator is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with JPMML-Evaluator.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.jpmml.evaluator.mining;

import java.util.Arrays;
import java.util.Map;

import org.dmg.pmml.FieldName;
import org.jpmml.evaluator.Evaluator;
import org.jpmml.evaluator.ModelEvaluatorTest;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class MultiTargetTest extends ModelEvaluatorTest {

	@Test
	public void evaluate() throws Exception {
		Evaluator evaluator = createModelEvaluator();

		checkResultFields(Arrays.asList("y1", "y2"), Arrays.asList("decision"), evaluator);

		Map<FieldName, ?> arguments = createArguments("x", -1.0d);

		Map<FieldName, ?> results = evaluator.evaluate(arguments);

		assertNotNull(getTarget(results, "y1"));
		assertNull(getTarget(results, "y2"));

		assertEquals(0, getOutput(results, "decision"));

		arguments = createArguments("x", 1.0d);

		results = evaluator.evaluate(arguments);

		assertNull(getTarget(results, "y1"));
		assertNotNull(getTarget(results, "y2"));

		assertEquals(1, getOutput(results, "decision"));
	}
}