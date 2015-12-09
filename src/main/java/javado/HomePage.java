package javado;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.html.HTML5Attributes;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.validation.validator.RangeValidator;
import org.apache.wicket.validation.validator.StringValidator;

import de.agilecoders.wicket.webjars.request.resource.WebjarsCssResourceReference;
import de.agilecoders.wicket.webjars.request.resource.WebjarsJavaScriptResourceReference;

public class HomePage extends WebPage {
  private static final long serialVersionUID = -8636699560519965343L;

  public HomePage() {
    IModel<String> nameModel = Model.of("");
    IModel<Integer> indexModel = Model.of(1);
    IModel<String> resultModel = Model.of("");

    queue(new FeedbackPanel("feedback"));

    Form<Void> form = new Form<Void>("form") {
      @Override
      protected void onSubmit() {
        super.onSubmit();
        List<String> omikujis = Stream.of("大吉", "中吉", "小吉", "吉", "凶")
            .collect(Collectors.toList());
        Collections.shuffle(omikujis);
        String result = nameModel.getObject()
                        + "さんの運勢は..."
                        + omikujis.get(indexModel.getObject() - 1);
        resultModel.setObject(result);
      }
    };
    queue(form);

    form.queue(new RequiredTextField<String>("form_name", nameModel) {
      @Override
      protected void onInitialize() {
        super.onInitialize();
        add(StringValidator.lengthBetween(1, 10));
        add(new HTML5Attributes());
        setLabel(Model.of("名前"));
      }
    });

    form.queue(new RequiredTextField<Integer>("index", indexModel, Integer.class) {
      @Override
      protected void onInitialize() {
        super.onInitialize();
        add(RangeValidator.range(1, 5));
        add(new HTML5Attributes());
        setLabel(Model.of("番号"));
      }
    });

    queue(new Label("result", resultModel) {
      @Override
      protected void onConfigure() {
        super.onConfigure();
        setVisible(!Objects.equals(getDefaultModelObjectAsString(), ""));
      }
    });
  }

  @Override
  public void renderHead(IHeaderResponse response) {
    super.renderHead(response);
    response.render(JavaScriptHeaderItem.forReference(getApplication().getJavaScriptLibrarySettings()
        .getJQueryReference()));
    response.render(CssHeaderItem.forReference(
        new WebjarsCssResourceReference("bootstrap/current/css/bootstrap.min.css")));
    response.render(CssHeaderItem.forReference(
        new WebjarsCssResourceReference("bootstrap/current/css/bootstrap-theme.min.css")));
    response.render(JavaScriptHeaderItem.forReference(
        new WebjarsJavaScriptResourceReference("bootstrap/current/js/bootstrap.min.js")));
  }

}
