package jp.ac.chitose.gishi_yama.page;

import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import jp.ac.chitose.gishi_yama.WicketApplication;
import jp.ac.chitose.gishi_yama.page.HomePage;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = WicketApplication.class)
@WebAppConfiguration
public class HomePageTest {

  private WicketTester tester;

  @Autowired
  private WicketApplication wicketApplication;

  @Before
  public void setUp() {
    tester = new WicketTester(wicketApplication);
  }

  @Test
  public void homepageRendersSuccessfully() {
    // start and render the test page
    tester.startPage(HomePage.class);

    // assert rendered page class
    tester.assertRenderedPage(HomePage.class);
  }
}
