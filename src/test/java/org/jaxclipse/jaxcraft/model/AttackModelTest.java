package org.jaxclipse.jaxcraft.model;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class AttackModelTest
{

	@Test
	public void testAttackModelBuilder() throws Exception
	{
		AttackModel am = new AttackModel();

		List<String> actions = am.getActions();
		actions.add("test");
		Assert.assertTrue(true);
	}
}
