package jp.ac.chitose.gishi_yama;

import org.apache.wicket.Page;
import org.apache.wicket.markup.html.WebPage;
import org.springframework.boot.builder.SpringApplicationBuilder;

import com.giffing.wicket.spring.boot.starter.app.WicketBootWebApplication;
import com.giffing.wicket.spring.boot.starter.context.WicketSpringBootApplication;

import jp.ac.chitose.gishi_yama.page.HomePage;

@WicketSpringBootApplication
public class WicketApplication extends WicketBootWebApplication {

  public static void main(String[] args) {
    new SpringApplicationBuilder().sources(WicketApplication.class).run(args);
  }

  @Override
  public Class<? extends Page> getHomePage() {
    return HomePage.class;
  }

  @Override
  protected Class<? extends WebPage> getSignInPageClass() {
    return null;
  }

}
