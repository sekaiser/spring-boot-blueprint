package de.seka.news;

import de.seka.news.test.categories.UnitTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;

@Category(UnitTest.class)
public class NewsApplicationUnitTests {

	@Test
	public void canConstruct() {
		assertThat(new NewsApplication(), notNullValue());
	}

}

